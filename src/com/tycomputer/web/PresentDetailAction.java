/*
 * @(#)LoginController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.web;

import com.tycomputer.common.web.BaseAction;

/**
 *	日期		:	2010-5-2<br>
 *	作者		:	zhangliuhua<br>
 *	项目		:	dong<br>
 *	功能		:	网站，礼品详细页面  Controller<br>
 */
public class PresentDetailAction extends BaseAction {
	
	private IPresentDetailService presentDetailService;
	
	private String i;

	

	public void setPresentDetailService(IPresentDetailService presentDetailService) {
		this.presentDetailService = presentDetailService;
	}



	/* (non-Javadoc)
	 * @see com.tycomputer.common.web.MultiFormActionController#initMethod(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public String  execute () throws Exception {
		if ( (getI() != null) && (!getI().equals(""))){
			String json = presentDetailService.getPresentListJSON(getI());
			if (json != null){
				setRequest("arr", json);
				setRequest("p", getI());
				return "detail";
			}
			
//			Present present = presentDetailService.getPresent(getI());
//			if (present != null){
//				setRequest("p", present);
//				return "page";
//			}

			
		}
		return "product";
		
	}



	public String getI() {
		return i;
	}



	public void setI(String i) {
		this.i = i;
	}
	
	

}
