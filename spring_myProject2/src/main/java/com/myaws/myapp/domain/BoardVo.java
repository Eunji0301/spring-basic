package com.myaws.myapp.domain;

public class BoardVo {
	private int bidx;
	private int boardOriginIdx;
	private int boardDepth;
	private int boardLevel;
	private String boardSubject;
	private String boardContents;
	private int boardWriterIdx;
	private String boardWriterType;
	private int boardRecom;
	private int boardViewCnt;
	private String boardFileName;
	private String boardWriteDay;
	private String boardModifyDay;
	private String boardDelYn;
	private String boardIp;
	private String boardWriterName;
	private String uploadedFilename;
	
	private int pidx;
	private int didx;
	
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public int getDidx() {
		return didx;
	}
	public void setDidx(int didx) {
		this.didx = didx;
	}

	public String getUploadedFilename() {
		return uploadedFilename;
	}
	public void setUploadedFilename(String uploadedFilename) {
		this.uploadedFilename = uploadedFilename;
	}
	public String getBoardWriterName() {
		return boardWriterName;
	}
	public void setBoardWriterName(String boardWriterName) {
		this.boardWriterName = boardWriterName;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public int getBoardOriginIdx() {
		return boardOriginIdx;
	}
	public void setBoardOriginIdx(int boardOriginIdx) {
		this.boardOriginIdx = boardOriginIdx;
	}
	public int getBoardDepth() {
		return boardDepth;
	}
	public void setBoardDepth(int boardDepth) {
		this.boardDepth = boardDepth;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public int getBoardWriterIdx() {
		return boardWriterIdx;
	}
	public void setBoardWriterIdx(int boardWriterIdx) {
		this.boardWriterIdx = boardWriterIdx;
	}
	public String getBoardWriterType() {
		return boardWriterType;
	}
	public void setBoardWriterType(String boardWriterType) {
		this.boardWriterType = boardWriterType;
	}
	public int getBoardRecom() {
		return boardRecom;
	}
	public void setBoardRecom(int boardRecom) {
		this.boardRecom = boardRecom;
	}
	public int getBoardViewCnt() {
		return boardViewCnt;
	}
	public void setBoardViewCnt(int boardViewCnt) {
		this.boardViewCnt = boardViewCnt;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public String getBoardWriteDay() {
		return boardWriteDay;
	}
	public void setBoardWriteDay(String boardWriteDay) {
		this.boardWriteDay = boardWriteDay;
	}
	public String getBoardModifyDay() {
		return boardModifyDay;
	}
	public void setBoardModifyDay(String boardModifyDay) {
		this.boardModifyDay = boardModifyDay;
	}
	public String getBoardDelYn() {
		return boardDelYn;
	}
	public void setBoardDelYn(String boardDelYn) {
		this.boardDelYn = boardDelYn;
	}
	public String getBoardIp() {
		return boardIp;
	}
	public void setBoardIp(String boardIp) {
		this.boardIp = boardIp;
	}

}
