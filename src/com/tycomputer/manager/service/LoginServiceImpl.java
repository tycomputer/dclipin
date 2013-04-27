/*
 * @(#)LoginServiceImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.service;

import com.tycomputer.manager.action.LoginForm;

/**
 * 日期 : 2010-5-2<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dong<br>
 * 功能 : <br>
 */
public class LoginServiceImpl implements ILoginService {

	private String username;
	private String password;

	private String hjusername;
	private String hjpassword;
	
	
	private String yycusername;
	private String yycpassword;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.manager.service.ILoginService#login(com.tycomputer.manager
	 * .controller.LoginForm)
	 */
	// @Override
	public String login(LoginForm form) {
		if ((form != null) && (form.getUsername() != null) && (form.getUsername().equals(username)) && (form.getPassword() != null)
				&& (form.getPassword().equals(password))) {
			return username;
		} else if ((form != null) && (form.getUsername() != null) && (form.getUsername().equals(hjusername)) && (form.getPassword() != null)
				&& (form.getPassword().equals(hjpassword))) {
			return hjusername;
		}else if ((form != null) && (form.getUsername() != null) && (form.getUsername().equals(yycusername)) && (form.getPassword() != null)
				&& (form.getPassword().equals(yycpassword))) {
			return yycusername;
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHjusername() {
		return hjusername;
	}

	public void setHjusername(String hjusername) {
		this.hjusername = hjusername;
	}

	public String getHjpassword() {
		return hjpassword;
	}

	public void setHjpassword(String hjpassword) {
		this.hjpassword = hjpassword;
	}

	public String getYycusername() {
		return yycusername;
	}

	public void setYycusername(String yycusername) {
		this.yycusername = yycusername;
	}

	public String getYycpassword() {
		return yycpassword;
	}

	public void setYycpassword(String yycpassword) {
		this.yycpassword = yycpassword;
	}

}
