/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.manager.service;

import java.util.List;

import com.tycomputer.hj.manager.action.ShowedPresentForm;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : IManager<br>
 */

public interface IShowedPresentService {
	
//	/**
//	 * 
//	 * 功能说明 : 根据礼品ID返回礼品类
//	 * @param presentId
//	 * @return
//	 */
//	public Present getPresentById(String presentId);
	
	/**
	 * 
	 * 功能说明 : 返回新或精品礼品列表
	 * @return
	 */
	
	public List getShowedPresentList(String type);

	/**
	 * 功能说明 : 以Json格式返回所有
	 * @return
	 */
	public String getAllPresent();
	
	
	/**
	 * 
	 * 功能说明 : 保存新、精礼品 
	 * @param form
	 */
	public void saveShowedPresent(ShowedPresentForm form);
	
//	/**
//	 * 
//	 * 功能说明 : 把回所有  新、精礼品 
//	 * @return
//	 */
//	public List getAllShowedPresent();
//	
//	
//	/**
//	 * 
//	 * 功能说明 : 保存 新、精礼品
//	 * @param showedPresent
//	 */
//	public void saveShowedPresent(ShowedPresent showedPresent);
//	
//	/**
//	 * 
//	 * 功能说明 : 更新 新、精礼品
//	 * @param showedPresent
//	 */
//	public void updataShowedPresent(ShowedPresent showedPresent);
//	
//	
//	/**
//	 * 
//	 * 功能说明 : 删除  新、精礼品
//	 * @param showedPresent
//	 */
//	public void deleteShowedPresent(String showedPresentId);
//	
}
