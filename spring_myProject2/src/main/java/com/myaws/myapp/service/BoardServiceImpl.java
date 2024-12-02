package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	@Transactional
	public int boardInsert(BoardVo bv) {
		int value = bm.boardInsert(bv);
		int maxBidx = bv.getBidx();
		int value2 = bm.boardOriginbidxUpdate(maxBidx);
		
		return value + value2;
	}
	
	@Override
	public int boardUpdate(BoardVo bv) {
		int value = bm.boardUpdate(bv);
		return value;
	}
	
	@Override
	public int boardDelete(int bidx, Integer pidx, Integer didx, String password) {
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("bidx", bidx);
		hm.put("password", password);
		
		// pidx 또는 didx에 따라 삭제 조건 설정
		if(pidx != null) {
			hm.put("pidx", pidx);
		}
		
		if(didx != null) {
			hm.put("didx", didx);
		}
		
		int cnt = bm.boardDelete(hm);		
		return cnt;
	}
	
	@Transactional
	@Override
	public int boardReply(BoardVo bv) {
		// 업데이트하고 입력하기
		int value = bm.boardReplyUpdate(bv);
		System.out.println("value : " + value);
		int value2 = bm.boardReplyInsert(bv);
		int maxBidx = bv.getBidx();
		
		return maxBidx;
	}


}
