/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.manager.service;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.web.CacheData;
import com.tycomputer.hj.entity.Hjdatacata;
import com.tycomputer.hj.entity.Hjdatatype;
import com.tycomputer.hj.entity.Hjpresent;
import com.tycomputer.hj.entity.Hjshowedpresent;
import com.tycomputer.hj.manager.action.ShowedPresentForm;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站新品精品礼品管理 managerImpl<br>
 */

public class ShowedPresentServiceImpl implements IShowedPresentService {
	private HibernateTemplate dao;

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.manager.service.IShowedPresentService#getPresentList(java
	 * .lang.String)
	 */

	//@Override
	public List getShowedPresentList(String type) {
		List list = dao.find("select t from Hjshowedpresent t where t.type='" + type + "' order by t.sn");
		return list;
	}

	//@Override
	public String getAllPresent() {
		List list = dao.find("select t.datatype.typeId,t.uid,t.pname from Hjpresent t where t.flag='0' ");

		JSONArray array = new JSONArray();
		try {
			for (int i = 0; i < CacheData.HJDATACATA.size(); i++) {
				Hjdatacata datacata = CacheData.HJDATACATA.get(i);
				JSONObject cata = new JSONObject();
				cata.put("cataid", datacata.getCataId());
				cata.put("cataname", datacata.getCataName());
				// 类别

				JSONArray typeArray = new JSONArray();
				Iterator<Hjdatatype> iterator = datacata.getDatatypes().iterator();
				while (iterator.hasNext()) {
					Hjdatatype datatype = iterator.next();
					JSONObject type = new JSONObject();
					type.put("typeid", datatype.getTypeId());
					type.put("typename", datatype.getTypeName());
					JSONArray presents = new JSONArray();
					for (int j = 0; j < list.size(); j++) {
						Object[] obj = (Object[]) list.get(j);
						if (obj[0].equals(datatype.getTypeId())) {
							JSONObject present = new JSONObject();
							present.put("presentid", obj[1]);
							present.put("presentname", obj[2]);
							presents.put(present);
						}
					}
					type.put("presents", presents);
					typeArray.put(type);
				}
				cata.put("types", typeArray);
				array.put(cata);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return array.toString();
	}

	public void saveShowedPresent(ShowedPresentForm form) {
		
		dao.deleteAll(dao.loadAll(Hjshowedpresent.class));
		
		
		String[] uids = form.getUids();
		if ((uids == null) || (uids.length == 0)) {
			return;
		}
		Integer[] sns = form.getSns();
		String[] types = form.getTypes();
		for (int i = 0; i < uids.length; i++) {
			Hjpresent present = (Hjpresent) dao.load(Hjpresent.class, uids[i]);
			if (present == null) {
				return;
			}
			Hjshowedpresent showedpresent =new Hjshowedpresent();
			showedpresent.setPresent(present);
			showedpresent.setSn(sns[i]);
			showedpresent.setType(types[i]);
			dao.save(showedpresent);
		}

	}


}
