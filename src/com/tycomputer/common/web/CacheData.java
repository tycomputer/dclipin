/*
 * @(#)OnLoadCacheData.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.entity.Datacata;
import com.tycomputer.entity.Datatype;
import com.tycomputer.entity.Present;
import com.tycomputer.hj.entity.Hjdatacata;
import com.tycomputer.hj.entity.Hjdatatype;
import com.tycomputer.hj.entity.Hjpresent;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 启动时载入，缓存标准数据<br>
 */
public class CacheData {

	private HibernateTemplate dao;
	
	/**
	 * 礼品目录、分类
	 */
	public static List<Datacata> DATACATA = new ArrayList<Datacata>();
	

	/**
	 * 新品
	 */
	public static List<Present> NEW_PRESENT = new ArrayList<Present>();
	
	/**
	 * 精品
	 */
	public static List<Present> COM_PRESENT = new ArrayList<Present>();
	
	public static String CATATYPE_JSON = "";
	
	
	/**
	 * 货架目录、分类
	 */
	public static List<Hjdatacata> HJDATACATA = new ArrayList<Hjdatacata>();
	

	/**
	 * 货架新品
	 */
	public static List<Hjpresent> HJ_NEW_PRESENT = new ArrayList<Hjpresent>();
	
	/**
	 * 货架精品
	 */
	public static List<Hjpresent> HJ_COM_PRESENT = new ArrayList<Hjpresent>();
	
	/**
	 * 育英才 新闻，最后添加的100个，网站只显示100个
	 */
	public static List<String[]> YYC_NEWS = new ArrayList<String[]>();
	
	public static String HJ_CATATYPE_JSON = "";


	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	public void cache(){
		DATACATA = dao.loadAll(Datacata.class);
		NEW_PRESENT = dao.find("select t.present from Showedpresent t where t.type='0' order by t.sn");
		COM_PRESENT = dao.find("select t.present from Showedpresent t where t.type='1' order by t.sn");
		try {
			JSONArray array = new JSONArray();
			for (int i=0; i<DATACATA.size(); i++){
				JSONObject dir =  new JSONObject();
				dir.put("di", DATACATA.get(i).getCataId());
				dir.put("dn", DATACATA.get(i).getCataName());
				Iterator<Datatype> iter = DATACATA.get(i).getDatatypes().iterator();
				JSONArray types = new JSONArray();
				while (iter.hasNext()) {
					Datatype datatype = (Datatype) iter.next();
					JSONObject type =  new JSONObject();
					type.put("ti", datatype.getTypeId());
					type.put("tn", datatype.getTypeName());
					types.put(type);
				}
				dir.put("ts", types);
				array.put(dir);
			}
			CATATYPE_JSON = array.toString();
		} catch (Exception e) {
		}
		
		//以下为货架缓存		
		HJDATACATA = dao.loadAll(Hjdatacata.class);
		HJ_NEW_PRESENT = dao.find("select t.present from Hjshowedpresent t where t.type='0' order by t.sn");
		HJ_COM_PRESENT = dao.find("select t.present from Hjshowedpresent t where t.type='1' order by t.sn");
		try {
			JSONArray array = new JSONArray();
			for (int i=0; i<HJDATACATA.size(); i++){
				JSONObject dir =  new JSONObject();
				dir.put("di", HJDATACATA.get(i).getCataId());
				dir.put("dn", HJDATACATA.get(i).getCataName());
				Iterator<Hjdatatype> iter = HJDATACATA.get(i).getDatatypes().iterator();
				JSONArray types = new JSONArray();
				while (iter.hasNext()) {
					Hjdatatype datatype = (Hjdatatype) iter.next();
					JSONObject type =  new JSONObject();
					type.put("ti", datatype.getTypeId());
					type.put("tn", datatype.getTypeName());
					types.put(type);
				}
				dir.put("ts", types);
				array.put(dir);
			}
			HJ_CATATYPE_JSON = array.toString();
		} catch (Exception e) {
		}
		
		CacheData.YYC_NEWS = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("select t.uuid,t.newsTitle,t.addData from YycNews t where t.flag='0' order by t.addData desc");
				query.setFirstResult(0);
				query.setMaxResults(100);
				List l = query.list();
				List<String[]> itemList = new ArrayList<String[]>();
				for (int i=0; i<l.size(); i++){
					Object[] obj = (Object[])l.get(i);
					String[] item = new String[3];
					item[0] = (String)obj[0];
					item[1] = (String)obj[1];
					item[2] = DateUtil.format((Calendar)obj[2], "yyyy-MM-dd");
					itemList.add(item);
				}				
				return itemList;		
			}
		});
	}

	
	

//	public static Map<String, Datacata> STAND_DATA = new HashMap<String, Datacata>();
//
//	/**
//	 * 礼品分类[0]-->目录ID，[1]-->目录名称，<br>
//	 * [2]-->List<Object[]>----->0类别ID，1类别名称
//	 */
//	public static List<Object[]> PRESENT_SORT = new ArrayList<Object[]>();
//	
//	@SuppressWarnings("unchecked")
//	public static String getPresentSortJson(){
//		JSONArray array = new JSONArray();
//		try {
//			for (int i=0; i<PRESENT_SORT.size(); i++){
//				JSONObject dir =  new JSONObject();
//				dir.put("di", PRESENT_SORT.get(i)[0]);
//				dir.put("dn", PRESENT_SORT.get(i)[1]);
//				JSONArray types = new JSONArray();
//				List l = (List) PRESENT_SORT.get(i)[2];
//				for (int j=0; j<l.size(); j++){
//					Object[] obj = (Object[])l.get(j);
//					JSONObject type =  new JSONObject();
//					type.put("ti", obj[0]);
//					type.put("tn", obj[1]);
//					types.put(type);
//				}
//				dir.put("ts", types);
//				array.put(dir);
//			}
//		} catch (Exception e) {
//		}
//		
//		return array.toString();
//	}
//	
//	
//
//	/**
//	 * 
//	 * 功能说明 : 根据Key 返回类别名称
//	 * 
//	 * @param k
//	 * @return
//	 */
//	public static String getNameOfDatadeta(String k) {
//		Datacata datadeta = STAND_DATA.get(k);
//		if (datadeta != null) {
//			return datadeta.getCataName();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * 功能说明 : 根据Key 返回目录名称
//	 * 
//	 * @param k
//	 * @return
//	 */
//	public static String getNameOfDatatype(String k) {
//		Collection<Datacata> collection = STAND_DATA.values();
//		Iterator<Datadeta> iterator = collection.iterator();
//		while (iterator.hasNext()) {
//			Datadeta datadeta = (Datadeta) iterator.next();
//			if (datadeta.getDatatype().getId().equals(k)) {
//				return datadeta.getDatatype().getDatatypeName();
//			}
//		}
//		return null;
//	}
//
//	@SuppressWarnings("unchecked")
//	public void cacheDatedeta() {
//		// 重新加载标准数据缓存
//		List l = dao.getList(Datatype.class, false);
//		Map<String, Datadeta> temp = new HashMap<String, Datadeta>();
//		List<Object[]> presentSort = new ArrayList<Object[]>();
//		for (int i = 0; i < l.size(); i++) {
//			Datatype datatype = (Datatype) l.get(i);
//			
//			Object[] obj = new Object[3];
//			obj[0] = datatype.getId();
//			obj[1] = datatype.getDatatypeName();
//			List<String[]> datas = new ArrayList<String[]>();
//			
//			for (int j = 0; j < datatype.getDetaList().size(); j++) {
//				Datadeta datadeta = datatype.getDetaList().get(j);
//				datadeta.setDatatype(datatype);
//				datas.add(new String[]{datadeta.getDetaId(),datadeta.getDetaName()});
//				temp.put(datadeta.getDetaId(), datadeta);
//			}
//			obj[2] = datas; 
//			presentSort.add(obj);
//		}
//		CacheData.STAND_DATA = temp;
//		CacheData.PRESENT_SORT  = presentSort;
//	}
//
//	/**
//	 * 
//	 * 功能说明 : 返回某类标准数据的所有值，以数组形式返回，[0]--->ID,[1]--->name
//	 * 
//	 * @param type
//	 * @return
//	 */
//	public static List<String[]> getAllTypeDeta(String type) {
//		List<String[]> list = new ArrayList<String[]>();
//		Collection<Datadeta> collection = STAND_DATA.values();
//		Iterator<Datadeta> iterator = collection.iterator();
//		while (iterator.hasNext()) {
//			Datadeta datadeta = (Datadeta) iterator.next();
//			if (datadeta.getDatatype().getId().equals(type)) {
//				list.add(new String[] { datadeta.getDetaId(), datadeta.getDetaName() });
//			}
//		}
//
//		return list;
//	}



	
}
