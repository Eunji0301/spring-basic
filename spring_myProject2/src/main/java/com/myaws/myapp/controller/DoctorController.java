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
		logger.info("doctorSignin ����");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		// logger.info("tt���� ? " + tt.test());
		return "WEB-INF/doctor/doctorSignin";
	}

	@RequestMapping(value = "doctorSigninAction.aws", method = RequestMethod.POST)
	public String doctorSigninAction(DoctorVo dv) {
		logger.info("doctorSigninAction ����");
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
		logger.info("doctorLogin ����");

		return "WEB-INF/doctor/doctorLogin";
	}

	@RequestMapping(value = "doctorLoginAction.aws", method = RequestMethod.POST)
	public String doctorLoginAction(@RequestParam("doctorId") String doctorId,
			@RequestParam("doctorPassword") String doctorPassword, RedirectAttributes rttr, HttpSession session) {
		System.out.println("doctorLoginAction ����");
		System.out.println("doctorId : " + doctorId);
		System.out.println("doctorPassword : " + doctorPassword);
		
		DoctorVo dv = doctorService.doctorLoginCheck(doctorId);
		// ����� ��й�ȣ�� �����´�.
//		System.out.println("doctorLoginAction dv"+dv);
		String path = "";
		if (dv != null) { // ��ü ���� ������
			String reservedPw = dv.getDoctorPassword();
//			System.out.println("reservedPw" + reservedPw);
			if (bCryptPasswordEncoder.matches(doctorPassword, reservedPw)) {
				// System.out.println("��й�ȣ ��ġ");
				session.setAttribute("didx", dv.getDidx());
				session.setAttribute("doctorId", dv.getDoctorId());
				session.setAttribute("doctorName", dv.getDoctorName());
				session.setAttribute("doctorSpecialty", dv.getDoctorSpecialty());
				session.setAttribute("doctorLicenseNo", dv.getDoctorLicenseNo());

				// ����� ������ ���ǿ� ����
	            session.setAttribute("userType", "D"); // �ǻ� �α����� ��� 'D'
	            session.setAttribute("userIdx", dv.getDidx()); // �ǻ��� ���� ID
	            
				logger.info("saveUrl : " + session.getAttribute("saveUrl"));

				if (session.getAttribute("saveUrl") != null) {
					path = "redirect:" + session.getAttribute("saveUrl").toString();
				} else {
					path = "redirect:/";
				}
			} else {
				rttr.addFlashAttribute("msg", "���̵�/��й�ȣ�� Ȯ�����ּ���.");

				path = "redirect:/doctor/doctorLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�.");

			path = "redirect:/doctor/doctorLogin.aws";
		}

		// ȸ�������� ���ǿ� ��´�.
		// �α����� �ȵǸ� �ٽ� �α��� �������� ����
		// �α����� �Ǹ� �������� ����

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
		logger.info("doctorLogout ����");
		
		session.removeAttribute("didx");
		session.removeAttribute("doctorName");
		session.removeAttribute("doctorId");
		
		session.invalidate();

		return "redirect:/";
	}
}
