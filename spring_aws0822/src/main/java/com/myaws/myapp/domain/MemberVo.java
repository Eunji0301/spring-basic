package com.myaws.myapp.domain;

public class MemberVo { // Vo : Value Object 값을 담는 객체 or DTO(Data Transfer Object)라고도 한다.
	private int midx; // DB 테이블에 있는 컬럼 이름과 동일하게 작성
	private String memberId; // 바인딩 기술을 사용하기 위해 html input name명과 동일하게 맞춤
	private String memberPw;
	private String memberName;
	private String memberGender;
	private String memberBirth;
	private String memberAddress;
	private String memberPhone;
	private String memberEmail;
	private String memberHobby;
	private String delYN;
	private String writeDay;
	private String memberIP;
	
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberHobby() {
		return memberHobby;
	}
	public void setMemberHobby(String memberHobby) {
		this.memberHobby = memberHobby;
	}
	public String getDelYN() {
		return delYN;
	}
	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}
	public String getWriteDay() {
		return writeDay;
	}
	public void setWriteDay(String writeDay) {
		this.writeDay = writeDay;
	}
	public String getMemberIP() {
		return memberIP;
	}
	public void setMemberIP(String memberIP) {
		this.memberIP = memberIP;
	}
}
