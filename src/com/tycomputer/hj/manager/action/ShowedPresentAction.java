/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.common.web.CacheData;
import com.tycomputer.hj.manager.service.IShowedPresentService;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 显示礼品管理 controller<br>
 */
public class ShowedPresentAction extends BaseAction {

	private IShowedPresentService hjshowedPresentService;
	private CacheData cacheData;
	private ShowedPresentForm form;

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
		setRequest("newsList", hjshowedPresentService.getShowedPresentList("0"));
		setRequest("compsList", hjshowedPresentService.getShowedPresentList("1"));
		setRequest("presents", hjshowedPresentService.getAllPresent());

		return "list";
	}

	public String saveShowedPresent() throws Exception {
		hjshowedPresentService.saveShowedPresent(form);
		cacheData.cache();
		return execute();
	}

	public ShowedPresentForm getForm() {
		return form;
	}

	public void setForm(ShowedPresentForm form) {
		this.form = form;
	}

	public void setCacheData(CacheData cacheData) {
		this.cacheData = cacheData;
	}

	public IShowedPresentService getHjshowedPresentService() {
		return hjshowedPresentService;
	}

	public void setHjshowedPresentService(IShowedPresentService hjshowedPresentService) {
		this.hjshowedPresentService = hjshowedPresentService;
	}

}
