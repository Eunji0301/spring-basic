package com.myaws.myapp.controller;

import java.util.ArrayList;

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
		logger.info("memberSignin 들어옴");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		return "WEB-INF/member/memberSignin";
	}

	@RequestMapping(value = "memberSigninAction.aws", method = RequestMethod.POST)
	public String memberSigninAction(MemberVo mv) {
		logger.info("memberSigninAction 들어옴");
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
		logger.info("memberLogin 들어옴");

		return "WEB-INF/member/memberLogin";
	}

	@RequestMapping(value = "memberLoginAction.aws", method = RequestMethod.POST)
	public String memberLoginAction(@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw, RedirectAttributes rttr) {
		System.out.println("memberLoginAction");
		System.out.println("memberId : " + memberId);
		System.out.println("memberPw : " + memberPw);

		MemberVo mv = memberService.memberLoginCheck(memberId);

		// 저장된 비밀번호를 가져온다.
		String path = "";
		if (mv != null) { // 객체 값이 없으면
			String reservedPw = mv.getMemberPw();
			if (bCryptPasswordEncoder.matches(memberPw, reservedPw)) {
				System.out.println("비밀번호 일치");
				rttr.addAttribute("midx", mv.getMidx());
				rttr.addAttribute("memberId", mv.getMemberId());
				rttr.addAttribute("memberName", mv.getMemberName());

				path = "redirect:/";
			} else {
				rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");

				path = "redirect:/member/memberLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "해당하는 아이디가 없습니다.");

			path = "redirect:/member/memberLogin.aws";
		}

		// 회원정보를 세션에 담는다.
		// 로그인이 안되면 다시 로그인 페이지로 가고
		// 로그인이 되면 메인으로 가라

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
		logger.info("memberList 들어옴");
		ArrayList<MemberVo> alist = memberService.memberSelectAll();
		
		model.addAttribute("alist", alist);
		return "WEB-INF/member/memberList";
	}
}
