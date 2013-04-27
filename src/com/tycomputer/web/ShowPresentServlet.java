/*
 * @(#)GetPresentServlet.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.SpringUtil;

/**
 * 日期 : 2010-5-16<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dong<br>
 * 功能 : <br>
 */
public class ShowPresentServlet extends HttpServlet {

	private static final long serialVersionUID = -1818952156767898161L;

	private static final String BEAN_IN_SESSION_NAME = "__bean";

	private static final int pageSize = 30;

	private ShowPresentBean bean;

	private HibernateTemplate dao;

	private void getBean(HttpServletRequest req) {
		String tid = req.getParameter("t");
		if (tid == null) {
			// 如果没有带查询条件，从Session 找,找不到new
			bean = (ShowPresentBean) req.getSession().getAttribute(BEAN_IN_SESSION_NAME);
			if (bean == null) {
				bean = new ShowPresentBean();
				bean.setTid(null);
				bean.setDid(null);
				bean.setPid(null);
				bean.setWords(null);
			}
		} else {
			String did = req.getParameter("d");
			String pid = req.getParameter("p");
			String words = req.getParameter("w");
			if ((tid == null) || (tid.equals("all"))) {
				tid = null;
				did = null;
			}
			if ((did == null) || (did.equals("all"))) {
				did = null;
			}
			if ((pid == null) || (pid.equals("0"))) {
				pid = null;
			} else if (pid.equals("1")) {
				pid = " and t.price<=5.0";
			} else if (pid.equals("2")) {
				pid = " and t.price>=5.0 and t.price<=10.0";
			} else if (pid.equals("3")) {
				pid = " and t.price>=10.0 and t.price<=20.0";
			} else if (pid.equals("4")) {
				pid = " and t.price>=20.0 and t.price<=30.0";
			} else if (pid.equals("5")) {
				pid = " and t.price>=30.0 and t.price<=50.0";
			} else if (pid.equals("6")) {
				pid = " and t.price>=50.0 and t.price<=100.0";
			} else if (pid.equals("7")) {
				pid = " and t.price>=100.0 and t.price<=200.0";
			} else if (pid.equals("8")) {
				pid = " and t.price>=200.0 and t.price<=500.0";
			} else if (pid.equals("9")) {
				pid = " and t.price>=500.0";
			}
			bean = new ShowPresentBean();
			bean.setTid(tid);
			bean.setDid(did);
			bean.setPid(pid);
			bean.setWords(words);
		}
		if (bean.getSql() == null) {
			StringBuffer sb = new StringBuffer("select t.uid,t.pname,t.price,t.litpic from Present t where t.flag='0' ");

			if ((bean.getDid() != null) && (!bean.getDid().equals(""))) {
				sb.append(" and t.datatype.typeId='").append(bean.getDid()).append("' ");
			} else if ((bean.getTid() != null) && (!bean.getTid().equals(""))) {
				sb.append(" and t.datacata.cataId='").append(bean.getTid()).append("' ");
			}
			if ((bean.getPid() != null) && (!bean.getPid().equals(""))) {
				sb.append(bean.getPid());
			}

			if ((bean.getWords() != null) && (!bean.getWords().trim().equals(""))) {
				String[] w = bean.getWords().trim().split(" ");
				for (int i = 0; i < w.length; i++) {
					if ((w[i] != null) && (!w[i].trim().equals(""))) {
						sb.append(" and (t.pname like '%").append(w[i]).append("%' or t.words like '%").append(w[i]).append("%' or t.description like '%")
								.append(w[i]).append("%') ");
					}
				}
			}

			if (((bean.getTid() != null) && (!bean.getTid().equals(""))) || ((bean.getTid() != null) && (!bean.getTid().equals("")))) {
				sb.append(" order by t.ordersn,t.addData desc");
			} else {
				sb.append(" order by t.addData desc");
			}

			bean.setSql(sb.toString());
			// bean.setTotalPage(countRec % pageSize == 0 ? countRec / pageSize
			// : countRec / pageSize + 1);
			req.getSession().setAttribute(BEAN_IN_SESSION_NAME, bean);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 第几页
		String pageStr = req.getParameter("page");
		int page = 1;
		try {
			if (pageStr != null && (!pageStr.trim().equals(""))) {
				page = Integer.parseInt(pageStr);
			}
		} catch (Exception e) {
			page = 1;
		}

		getBean(req);
		dao = (HibernateTemplate) SpringUtil.getBean("dao");
		if (dao == null) {
			return;
		}
		List l = this.getPageData(page);
		JSONObject jsonObject = new JSONObject();
		JSONArray arr = new JSONArray();
		try {
			for (int i = 0; ((l != null) && (i < l.size())); i++) {
				Object[] obj = (Object[]) l.get(i);
				JSONObject json = new JSONObject();
				json.put("i", obj[0]);
				json.put("n", obj[1]);
				// json.put("c", obj[2]);
				double d = (Double) obj[2];
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
				json.put("p", df.format(d));
				json.put("l", obj[3]);
				arr.put(json);
			}
			jsonObject.put("data", arr);
			jsonObject.put("nav", getPageNavigator(page));

		} catch (Exception e) {
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(jsonObject.toString());
	}

	/**
	 * 
	 * @return html上显示的分页导行字符串
	 */
	public String getPageNavigator(int page) {
		StringBuffer sb = new StringBuffer();
		// sb.append("页次:<font color='red'>").append((page)).append("</font>/").
		// append(bean.getTotalPage()).append(
		// " 每页:<font color='red'>").append(pageSize).append(
		// "</font> 共计:<font color='red'>").append(bean.getCountRec())
		// .append("</font>条记录");
		if (bean.getTotalPage() <= 0) {
			sb.append("一共有0个礼品");
			return sb.toString();
		}

		if (page > 1) {
			sb.append("<a href='#' onclick=\"goPage(1);return false;\" title='转到首页'>首页</a>&nbsp;&nbsp;&nbsp;");
			if (page > 2) {
				sb.append("<a href='#' onclick=\"goPage(" + (page - 1) + ");return false;\" title='转到上一页'>上一页</a>&nbsp;&nbsp;&nbsp;");
			}
		}
		if (bean.getTotalPage() <= 10) {
			// 总数 小于10个全部列
			for (int i = 1; i < bean.getTotalPage() + 1; i++) {
				if (page == i) {
					sb.append((i)).append("&nbsp;&nbsp;&nbsp;");
				} else {
					sb.append(genA(i)).append("&nbsp;&nbsp;&nbsp;");
				}
			}
		} else {
			// 总数 大于10个
			for (int i = ((page - 5 > 0 ? (page - 5) : 1)); (i <= page + 5 && (i <= bean.getTotalPage())); i++) {
				if (page == i) {
					sb.append((i)).append("&nbsp;&nbsp;&nbsp;");
				} else {
					sb.append(genA(i)).append("&nbsp;&nbsp;&nbsp;");
				}

			}
		}
		if (page < bean.getTotalPage()) {
			sb.append("<a href='#' onclick=\"goPage(" + (page + 1) + ");return false;\" title='转到下一页'>下一页</a>&nbsp;&nbsp;&nbsp;");

		}
		sb.append("<select id='sel' onchange=\"goPage();\">");

		for (int i = 1; i < bean.getTotalPage() + 1; i++) {
			sb.append("<option value='").append(i).append("'");
			if (i == page) {
				sb.append(" selected ");
			}
			sb.append(">第").append(i).append("页</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	/**
	 * 
	 * @param i
	 * @return 生成超链接 <a></a>
	 */
	public String genA(int i) {
		return "<a href='#' onclick=\"goPage(" + (i) + ");return false;\" title='转到第" + (i) + "页'>" + (i) + "</a>";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private List getPageData(final int page) {

		List list = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(bean.getSql());

				ScrollableResults sr = query.scroll();
				sr.last();
				int totalCount = sr.getRowNumber();
				totalCount++;
				bean.setCountRec(totalCount);
				int totalPage = totalCount / pageSize; // 总页数
				if (totalCount % pageSize != 0) {
					totalPage++;
				}
				bean.setTotalPage(totalPage);
				query.setFirstResult(pageSize * (page - 1));
				query.setMaxResults(pageSize);
				List list = query.list();

				return list;
			}
		});
		return list;
	}

}
