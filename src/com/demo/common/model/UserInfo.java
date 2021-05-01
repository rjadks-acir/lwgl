package com.demo.common.model;

public class UserInfo {

	private String username;
	private long submitcount;
	private String role;
	private String realname;
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRealname() {
		return realname;
	}
	public void setSubmitcount(long submitcount) {
		this.submitcount = submitcount;
	}
	public long getSubmitcount() {
		return submitcount;
	}
}
