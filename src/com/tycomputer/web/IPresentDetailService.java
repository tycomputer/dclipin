/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.web;

import com.tycomputer.entity.Present;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站，礼品详细页面 IManager<br>
 */

public interface IPresentDetailService {
	
	public Present getPresent(String id);
	
	public String getPresentListJSON(String id);
}
