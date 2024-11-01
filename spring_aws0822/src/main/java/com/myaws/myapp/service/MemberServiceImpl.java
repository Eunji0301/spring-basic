package com.myaws.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.persistance.MemberMapper;

public class MemberServiceImpl implements MemberService{
	private MemberMapper mm;
	
	@Autowired
	public MemberServiceImpl(SqlSession sqlSession) {
		this.mm = sqlSession.getMapper(MemberMapper.class);
	}
	
	@Override
	public int memberInsert(MemberVo mv) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
