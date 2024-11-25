package com.myproject.myapp.service;

import java.util.ArrayList;

import com.myproject.myapp.domain.MemberVo;

// 스프링에서 사용할 메서드 선언하는 곳
public interface MemberService {
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberId);
	
	public MemberVo memberLoginCheck(String memberId);
	
	public ArrayList<MemberVo> memberSelectAll();
	
}