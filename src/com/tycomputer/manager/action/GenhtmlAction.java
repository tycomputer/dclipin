/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.manager.service.IGenhtmlService;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 显示礼品管理 controller<br>
 */
public class GenhtmlAction extends BaseAction{

	private IGenhtmlService genhtmlService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.common.web.MultiFormActionController#initMethod(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */

	@Override
	public String execute() throws Exception{
		return "list";
	}

	public String genhtml() throws Exception {
		if (genhtmlService.genhtml()){
			this.setMessage("生成静态HTML成功！");
		} else {
			this.setMessage("生成静态HTML不成功！");
		}
		return execute();
	}

	
	

	public void setGenhtmlService(IGenhtmlService genhtmlService) {
		this.genhtmlService = genhtmlService;
	}

	

}
