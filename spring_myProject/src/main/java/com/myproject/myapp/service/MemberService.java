package com.myproject.myapp.service;

import java.util.ArrayList;

import com.myproject.myapp.domain.MemberVo;

// ���������� ����� �޼��� �����ϴ� ��
public interface MemberService {
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberId);
	
	public MemberVo memberLoginCheck(String memberId);
	
	public ArrayList<MemberVo> memberSelectAll();
	
}