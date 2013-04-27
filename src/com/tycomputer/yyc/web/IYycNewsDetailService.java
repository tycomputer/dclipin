/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.web;

import com.tycomputer.yyc.entity.YycNews;

/**
 * 
 * 日期 : 2011-10-8  下午10:51:51<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才新闻 详细<br>
 */

public interface IYycNewsDetailService {
	
	public YycNews getNews(String id);
	
}
