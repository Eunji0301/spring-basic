package com.myaws.myapp.domain;

public class PageMaker {

	private int displayPageNum = 10; 
	private int startPage; 
	private int endPage;
	private int totalCount;

	private boolean prev;
	private boolean next;

	private SearchCriteria scri;

	public SearchCriteria getScri() {
		return scri;
	}

	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) { // 珥앷쾶�떆臾쇱씠 紐뉕컻�씤吏� 諛쏅뒗 硫붿냼�뱶
		this.totalCount = totalCount;
		calcData(); // �럹�씠吏� 紐⑸줉 由ъ뒪�듃 踰덊샇瑜� �굹���궡二쇨린 �쐞�븳 怨꾩궛�떇
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	private void calcData() {

		// 1. 湲곕낯�쟻�쑝濡� 1�뿉�꽌遺��꽣 10源뚯� �굹���굹寃� �꽕�젙�븳�떎 (�럹�씠吏��꽕鍮꾧쾶�씠�뀡�뿉�꽌)
		endPage = (int) (Math.ceil(scri.getPage() / (double) displayPageNum) * displayPageNum);
		// 紐⑤몢 �삱由쇱쿂由ы븯�뒗 硫붿냼�뱶ceil()

		// 2.endPage媛� �꽕�젙�릺�뿀�쑝硫� �떆�옉�럹�씠吏��룄 �꽕�젙
		startPage = (endPage - displayPageNum) + 1;

		// 3.�떎�젣 寃뚯떆臾쇱닔�뿉 �뵲�씪�꽌 endPage瑜� 援ы븯寃좊떎
		int tempEndPage = (int) (Math.ceil(totalCount / (double) scri.getPerPageNum()));

		// 4.�꽕�젙�븳 endPage�� �떎�젣endPage 瑜� 鍮꾧탳�빐�꽌 理쒖쥌 endPage 援ы븳�떎
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		// 5. �씠�쟾�떎�쓬 踰꾪듉 留뚮뱾湲�
		prev = (startPage == 1 ? false : true); // �궪�빆�뿰�궛�옄 �궗�슜
		next = (endPage * scri.getPerPageNum() >= totalCount ? false : true);
	}
}