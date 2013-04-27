/*
 * @(#)LoginController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.web;

import com.tycomputer.common.web.BaseAction;

/**
 * 
 * 日期 : 2011-10-8 下午10:50:40<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才新闻 详细<br>
 */
public class YycNewsDetailAction extends BaseAction {

	private IYycNewsDetailService yycNewsDetailService;

	private String i;

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
		if ((getI() != null) && (!getI().equals(""))) {
			setRequest("news", yycNewsDetailService.getNews(getI()));
			return "detail";
		}
		return "list";

	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public IYycNewsDetailService getYycNewsDetailService() {
		return yycNewsDetailService;
	}

	public void setYycNewsDetailService(IYycNewsDetailService yycNewsDetailService) {
		this.yycNewsDetailService = yycNewsDetailService;
	}

}
