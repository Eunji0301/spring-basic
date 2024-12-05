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
		logger.info("patientSignin ����");
		logger.info("bCryptPasswordEncoder : " + bCryptPasswordEncoder);

		// logger.info("tt���� ? " + tt.test());
		return "WEB-INF/patient/patientSignin";
	}

	@RequestMapping(value = "patientSigninAction.aws", method = RequestMethod.POST)
	public String patientSigninAction(PatientVo pv) {
		logger.info("patientSigninAction ����");
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
		logger.info("patientLogin ����");

		return "WEB-INF/patient/patientLogin";
	}

	@RequestMapping(value = "patientLoginAction.aws", method = RequestMethod.POST)
	public String patientLoginAction(@RequestParam("patientId") String patientId,
			@RequestParam("patientPassword") String patientPassword, RedirectAttributes rttr, HttpSession session) {
		System.out.println("patientLoginAction ����");
		System.out.println("patientId : " + patientId);
		System.out.println("patientPassword : " + patientPassword);
		
		PatientVo pv = patientService.patientLoginCheck(patientId);
		// ����� ��й�ȣ�� �����´�.
//		System.out.println("patientLoginAction pv"+pv);
		String path = "";
		if (pv != null) { // ��ü ���� ������
			String reservedPw = pv.getPatientPassword();
//			System.out.println("reservedPw" + reservedPw);
			if (bCryptPasswordEncoder.matches(patientPassword, reservedPw)) {
				// System.out.println("��й�ȣ ��ġ");
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
				rttr.addFlashAttribute("msg", "���̵�/��й�ȣ�� Ȯ�����ּ���.");

				path = "redirect:/patient/patientLogin.aws";
			}
		} else {
			rttr.addFlashAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�.");

			path = "redirect:/patient/patientLogin.aws";
		}

		// ȸ�������� ���ǿ� ��´�.
		// �α����� �ȵǸ� �ٽ� �α��� �������� ����
		// �α����� �Ǹ� �������� ����

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
	 * String memberList(Model model) { logger.info("memberList ����");
	 * ArrayList<MemberVo> alist = memberService.memberSelectAll();
	 * 
	 * model.addAttribute("alist", alist);
	 * 
	 * return "WEB-INF/member/memberList"; }
	 */
	
	@RequestMapping(value = "patientLogout.aws", method = RequestMethod.GET)
	public String patientLogout(HttpSession session) {
		logger.info("patientLogout ����");
		
		session.removeAttribute("pidx");
		session.removeAttribute("patientName");
		session.removeAttribute("patientId");
		
		session.invalidate();

		return "redirect:/";
	}
	
	@RequestMapping(value = "patientInfo.aws", method = RequestMethod.GET)
	public String patientInfo(@RequestParam("patientId") String patientId, Model model) {
		 logger.info("patientInfo ����");
		 logger.info("patientId: " + patientId);
		 
		 // ȯ�� ������ �������� ���� ȣ��
		 PatientVo pv = patientService.getPatientInfo(patientId);
		 
		 if(pv != null) {
			 // ���̰�� - ��� ���� ������ ���� �������� ��
			 String birthyear = pv.getPatientBirth().substring(0,4);
			 int age = 2024 - Integer.parseInt(birthyear);
					 
			// �𵨿� ȯ�� ������ ���� �߰�
			model.addAttribute("pv",pv);
			model.addAttribute("age",age);
		 } else {
			 logger.info("�ش� ȯ�� ������ �����ϴ�.");
			 model.addAttribute("msg","�ش� ȯ�� ������ �����ϴ�.");
		 }
		 
		 return "WEB-INF/patient/patientInfo";
	}
	
}
