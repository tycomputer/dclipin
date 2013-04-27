/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.web;

import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.hj.entity.Hjpresent;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站，礼品详细页面 managerImpl<br>
 */

public class PresentDetailServiceImpl implements IPresentDetailService {
	private HibernateTemplate dao;

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IPresentDetailService#getPresent(java.lang.String)
	 */
	public Hjpresent getPresent(String id) {
		Hjpresent present = (Hjpresent) dao.load(Hjpresent.class, id);
		if (present != null) {
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			present.setPriceStr(df.format(present.getPrice()));
		}
		return present;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IPresentDetailService#getPresentList(java.lang.String)
	 */
	//@Override
	public String getPresentListJSON(String id) {

		List l = dao
				.find("select t.uid,t.datacata.cataName,t.datatype.typeName,t.pname,t.price,t.description,t.bigpic,t.unit,t.spec,t.buynum,t.itsize,t.packsize,t.weight,t.material,t.resale from Hjpresent t where t.flag='0' and t.datatype=(select a.datatype from Hjpresent a where a.uid=?) order by t.ordersn,t.addData desc",
						new Object[] { id });
		if (l != null) {
			JSONArray array = new JSONArray();
			// JSONArray descarray = new JSONArray();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
			try {
				for (int i = 0; i < l.size(); i++) {
					Object[] obj = (Object[]) l.get(i);
					JSONObject p = new JSONObject();
					p.put("uid", obj[0]);
					p.put("cname", obj[1]);
					p.put("tname", obj[2]);
					p.put("pname", obj[3]);
					Double price = (Double) obj[4];
					p.put("price", df.format(price));

					if (obj[5] != null && !obj[5].equals("")) {
						String str = htmlParse(obj[5].toString());
						str = str.replaceAll("\r\n", "<br>");
						str = str.replaceAll("&nbsp;", "\u00A0");
						str = str.replaceAll(" ", "\u00A0");
						p.put("desc", str);
					}
					p.put("pic", obj[6]);
					if (obj[7] != null && !obj[7].equals("")) {
						p.put("unit", obj[7]);
					}
					if (obj[8] != null && !obj[8].equals("")) {
						p.put("spec", obj[8]);
					}
					if (obj[9] != null && ((Integer) obj[9]).intValue() != 0) {
						p.put("buynum", obj[9]);
					}
					if (obj[10] != null && !obj[10].equals("")) {
						p.put("itsize", obj[10]);
					}
					if (obj[11] != null && !obj[11].equals("")) {
						p.put("packsize", obj[11]);
					}
					if (obj[12] != null && !obj[12].equals("")) {
						p.put("weight", obj[12]);
					}
					if (obj[13] != null && !obj[13].equals("")) {
						p.put("material", obj[13]);
					}
					if (obj[14] != null && obj[14].equals("0")) {
						p.put("resale", "不零售");
					} else {
						p.put("resale", "零售");
					}

					array.put(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return array.toString();
		}

		return null;
	}

	private String htmlParse(final String html) {
		if ((html == null) || (html.trim().equals(""))) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		try {
			Parser parser = new Parser();
			parser.setInputHTML(html);
			NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
				public boolean accept(Node node) {
					return true;
				}
			});

			for (int i = 0; i < nodes.size(); i++) {
				Node nodet = nodes.elementAt(i);
				if (nodet instanceof ParagraphTag) {
					sb.append("<br>");
				}

				sb.append(new String(nodet.toPlainTextString()));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
