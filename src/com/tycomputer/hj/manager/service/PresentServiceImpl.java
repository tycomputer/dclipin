/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.manager.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.util.ImageUtils;
import com.tycomputer.common.web.CacheData;
import com.tycomputer.hj.entity.Hjdatacata;
import com.tycomputer.hj.entity.Hjdatatype;
import com.tycomputer.hj.entity.Hjpresent;
import com.tycomputer.hj.manager.action.PresentForm;


/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站技术文章管理 managerImpl<br>
 */
public class PresentServiceImpl implements IPresentService {

	private HibernateTemplate dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tycomputer.web.ITechartiManager#saveTecharti(com.tycomputer.web.
	 * TechartiForm)
	 */
	//@Override
	public String savePresent(PresentForm form, File pic, String path, String fileName) {
		String id = form.getUid();
		boolean isnew = true;
		Hjpresent Hjpresent = null;
		Hjdatatype Hjdatatype = (Hjdatatype) dao.load(Hjdatatype.class, form.getTypeId());
		if (Hjdatatype == null) {
			return null;
		}
		String[] picnames = null;
		if (pic != null) {
			picnames = ImageUtils.pressResizeImg(path + "images/h/", fileName.substring(fileName.lastIndexOf('.')), pic,form.getColor(),false);
		}
		if ((id != null) && (!id.equals(""))) {
			Hjpresent temp = (Hjpresent) dao.load(Hjpresent.class, id);
			// 更换小图片，设置要删除旧的小图片
			if ((picnames != null) && (picnames.length == 2) && (temp.getBigpic() != null) && (!temp.getBigpic().equals(""))) {
				this.delPic(path + "images/h/", temp.getBigpic());
				this.delPic(path + "images/h/", temp.getLitpic());
			}
			// 改变目录或类别
			if (!form.getTypeId().equals(temp.getDatatype().getTypeId())) {
				dao.delete(temp);				
			} else {
				Hjpresent = temp;
				isnew = false;
			}

		}
		if (Hjpresent == null) {
			Hjpresent = new Hjpresent();
			Hjpresent.setDatatype(Hjdatatype);
			Hjpresent.setDatacata(Hjdatatype.getDatacata());
			Hjpresent.setUid(getMaxCode(Hjdatatype));
		}
		Hjpresent.setPname(form.getPname());
		Hjpresent.setPrice(0.0);
		Hjpresent.setInprice(0.0);
		Hjpresent.setBuynum(1);
		Hjpresent.setDescription(form.getDescription());
		if (picnames != null && (picnames.length == 2)) {
			Hjpresent.setBigpic(picnames[0]);
			Hjpresent.setLitpic(picnames[1]);
		}
		Hjpresent.setFlag(form.getFlag());
		Hjpresent.setResale("0");
		Hjpresent.setUnit(form.getUnit());
		Hjpresent.setSpec(form.getSpec());
		Hjpresent.setItsize(form.getItsize());
		Hjpresent.setPacksize(form.getPacksize());
		Hjpresent.setWeight(form.getWeight());
		Hjpresent.setMaterial(form.getMaterial());
		Hjpresent.setWords(form.getWords());
		Hjpresent.setOrdersn(form.getOrdersn());
		Hjpresent.setNote(form.getNote() == null ? null : (form.getNote().length() > 50 ? form.getNote().substring(0, 50) : form.getNote()));

		Hjpresent.setAddData(DateUtil.getSysCalendar());

		if (isnew) {
			dao.save(Hjpresent);
		} else {
			dao.update(Hjpresent);
		}
		return Hjpresent.getUid();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.ITechartiManager#loadEntityAndSetFrom(com.tycomputer
	 * .web.TechartiForm)
	 */
	//@Override
	public void loadEntityAndSetFrom(PresentForm form) {
		Hjpresent Hjpresent = (Hjpresent) dao.load(Hjpresent.class, form.getUid());
		if (Hjpresent != null) {
			form.setPname(Hjpresent.getPname());
			form.setTypeId(Hjpresent.getDatatype().getTypeId());
			form.setCataId(Hjpresent.getDatacata().getCataId());

			form.setPrice(Hjpresent.getPrice());
			form.setInprice(Hjpresent.getInprice());
			form.setBuynum(Hjpresent.getBuynum());
			form.setDescription(Hjpresent.getDescription() == null ? "" : Hjpresent.getDescription());
			form.setLitpic(Hjpresent.getLitpic());
			form.setBigpic(Hjpresent.getBigpic());
			form.setFlag(Hjpresent.getFlag());
			form.setResale(Hjpresent.getResale());
			form.setUnit(Hjpresent.getUnit());
			form.setSpec(Hjpresent.getSpec());
			form.setItsize(Hjpresent.getItsize());
			form.setPacksize(Hjpresent.getPacksize());
			form.setWeight(Hjpresent.getWeight());
			form.setMaterial(Hjpresent.getMaterial());
			form.setWords(Hjpresent.getWords());
			form.setOrdersn(Hjpresent.getOrdersn());
			form.setNote(Hjpresent.getNote());
			form.setBuynum(Hjpresent.getBuynum());
		}

	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see com.tycomputer.web.IHjpresentManager#getAllPhoto()
	// */
	// //@Override
	// public List getAllPhoto() {
	//
	// return dao.getList(Hjpresent.class);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tycomputer.web.IHjpresentManager#getDataDetailJson()
	 */
	//@Override
	public List<String[]> getDataDetail() {
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < CacheData.HJDATACATA.size(); i++) {
			Hjdatacata cata = CacheData.HJDATACATA.get(i);
			Iterator<Hjdatatype> iter = cata.getDatatypes().iterator();
			while (iter.hasNext()) {
				Hjdatatype type = (Hjdatatype) iter.next();
				String[] str = new String[3];
				str[0] = cata.getCataId();
				str[1] = type.getTypeId();
				str[2] = type.getTypeName();
				result.add(str);
			}
		}
		return result;
	}

	private String getMaxCode(Hjdatatype type) {
		int maxid = 0;
		@SuppressWarnings("rawtypes")
		List l = dao.find("select max(t.uid) from Hjpresent t where t.datatype=?", new Object[] { type });
		if ((l != null) && (l.size() > 0)) {
			String maxUid = (String) l.get(0);
			if (maxUid != null) {
				try {
					maxUid = maxUid.substring(6, 9);
					maxid = Integer.parseInt(maxUid);
				} catch (Exception e) {
				}
			}
		}
		maxid++;
		String maxStr = maxid < 10 ? "00" + maxid : (maxid < 100 ? "0" + maxid : "" + maxid);

		return type.getDatacata().getCataSort() + "-" + type.getTypeSort() + "-" + maxStr;
	}

	// /**
	// *
	// * 功能说明 : 转换查询出的数据，转换，礼品目录、类别、标志
	// *
	// * @param list
	// * @return
	// */
	// @SuppressWarnings("unchecked")
	// public List transform(List list) {
	// if ((list == null) || (list.size() == 0)) {
	// return new ArrayList();
	// }
	// for (int i = 0; i < list.size(); i++) {
	// Object[] obj = (Object[]) list.get(i);
	// obj[0] = CacheData.getNameOfHjdatatype(obj[0].toString());
	// obj[1] = CacheData.getNameOfDatadeta(obj[1].toString());
	// obj[5] = ((Boolean) obj[5]) ? "启用" : "停产";
	// }
	// return list;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IHjpresentManager#delOldImg(com.tycomputer.web.PresentForm
	 * )
	 */
	// //@Override
	// public void delOldImg(String imgId) {
	// if ((imgId != null) && (!imgId.equals(""))) {
	// Img lit = (Img) dao.load(Img.class, imgId);
	// dao.delete(lit);
	// }
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IHjpresentManager#getQuerySQL(com.tycomputer.web.PresentForm
	 * )
	 */
	//@Override
	public String getQuerySQL(PresentForm form) {
		if (form == null) {
			form = new PresentForm();
		}
		StringBuffer sb = new StringBuffer();
		boolean where = false;
		sb.append("select t.datacata.cataName,t.datatype.typeName,t.pname,t.flag,t.ordersn,t.uid from Hjpresent t");
		// sb.append(" where  price >= 5.0 && price <= 30.00");
		if ((form.getCataId() != null) && (!form.getCataId().equals(""))) {
			sb.append(" where t.datacata.cataId='").append(form.getCataId()).append("'");
			where = true;
		}
		if ((form.getTypeId() != null) && (!form.getTypeId().equals(""))) {
			if (!where) {
				sb.append(" where ");
				where = true;
			} else {
				sb.append(" and ");
			}
			sb.append("t.datatype.typeId='").append(form.getTypeId()).append("'");

		}

		if ((form.getPname() != null) && (!form.getPname().trim().equals(""))) {
			if (!where) {
				sb.append(" where ");
				where = true;
			} else {
				sb.append(" and ");
			}
			sb.append("pname like '").append(form.getPname().trim()).append("'");
		}
		if (form.getOrder() == null || (form.getOrder().equals(""))) {
			form.setOrder("addData desc");
		}
		sb.append(" order by t.").append(form.getOrder());
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IHjpresentManager#delHjpresent(com.tycomputer.web.PresentForm
	 * )
	 */
	//@Override
	public void delPresent(PresentForm form) {
		Hjpresent present = (Hjpresent) dao.load(Hjpresent.class, form.getUid());
		if (present != null) {
			dao.delete(present);
		}

	}

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

	/**
	 * 
	 * 功能说明 : 删除文件
	 * 
	 * @param path
	 * @param picName
	 */
	private void delPic(String path, String picName) {
		File file = new File(path, picName);
		if (file.exists()) {
			file.delete();
		}

	}

	

}
