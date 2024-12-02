package com.myaws.myapp.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myaws.myapp.domain.AppointmentVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.AppointmentService;


@Controller
@RequestMapping(value = "/appointment/")
public class AppointmentController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(value = "makeAppointment.aws")
	public String makeAppointment(SearchCriteria scri, Model model) {
		logger.info("makeAppointment µé¾î¿È");

		String path = "WEB-INF/appointment/makeAppointment";

		return path;
	}
	
	@RequestMapping(value = "viewAppointment.aws", method = RequestMethod.GET)
	public String viewAppointment(Model model) {
		logger.info("viewAppointment µé¾î¿È");

		ArrayList<AppointmentVo> blist = appointmentService.appointmentSelectAll();

		model.addAttribute("blist", blist);
		
		return "WEB-INF/appointment/viewAppointment";
	}
	
}
