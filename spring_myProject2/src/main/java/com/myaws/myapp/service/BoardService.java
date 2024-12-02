package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface BoardService {
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri);
	
	public int boardTotalCount(SearchCriteria scri);
	
	public int boardInsert(BoardVo bv);
	
	public BoardVo boardSelectOne(int bidx);
	
	public int boardViewCntUpdate(int bidx);
	
	public int boardRecomUpdate(int bidx);
	
	public int boardDelete(int bidx, Integer pidx, Integer didx, String password);
	
	public int boardUpdate(BoardVo bv);
	
	public int boardReply(BoardVo bv);
}
