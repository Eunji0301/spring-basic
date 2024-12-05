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

import com.myaws.myapp.domain.PatientVo;
import com.myaws.myapp.service.PatientService;

import netscape.javascript.JSObject;

@Controller
@RequestMapping(value = "/patient/")
public class PatientController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@Autowired(required = false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "patientSignin.aws", method = RequestMethod.GET)
	public String patientSignin() {
		logger.info("patientSignin 들어옴");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		// logger.info("tt값은 ? " + tt.test());
		return "WEB-INF/patient/patientSignin";
	}

	@RequestMapping(value = "patientSigninAction.aws", method = RequestMethod.POST)
	public String patientSigninAction(PatientVo pv) {
		logger.info("patientSigninAction 들어옴");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		String patientPw_enc = bCryptPasswordEncoder.encode(pv.getPatientPassword());
		pv.setPatientPassword(patientPw_enc);

		int value = patientService.patientInsert(pv);
		logger.info("value : " + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/";
		} else if (value == 0) {
			path = "redirect:/patient/patientSignin.aws";
		}
		return path;
	}

	@RequestMapping(value = "patientLogin.aws", method = RequestMethod.GET)
	public String patientLogin() {
		logger.info("patientLogin 들어옴");

		return "WEB-INF/patient/patientLogin";
	}

	@RequestMapping(value = "patientLoginAction.aws", method = RequestMethod.POST)
	public String patientLoginAction(@RequestParam("patientId") String patientId,
			@RequestParam("patientPassword") String patientPassword, RedirectAttributes rttr, HttpSession session) {
		System.out.println("patientLoginAction 들어옴");
		System.out.println("patientId : " + patientId);
		System.out.println("patientPassword : " + patientPassword);
		
		PatientVo pv = patientService.patientLoginCheck(patientId);
		// 저장된 비밀번호를 가져온다.
//		System.out.println("patientLoginAction pv"+pv);
		String path = "";
		if (pv != null) { // 객체 값이 있으면
			String reservedPw = pv.getPatientPassword();
//			System.out.println("reservedPw" + reservedPw);
			if (bCryptPasswordEncoder.matches(patientPassword, reservedPw)) {
				// System.out.println("비밀번호 일치");
				session.setAttribute("pidx", pv.getPidx());
				session.setAttribute("patientId", pv.getPatientId());
				session.setAttribute("patientName", pv.getPatientName());
				session.setAttribute("patientPhone",pv.getPatientPhone());
				
				logger.info("saveUrl : " + session.getAttribute("saveUrl"));

				if (session.getAttribute("saveUrl") != null) {
					path = "redirect:" + session.getAttribute("saveUrl").toString();
				} else {
					path = "redirect:/";
				}
			} else {
				rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");

				path = "redirect:/patient/patientLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "해당하는 아이디가 없습니다.");

			path = "redirect:/patient/patientLogin.aws";
		}

		// 회원정보를 세션에 담는다.
		// 로그인이 안되면 다시 로그인 페이지로 가고
		// 로그인이 되면 메인으로 가라

		return path;
	}

	@ResponseBody
	@RequestMapping(value = "patientIdCheck.aws", method = RequestMethod.POST)
	public JSONObject patientIdCheck(@RequestParam("patientId") String patientId) {
		int cnt = patientService.patientIdCheck(patientId);

		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);

		return obj;
	}

	/*
	 * @RequestMapping(value = "memberList.aws", method = RequestMethod.GET) public
	 * String memberList(Model model) { logger.info("memberList 들어옴");
	 * ArrayList<MemberVo> alist = memberService.memberSelectAll();
	 * 
	 * model.addAttribute("alist", alist);
	 * 
	 * return "WEB-INF/member/memberList"; }
	 */
	
	@RequestMapping(value = "patientLogout.aws", method = RequestMethod.GET)
	public String patientLogout(HttpSession session) {
		logger.info("patientLogout 들어옴");
		
		session.removeAttribute("pidx");
		session.removeAttribute("patientName");
		session.removeAttribute("patientId");
		
		session.invalidate();

		return "redirect:/";
	}
	
	@RequestMapping(value = "patientInfo.aws", method = RequestMethod.GET)
	public String patientInfo(@RequestParam("patientId") String patientId, Model model) {
		 logger.info("patientInfo 들어옴");
		 logger.info("patientId: " + patientId);
		 
		 // 환자 정보를 가져오는 서비스 호출
		 PatientVo pv = patientService.getPatientInfo(patientId);
		 
		 if(pv != null) {
			 // 나이계산 - 출생 연도 가져와 현재 연도에서 뺌
			 String birthyear = pv.getPatientBirth().substring(0,4);
			 int age = 2024 - Integer.parseInt(birthyear);
					 
			// 모델에 환자 정보와 나이 추가
			model.addAttribute("pv",pv);
			model.addAttribute("age",age);
		 } else {
			 logger.info("해당 환자 정보가 없습니다.");
			 model.addAttribute("msg","해당 환자 정보가 없습니다.");
		 }
		 
		 return "WEB-INF/patient/patientInfo";
	}
	
}
