package com.myaws.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myaws.myapp.domain.CommentVo;
import com.myaws.myapp.service.CommentService;
import com.myaws.myapp.util.UserIp;

@RestController
@RequestMapping(value="/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired(required = false)
	private UserIp userIp;
	
	@RequestMapping(value="/{bidx}/{block}/commentList.aws") 
	public JSONObject commentList(@PathVariable("bidx") int bidx, @PathVariable("block") int block) {
		System.out.println("commentList 들어옴");
		
		String moreView = "";
		int nextBlock = '0';
		int cnt = commentService.commentTotalCnt(bidx);
		if(cnt > block * 15) {
			moreView = "Y";
			nextBlock = block + 1;
		} else {
			moreView = "N";
			nextBlock = block;
		}
		
		ArrayList<CommentVo> clist = commentService.commentSelectAll(bidx, block);
		JSONObject js = new JSONObject();
		System.out.println("clist" + clist);
		js.put("clist", clist);
		js.put("moreView", moreView);
		
		return js;
	}
	
	@RequestMapping(value="/commentWriteAction.aws")
	@ResponseBody // JSON 형식의 문자열 직접 반환
	public JSONObject commentWriteAction(CommentVo cv, HttpServletRequest request) throws Exception {
		System.out.println("commentWriteAction 들어옴");
		
		// 세션에서 pidx(환자)와 didx(의사)를 확인하여 구분
		String commentWriter = null;
		int userType = 0; // 1 : 환자, 2 : 의사
		
		if(request.getSession().getAttribute("pidx") != null) {
			userType = 1;
			cv.setPidx(Integer.parseInt(request.getSession().getAttribute("pidx").toString()));
			commentWriter = (String) request.getSession().getAttribute("patientName");
		} else if(request.getSession().getAttribute("didx") != null) {
			userType = 2;
			cv.setDidx(Integer.parseInt(request.getSession().getAttribute("didx").toString()));
			commentWriter = (String) request.getSession().getAttribute("doctorName");
		} else {
			JSONObject js = new JSONObject();
			js.put("value", 0); // 로그인하지 않은 경우
			return js;
		}
		
		cv.setCommentWriter(commentWriter);
		cv.setCommentIp(userIp.getUserIp(request));
		
		int value = (userType == 1) ? commentService.commentInsert(cv) : commentService.commentInsert_Doctor(cv);
		
		JSONObject js = new JSONObject();;
		js.put("value", value);
		return js;
	}
	
	@RequestMapping(value="/{cidx}/commentDeleteAction.aws") 
	public JSONObject commentDeleteAction(@PathVariable("cidx") int cidx, HttpServletRequest request, CommentVo cv) throws Exception{
		System.out.println("commentDeleteAction 들어옴");
		
		JSONObject js = new JSONObject();
		
		// 세션에서 pidx(환자) 또는 didx(의사) 확인
		if(request.getSession().getAttribute("pidx") != null) {
			cv.setPidx(Integer.parseInt(request.getSession().getAttribute("pidx").toString()));
			int value = commentService.commentDelete(cv);
			js.put("value", value);
		} else if(request.getSession().getAttribute("didx") != null) {
			cv.setDidx(Integer.parseInt(request.getSession().getAttribute("didx").toString()));
			int value = commentService.commentDelete_Doctor(cv);
			js.put("value", value);
		} else {
			// 로그인하지 않으면 처리 불가
			js.put("value", 0); // 로그인하지 않은 경우
			return js;
		}
		
		cv.setCidx(cidx);
		cv.setCommentIp(userIp.getUserIp(request));
		
		
				
		return js;
	}
	
}
