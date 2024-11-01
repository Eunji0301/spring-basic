package com.myaws.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;
import com.myaws.myapp.service.Test;

@Controller
@RequestMapping(value = "/member/")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/*
	 * @Autowired private Test tt;
	 */
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "memberSignin.aws", method = RequestMethod.GET)
	public String memberSignin(MemberVo mv) {
		logger.info("memberSigninAction µé¾î¿È");
		
		int value = memberService.memberInsert(mv);
		logger.info("value : " + value);
		
		String path = "";
		if(value == 1) {
			path = "redirect:/";
		} else if(value == 0) {
			path = "redirect:/member/memberSignin.aws";
		}
		
		return path;
	}
	
	@RequestMapping(value = "memberSigninAction.aws", method = RequestMethod.POST)
	public String memberSignin() {
		logger.info("memberSignin µé¾î¿È");
		
		// logger.info("tt°ªÀº ? " + tt.test());
		
		return "WEB-INF/member/memberSignin";
	}

	@RequestMapping(value = "memberLogin.aws", method = RequestMethod.GET)
	public String memberLogin() {
		logger.info("memberLogin µé¾î¿È");
		
		return "WEB-INF/member/memberLogin";
	}
}
