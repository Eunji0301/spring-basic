package com.myaws.myapp.domain;

public class SearchCriteria extends Criteria{
	private String search;
	private String keyword; 
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
