/*
 * @(#)Constants.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.util;

/**
 * 日期 : 2010-5-2<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dong<br>
 * 功能 : <br>
 */
public class Constants {

	/**
	 * 提示信息
	 */
	public static final String _MESSAGE = "__msg";

	/**
	 * session 中登陆用户名
	 */
	public static final String USER_NAME = "__USER_NAME";

	/**
	 * dclipin网站标题
	 */
	public static String siteTitle;
	public static String keywords;
	public static String description;
	
	
	/**
	 * 旭日东升网站标题
	 */
	public static String hjsiteTitle;
	public static String hjkeywords;
	public static String hjdescription;

	/**
	 * 小图片宽
	 */
	public static int litWidth = 150;
	/**
	 * 小图片高
	 */
	public static int litHeight = 150;
	/**
	 * 大图片宽
	 */
	public static int bigWidth = 394;
	/**
	 * 大图片高
	 */
	public static int bigHeight = 394;

	/**
	 * dclipin网站水印
	 */
	public static String waterName = "东创伟业";

	public static String waterUrl = "www.dclipin.com";
	
	
	
	/**
	 * huo jia网站水印
	 */
	public static String hjwaterName = "旭日东升";

	public static String hjwaterUrl = "www.xrdshuojia.com";

	public int getLitWidth() {
		return Constants.litWidth;
	}

	public void setLitWidth(int litWidth) {
		Constants.litWidth = litWidth;
	}

	public int getLitHeight() {
		return Constants.litHeight;
	}

	public void setLitHeight(int litHeight) {
		Constants.litHeight = litHeight;
	}

	public int getBigWidth() {
		return Constants.bigWidth;
	}

	public void setBigWidth(int bigWidth) {
		Constants.bigWidth = bigWidth;
	}

	public int getBigHeight() {
		return Constants.bigHeight;
	}

	public void setBigHeight(int bigHeight) {
		Constants.bigHeight = bigHeight;
	}

	public String getSiteTitle() {
		return Constants.siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		Constants.siteTitle = siteTitle;
	}

	public String getKeywords() {
		return Constants.keywords;
	}

	public void setKeywords(String keywords) {
		Constants.keywords = keywords;
	}

	public String getDescription() {
		return Constants.description;
	}

	public void setDescription(String description) {
		Constants.description = description;
	}

	public void setWaterName(String waterName) {
		Constants.waterName = waterName;
	}

	public void setWaterUrl(String waterUrl) {
		Constants.waterUrl = waterUrl;
	}

	public String getWaterName() {
		return waterName;
	}

	public String getWaterUrl() {
		return waterUrl;
	}

	public String getHjwaterName() {
		return hjwaterName;
	}

	public void setHjwaterName(String hjwaterName) {
		Constants.hjwaterName = hjwaterName;
	}

	public String getHjwaterUrl() {
		return hjwaterUrl;
	}

	public void setHjwaterUrl(String hjwaterUrl) {
		Constants.hjwaterUrl = hjwaterUrl;
	}

	public String getHjsiteTitle() {
		return hjsiteTitle;
	}

	public void setHjsiteTitle(String hjsiteTitle) {
		Constants.hjsiteTitle = hjsiteTitle;
	}

	public String getHjkeywords() {
		return hjkeywords;
	}

	public void setHjkeywords(String hjkeywords) {
		Constants.hjkeywords = hjkeywords;
	}

	public String getHjdescription() {
		return hjdescription;
	}

	public void setHjdescription(String hjdescription) {
		Constants.hjdescription = hjdescription;
	}

}
