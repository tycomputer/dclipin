/*
 * @(#)LoginController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.web;

import com.tycomputer.common.web.BaseAction;

/**
 *	日期		:	2010-5-2<br>
 *	作者		:	zhangliuhua<br>
 *	项目		:	dong<br>
 *	功能		:	网站，礼品详细页面  Controller<br>
 */
public class PresentDetailAction extends BaseAction {
	
	private IPresentDetailService hjDetailService;
	
	private String i;

	

	

	/* (non-Javadoc)
	 * @see com.tycomputer.common.web.MultiFormActionController#initMethod(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public String  execute () throws Exception {
		if ( (getI() != null) && (!getI().equals(""))){
			String json = hjDetailService.getPresentListJSON(getI());
			if (json != null){
				setRequest("arr", json);
				setRequest("p", getI());
				return "detail";
			}
		}
		return "product";
		
	}



	public String getI() {
		return i;
	}



	public void setI(String i) {
		this.i = i;
	}



	public void setHjDetailService(IPresentDetailService hjDetailService) {
		this.hjDetailService = hjDetailService;
	}
	
	

}
