package com.myaws.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface BoardMapper {
	public ArrayList<BoardVo> boardSelectAll(HashMap<String, Object> hm);
	
	public int boardTotalCount(SearchCriteria scri);
	
	public BoardVo boardSelectOne(int bidx);
	
	public int boardViewCntUpdate(int bidx);
	
	public int boardRecomUpdate(BoardVo bv);
	
	public int boardInsert(BoardVo bv);
	
	public int boardOriginbidxUpdate(int bidx);
	
	public int boardUpdate(BoardVo bv);
	
	public int boardDelete(HashMap<String, Object> hm);
	
	public int boardReply(BoardVo bv);
	
	public int boardReplyUpdate(BoardVo bv);
	
	public int boardReplyInsert(BoardVo bv);
}