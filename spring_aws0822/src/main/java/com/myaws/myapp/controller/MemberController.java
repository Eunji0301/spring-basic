package com.myaws.myapp.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;
import com.myaws.myapp.service.Test;

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
			@RequestParam("memberPw") String memberPw, RedirectAttributes rttr) {
		System.out.println("memberLoginAction");
		System.out.println("memberId : " + memberId);
		System.out.println("memberPw : " + memberPw);

		MemberVo mv = memberService.memberLoginCheck(memberId);

		// ����� ��й�ȣ�� �����´�.
		String path = "";
		if (mv != null) { // ��ü ���� ������
			String reservedPw = mv.getMemberPw();
			if (bCryptPasswordEncoder.matches(memberPw, reservedPw)) {
				System.out.println("��й�ȣ ��ġ");
				rttr.addAttribute("midx", mv.getMidx());
				rttr.addAttribute("memberId", mv.getMemberId());
				rttr.addAttribute("memberName", mv.getMemberName());

				path = "redirect:/";
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

}
