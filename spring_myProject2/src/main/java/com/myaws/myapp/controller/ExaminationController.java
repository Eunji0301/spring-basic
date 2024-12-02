package com.myaws.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;

@Controller
@RequestMapping(value = "/examination/")
public class ExaminationController {
	private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

	@Autowired(required = false)
	private BoardService boardService;
	
	@RequestMapping(value = "examinationWrite.aws")
	public String examinationWrite(SearchCriteria scri, Model model) {
		logger.info("examinationWrite µé¾î¿È");

		String path = "WEB-INF/examination/examinationWrite";

		return path;
	}
	
	@RequestMapping(value = "examinationResult.aws")
	public String examinationResult(SearchCriteria scri, Model model) {
		logger.info("examinationResult µé¾î¿È");

		String path = "WEB-INF/examination/examinationResult";

		return path;
	}
}
