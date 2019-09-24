package com.care.care.userregistration.model;

import java.util.List;

public class PostHelper {
	private String title;
	private String body,contentHTML;
	private String ageLimit;
	private List<Tag> taging;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getContentHTML() {
		return contentHTML;
	}
	public void setContentHTML(String contentHTML) {
		this.contentHTML = contentHTML;
	}
	public String getAgeLimit() {
		return ageLimit;
	}
	
	public List<Tag> getTaging() {
		return taging;
	}
	public void setTaging(List<Tag> arr) {
		this.taging = arr;
	}
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}
//	public PostHelper(String title, String body, String contentHTML, String ageLimit) {
//		super();
//		this.title = title;
//		this.body = body;
//		this.contentHTML = contentHTML;
//		this.ageLimit = ageLimit;
//	}
	public PostHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
