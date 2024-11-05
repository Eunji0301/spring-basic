package com.myaws.myapp.persistance;

import com.myaws.myapp.domain.MemberVo;

// MyBatis용 메서드
public interface MemberMapper {
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberId);
	
	public MemberVo memberLoginCheck(String memberId);
}
