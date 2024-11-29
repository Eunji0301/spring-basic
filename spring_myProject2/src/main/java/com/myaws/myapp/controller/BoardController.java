package com.myaws.myapp.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.PageMaker;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.util.UserIp;

@Controller
@RequestMapping(value = "/board/")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired(required = false)
	private BoardService boardService;

	@Autowired(required = false)
	private PageMaker pm;

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Autowired(required = false)
	private UserIp userIp;
	
	@RequestMapping(value = "boardList.aws")
	public String boardList(SearchCriteria scri, Model model) {
		logger.info("boardList µé¾î¿È");

		int cnt = boardService.boardTotalCount(scri);
		pm.setScri(scri);
		pm.setTotalCount(cnt);

		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);
		model.addAttribute("blist", blist);
		model.addAttribute("pm", pm);

		String path = "WEB-INF/board/boardList";

		return path;
	}
	
	@RequestMapping(value = "boardContent.aws")
	public String boardContent(@RequestParam("bidx") int bidx, Model model) {
		logger.info("boardContent µé¾î¿È");

		boardService.boardViewCntUpdate(bidx);

		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);

		String path = "WEB-INF/board/boardContent";
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value = "boardRecom.aws", method = RequestMethod.GET)
	public JSONObject boardRecom(@RequestParam("bidx") int bidx) {
		int value = boardService.boardRecomUpdate(bidx);

		JSONObject js = new JSONObject();
		js.put("recom", value);
		return js;
	}
}
