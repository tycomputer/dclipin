/*
 * @(#)LoginController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.action;

import com.tycomputer.common.util.Constants;
import com.tycomputer.common.web.BaseAction;
import com.tycomputer.manager.service.ILoginService;

/**
 * 日期 : 2010-5-2<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dong<br>
 * 功能 : <br>
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -558850652697402145L;

	private ILoginService loginService;

	private LoginForm form;

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.common.web.MultiFormActionController#initMethod(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public String execute() throws Exception {
		if (getSession().getAttribute(Constants.USER_NAME) != null && getSession().getAttribute(Constants.USER_NAME).equals("admin")) {
			return "menu";
		} else if (getSession().getAttribute(Constants.USER_NAME) != null && getSession().getAttribute(Constants.USER_NAME).equals("xds")) {
			return "hjmenu";
		}
		String username = loginService.login(form);
		if (username != null) {
			getSession().setAttribute(Constants.USER_NAME, username);

			if (username.equals("admin")) {
				return "menu";
			} else if (username.equals("xds")) {
				return "hjmenu";
			}else if (username.equals("yyc")) {
				return "yycmenu";
			}

		} else if ((form != null) && (form.getUsername() != null) && (!form.getUsername().equals(""))) {
			setMessage("您输入的用户名或密码不对，请重新输入！");
		}
		return "login";
	}

	public LoginForm getForm() {
		return form;
	}

	public void setForm(LoginForm form) {
		this.form = form;
	}

}
