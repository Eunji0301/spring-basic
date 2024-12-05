package com.myaws.myapp.controller;

import javax.servlet.http.HttpSession;

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

import com.myaws.myapp.domain.DoctorVo;
import com.myaws.myapp.service.DoctorService;

@Controller
@RequestMapping(value = "/doctor/")
public class DoctorController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@Autowired(required = false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "doctorSignin.aws", method = RequestMethod.GET)
	public String doctorSignin() {
		logger.info("doctorSignin 들어옴");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		// logger.info("tt값은 ? " + tt.test());
		return "WEB-INF/doctor/doctorSignin";
	}

	@RequestMapping(value = "doctorSigninAction.aws", method = RequestMethod.POST)
	public String doctorSigninAction(DoctorVo dv) {
		logger.info("doctorSigninAction 들어옴");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		String doctorPw_enc = bCryptPasswordEncoder.encode(dv.getDoctorPassword());
		dv.setDoctorPassword(doctorPw_enc);

		int value = doctorService.doctorInsert(dv);
		logger.info("value : " + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/";
		} else if (value == 0) {
			path = "redirect:/doctor/doctorSignin.aws";
		}
		return path;
	}

	@RequestMapping(value = "doctorLogin.aws", method = RequestMethod.GET)
	public String doctorLogin() {
		logger.info("doctorLogin 들어옴");

		return "WEB-INF/doctor/doctorLogin";
	}

	@RequestMapping(value = "doctorLoginAction.aws", method = RequestMethod.POST)
	public String doctorLoginAction(@RequestParam("doctorId") String doctorId,
			@RequestParam("doctorPassword") String doctorPassword, RedirectAttributes rttr, HttpSession session) {
		System.out.println("doctorLoginAction 들어옴");
		System.out.println("doctorId : " + doctorId);
		System.out.println("doctorPassword : " + doctorPassword);
		
		DoctorVo dv = doctorService.doctorLoginCheck(doctorId);
		// 저장된 비밀번호를 가져온다.
//		System.out.println("doctorLoginAction dv"+dv);
		String path = "";
		if (dv != null) { // 객체 값이 있으면
			String reservedPw = dv.getDoctorPassword();
//			System.out.println("reservedPw" + reservedPw);
			if (bCryptPasswordEncoder.matches(doctorPassword, reservedPw)) {
				// System.out.println("비밀번호 일치");
				session.setAttribute("didx", dv.getDidx());
				session.setAttribute("doctorId", dv.getDoctorId());
				session.setAttribute("doctorName", dv.getDoctorName());
				session.setAttribute("doctorSpecialty", dv.getDoctorSpecialty());
				session.setAttribute("doctorLicenseNo", dv.getDoctorLicenseNo());

				// 사용자 정보를 세션에 저장
	            session.setAttribute("userType", "D"); // 의사 로그인일 경우 'D'
	            session.setAttribute("userIdx", dv.getDidx()); // 의사의 고유 ID
	            
				logger.info("saveUrl : " + session.getAttribute("saveUrl"));

				if (session.getAttribute("saveUrl") != null) {
					path = "redirect:" + session.getAttribute("saveUrl").toString();
				} else {
					path = "redirect:/";
				}
			} else {
				rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");

				path = "redirect:/doctor/doctorLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "해당하는 아이디가 없습니다.");

			path = "redirect:/doctor/doctorLogin.aws";
		}

		// 회원정보를 세션에 담는다.
		// 로그인이 안되면 다시 로그인 페이지로 가고
		// 로그인이 되면 메인으로 가라

		return path;
	}

	@ResponseBody
	@RequestMapping(value = "doctorIdCheck.aws", method = RequestMethod.POST)
	public JSONObject doctorIdCheck(@RequestParam("doctorId") String doctorId) {
		int cnt = doctorService.doctorIdCheck(doctorId);

		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);

		return obj;
	}
	
	@RequestMapping(value = "doctorLogout.aws", method = RequestMethod.GET)
	public String doctorLogout(HttpSession session) {
		logger.info("doctorLogout 들어옴");
		
		session.removeAttribute("didx");
		session.removeAttribute("doctorName");
		session.removeAttribute("doctorId");
		
		session.invalidate();

		return "redirect:/";
	}
}
