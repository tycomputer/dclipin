/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.web;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.yyc.entity.YycNews;

/**
 * 
 * 日期 : 2011-10-8 下午6:15:14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站新闻 ServiceImpl<br>
 */
public class YycNewsDetailServiceImpl implements IYycNewsDetailService {

	private HibernateTemplate dao;

	
	public YycNews getNews(String id) {
		if (id == null){
			return new YycNews();
		}
		Object object = dao.load(YycNews.class, id);
		if (object != null){
			YycNews news = (YycNews)object;
			if (news.getFlag().equals("0")){
				return news;
			} else {
				return new YycNews();
			}
		}
		return new YycNews();
	}


	public HibernateTemplate getDao() {
		return dao;
	}


	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}
	
	
}
