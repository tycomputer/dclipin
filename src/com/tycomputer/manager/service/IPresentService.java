/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.service;

import java.io.File;
import java.util.List;

import com.tycomputer.manager.action.PresentForm;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站技术文章管理 IManager<br>
 */
public interface IPresentService {

	/**
	 * 
	 * 功能说明 : 根据提交的查询条件构成SQL
	 * 
	 * @param form
	 * @return
	 */
	public String getQuerySQL(PresentForm form);

	/**
	 * 
	 * 功能说明 : 保存文章
	 * 
	 * @param form
	 */
	public String  savePresent(PresentForm form,File pic,String path,String fileName);

	/**
	 * 
	 * 功能说明 : 根据ID 载入实体类并设置 form
	 * 
	 * @param form
	 */
	public void loadEntityAndSetFrom(PresentForm form);

	/**
	 * 
	 * 功能说明 : 返回数据明细,str[0]typeID,str[1]detaid,str[2] detaname
	 * 
	 * @return
	 */
	public List<String[]> getDataDetail();

	/**
	 * 
	 * 功能说明 :
	 * 
	 * @param imgId
	 */
	public void delPresent(PresentForm form);

//	/**
//	 * 
//	 * 功能说明 : 删除旧的图片
//	 * 
//	 * @param newImg
//	 * @param oldImgId
//	 * @return
//	 */
//	public void delOldImg(String imgId);

}
