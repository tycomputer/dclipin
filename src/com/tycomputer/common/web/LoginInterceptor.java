/*
 * @(#)LoginInterceptor.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tycomputer.common.util.Constants;

/**
 * 日期 : 2010-4-16<br>
 * 作者 : zhangliuhua<br>
 * 项目 : GS_WUMONWeb<br>
 * 功能 : <br>
 */
public class LoginInterceptor extends AbstractInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation ai) throws Exception {

		if (ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_NAME) != null) {
			return ai.invoke();
		}
		return Action.LOGIN;
	}

}
