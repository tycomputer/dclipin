/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.tycomputer.entity.Datacata;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 生成html页面 managerImpl<br>
 */

public class GenhtmlServiceImpl implements IGenhtmlService {
	private HibernateTemplate dao;

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}
	
	private ServletContext sc ;
	private Map<String,Object> map = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see com.tycomputer.manager.service.IGenhtmlService#genhtml()
	 */
	public boolean genhtml() {
		
		
		ActionContext ct = ActionContext.getContext();		
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		sc = request.getSession().getServletContext();
		
		
		map.put("cata", dao.loadAll(Datacata.class));//目录
		map.put("newPre", com.tycomputer.common.web.CacheData.NEW_PRESENT);//新品
		map.put("comPre", com.tycomputer.common.web.CacheData.NEW_PRESENT);//精品
		genIndex();
		
		
		return true;
	}
	
	
	private boolean genIndex(){		
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setServletContextForTemplateLoading(sc, "WEB-INF/templates");
		try {
			Template t = cfg.getTemplate("default.ftl");
			t.setEncoding("UTF-8");
			t.setOutputEncoding("UTF-8");
			File page = new File(sc.getRealPath("/") + "/html/index.html");
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(page),"UTF-8"));			
			t.process(map, out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

}
