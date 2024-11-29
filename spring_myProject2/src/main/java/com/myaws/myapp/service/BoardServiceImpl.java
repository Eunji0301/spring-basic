package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.persistance.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardMapper bm;

	@Autowired
	public BoardServiceImpl(SqlSession sqlSession) {
		this.bm = sqlSession.getMapper(BoardMapper.class);
	}

	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("startPageNum", (scri.getPage() - 1) * scri.getPerPageNum());
		hm.put("search", scri.getSearch());
		hm.put("keyword", scri.getKeyword());
		hm.put("perPageNum", scri.getPerPageNum());
		
		ArrayList<BoardVo> blist = bm.boardSelectAll(hm);
		return blist;
	}

	@Override
	public int boardTotalCount(SearchCriteria scri) {
		int cnt = bm.boardTotalCount(scri);
		return cnt;
	}
	
	@Override
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = bm.boardSelectOne(bidx);
		return bv;
	}

	@Override
	public int boardViewCntUpdate(int bidx) {
		int cnt = bm.boardViewCntUpdate(bidx);
		return cnt;
	}
	
	@Override
	public int boardRecomUpdate(int bidx) {
		BoardVo bv = new BoardVo();
		bv.setBidx(bidx);
		int cnt = bm.boardRecomUpdate(bv);
		int recom = bv.getBoardRecom();
		return recom;
	}
}
