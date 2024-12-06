package com.myaws.myapp.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myaws.myapp.domain.AppointmentVo;
import com.myaws.myapp.domain.PatientVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.AppointmentService;
import com.myaws.myapp.service.PatientService;


@Controller
@RequestMapping(value = "/appointment/")
public class AppointmentController {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(value = "makeAppointment.aws")
	public String makeAppointment(Model model) {
		logger.info("makeAppointment 들어옴");

		String path = "WEB-INF/appointment/makeAppointment";

		return path;
	}
	
	@RequestMapping(value = "viewAppointment.aws", method = RequestMethod.GET)
	public String viewAppointment(Model model) {
		logger.info("viewAppointment 들어옴");

		ArrayList<AppointmentVo> blist = appointmentService.appointmentSelectAll();

		model.addAttribute("blist", blist);
		
		return "WEB-INF/appointment/viewAppointment";
	}
	
	@Transactional
	@RequestMapping(value = "makeAppointmentAction.aws", method = RequestMethod.POST)
	public String makeAppointmentAction(AppointmentVo av) {
		logger.info("makeAppointmentAction 들어옴");

		int value = appointmentService.appointmentInsert(av);
		logger.info("value : " + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/appointment/viewAppointment.aws";
		} else if (value == 0) {
			path = "redirect:/appointment/makeAppointment.aws";
		}
		return path;
	}
	
//	@RequestMapping(value = "getAidxByDetail.aws", method = RequestMethod.GET)
//	public String getAidxByDetail(@RequestParam("aidx") int aidx, Model model) {
//	    logger.info("getAidxByDetail 들어옴, aidx: " + aidx);
//
//	    // aidx 값을 모델에 담아 반환
//	    model.addAttribute("aidx", aidx); // 모델에 aidx 추가
//	    
//	    System.out.println("aidx : " + aidx);
//
//	    return "WEB-INF/examination/examinationWrite"; // aidx를 출력할 페이지로 변경
//	}
}
