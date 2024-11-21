package com.myaws.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;
import com.myaws.myapp.service.Test;

import netscape.javascript.JSObject;

@Controller
@RequestMapping(value = "/member/")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@Autowired(required = false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "memberSignin.aws", method = RequestMethod.GET)
	public String memberSignin() {
		logger.info("memberSignin ����");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		// logger.info("tt���� ? " + tt.test());
		return "WEB-INF/member/memberSignin";
	}

	@RequestMapping(value = "memberSigninAction.aws", method = RequestMethod.POST)
	public String memberSigninAction(MemberVo mv) {
		logger.info("memberSigninAction ����");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		String memberPw_enc = bCryptPasswordEncoder.encode(mv.getMemberPw());
		mv.setMemberPw(memberPw_enc);

		int value = memberService.memberInsert(mv);
		logger.info("value : " + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/";
		} else if (value == 0) {
			path = "redirect:/member/memberSignin.aws";
		}
		return path;
	}

	@RequestMapping(value = "memberLogin.aws", method = RequestMethod.GET)
	public String memberLogin() {
		logger.info("memberLogin ����");

		return "WEB-INF/member/memberLogin";
	}

	@RequestMapping(value = "memberLoginAction.aws", method = RequestMethod.POST)
	public String memberLoginAction(@RequestParam("memberId") String memberId,
			@RequestParam("memberPw") String memberPw, RedirectAttributes rttr, HttpSession session) {
		System.out.println("memberLoginAction");
		System.out.println("memberId : " + memberId);
		System.out.println("memberPw : " + memberPw);
		
		MemberVo mv = memberService.memberLoginCheck(memberId);
		// ����� ��й�ȣ�� �����´�.
//		System.out.println("memberLoginAction mv"+mv);
		String path = "";
		if (mv != null) { // ��ü ���� ������
			String reservedPw = mv.getMemberPw();
//			System.out.println("reservedPw" + reservedPw);
			if (bCryptPasswordEncoder.matches(memberPw, reservedPw)) {
				// System.out.println("��й�ȣ ��ġ");
				rttr.addAttribute("midx", mv.getMidx());
				rttr.addAttribute("memberId", mv.getMemberId());
				rttr.addAttribute("memberName", mv.getMemberName());

				logger.info("saveUrl : " + session.getAttribute("saveUrl"));

				if (session.getAttribute("saveUrl") != null) {
					path = "redirect:" + session.getAttribute("saveUrl").toString();
				} else {
					path = "redirect:/";
				}
			} else {
				rttr.addFlashAttribute("msg", "���̵�/��й�ȣ�� Ȯ�����ּ���.");

				path = "redirect:/member/memberLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�.");

			path = "redirect:/member/memberLogin.aws";
		}

		// ȸ�������� ���ǿ� ��´�.
		// �α����� �ȵǸ� �ٽ� �α��� �������� ����
		// �α����� �Ǹ� �������� ����

		return path;
	}

	@ResponseBody
	@RequestMapping(value = "memberIdCheck.aws", method = RequestMethod.POST)
	public JSONObject memberIdCheck(@RequestParam("memberId") String memberId) {
		int cnt = memberService.memberIdCheck(memberId);

		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);

		return obj;
	}

	@RequestMapping(value = "memberList.aws", method = RequestMethod.GET)
	public String memberList(Model model) {
		logger.info("memberList ����");
		ArrayList<MemberVo> alist = memberService.memberSelectAll();

		model.addAttribute("alist", alist);

		return "WEB-INF/member/memberList";
	}
	
	@RequestMapping(value = "memberLogout.aws", method = RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		logger.info("memberLogout ����");
		
		session.removeAttribute("midx");
		session.removeAttribute("memberName");
		session.removeAttribute("memberId");
		
		session.invalidate();

		return "redirect:/";
	}
}
