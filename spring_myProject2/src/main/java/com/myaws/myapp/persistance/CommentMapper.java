package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.CommentVo;

public interface CommentMapper {
	public ArrayList<CommentVo> commentSelectAll(int bidx, int block);
	
	public int commentInsert(CommentVo cv);
	
	public int commentDelete(CommentVo cv);
	
	// ÀÇ»ç
	public int commentInsert_Doctor(CommentVo cv);
	
	public int commentDelete_Doctor(CommentVo cv);
	
	public int commentTotalCnt(int bidx);
}
