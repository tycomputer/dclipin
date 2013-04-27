/*
 * @(#)ShowPresentBean.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.web;

import java.io.Serializable;

/**
 * 日期 : 2010-5-21<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dong<br>
 * 功能 : <br>
 */
public class ShowPresentBean implements Serializable{

	private static final long serialVersionUID = 2466189876728169459L;
	private String tid;
	private String did;
	private String pid;
	private String words;
	private int totalPage;
	private int countRec;
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCountRec() {
		return countRec;
	}

	public void setCountRec(int countRec) {
		this.countRec = countRec;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}
}
