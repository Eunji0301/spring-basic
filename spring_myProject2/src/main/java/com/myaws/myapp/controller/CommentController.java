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
		
		cv.setCommentIp(userIp.getUserIp(request));
		int value = commentService.commentInsert(cv);
		
		JSONObject js = new JSONObject();;
		js.put("value", value);
		return js;
	}
	
	@RequestMapping(value="/{cidx}/commentDeleteAction.aws") 
	public JSONObject commentDeleteAction(@PathVariable("cidx") int cidx, HttpServletRequest request, CommentVo cv) throws Exception{
		System.out.println("commentDeleteAction 들어옴");
		
		int pidx = Integer.parseInt(request.getSession().getAttribute("pidx").toString());
		
		cv.setPidx(pidx);
		cv.setCidx(cidx);
		cv.setCommentIp(userIp.getUserIp(request));
		
		int value = commentService.commentDelete(cv);
		JSONObject js = new JSONObject();
		
		js.put("value", value);
				
		return js;
	}
}
