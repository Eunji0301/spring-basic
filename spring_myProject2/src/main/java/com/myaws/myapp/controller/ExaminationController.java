package com.myaws.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.ExaminationVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.service.ExaminationService;

@Controller
@RequestMapping(value = "/examination/")
public class ExaminationController {
	private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

	@Autowired
	private ExaminationService examinationService;
	
	@Autowired(required = false)
	private BoardService boardService;
	
	@RequestMapping(value = "examinationWrite.aws")
	public String examinationWrite(Model model) {
		logger.info("examinationWrite ����");
		String path = "WEB-INF/examination/examinationWrite";

		return path;
	}
	
//	@RequestMapping(value = "examinationResult.aws")
//	public String examinationResult(SearchCriteria scri, Model model) {
//		logger.info("examinationResult ����");
//
//		String path = "WEB-INF/examination/examinationResult";
//
//		return path;
//	}
	 @RequestMapping(value = "examinationResult.aws")
	    public String examinationResult(@RequestParam(value = "pidx", required = false) Integer pidx, Model model) {
	        logger.info("examinationResult ����");
	        logger.info("pidx : " + pidx);
	        // ���� ����� DB���� ��ȸ
	        ExaminationVo result = examinationService.getExaminationResult(pidx);
	        logger.info("result : " + result);
	        // ��ȸ�� ���� ����� �𵨿� ��� JSP�� ����
	        model.addAttribute("result", result);
	        model.addAttribute("pidx",pidx);

	        String path = "WEB-INF/examination/examinationResult";
	        return path;
	    }
	
	@Transactional
	@RequestMapping(value = "examinationWriteAction.aws", method = RequestMethod.POST)
	public String examinationWriteAction(ExaminationVo ev) {
		logger.info("examinationWriteAction ����");

		int value = examinationService.examinationInsert(ev);
		logger.info("value : " + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/examination/examinationResult.aws?pidx=" + ev.getPidx();
		} else if (value == 0) {
			path = "redirect:/";
		}
		return path;
	}
}
