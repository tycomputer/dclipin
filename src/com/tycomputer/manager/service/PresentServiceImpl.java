/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.manager.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.util.ImageUtils;
import com.tycomputer.common.web.CacheData;
import com.tycomputer.entity.Datacata;
import com.tycomputer.entity.Datatype;
import com.tycomputer.entity.Present;
import com.tycomputer.manager.action.PresentForm;

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
		Present present = null;
		Datatype datatype = (Datatype) dao.load(Datatype.class, form.getTypeId());
		if (datatype == null) {
			return null;
		}
		String[] picnames = null;
		if (pic != null) {
			picnames = ImageUtils.pressResizeImg(path + "images/p/", fileName.substring(fileName.lastIndexOf('.')), pic,form.getColor(),true);
		}
		if ((id != null) && (!id.equals(""))) {
			Present temp = (Present) dao.load(Present.class, id);
			// 更换小图片，设置要删除旧的小图片
			if ((picnames != null) && (picnames.length == 2) && (temp.getBigpic() != null) && (!temp.getBigpic().equals(""))) {
				this.delPic(path + "images/p/", temp.getBigpic());
				this.delPic(path + "images/p/", temp.getLitpic());
			}
			// 改变目录或类别
			if (!form.getTypeId().equals(temp.getDatatype().getTypeId())) {
				dao.delete(temp);				
			} else {
				present = temp;
				isnew = false;
			}

		}
		if (present == null) {
			present = new Present();
			present.setDatatype(datatype);
			present.setDatacata(datatype.getDatacata());
			present.setUid(getMaxCode(datatype));
		}
		present.setPname(form.getPname());
		present.setPrice(form.getPrice());
		present.setInprice(form.getInprice());
		present.setBuynum(form.getBuynum());
		present.setDescription(form.getDescription());
		if (picnames != null && (picnames.length == 2)) {
			present.setBigpic(picnames[0]);
			present.setLitpic(picnames[1]);
		}
		present.setFlag(form.getFlag());
		present.setResale(form.getResale());
		present.setUnit(form.getUnit());
		present.setSpec(form.getSpec());
		present.setItsize(form.getItsize());
		present.setPacksize(form.getPacksize());
		present.setWeight(form.getWeight());
		present.setMaterial(form.getMaterial());
		present.setWords(form.getWords());
		present.setOrdersn(form.getOrdersn());
		present.setNote(form.getNote() == null ? null : (form.getNote().length() > 50 ? form.getNote().substring(0, 50) : form.getNote()));

		present.setAddData(DateUtil.getSysCalendar());

		if (isnew) {
			dao.save(present);
		} else {
			dao.update(present);
		}
		return present.getUid();

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
		Present present = (Present) dao.load(Present.class, form.getUid());
		if (present != null) {
			form.setPname(present.getPname());
			form.setTypeId(present.getDatatype().getTypeId());
			form.setCataId(present.getDatacata().getCataId());

			form.setPrice(present.getPrice());
			form.setInprice(present.getInprice());
			form.setBuynum(present.getBuynum());
			form.setDescription(present.getDescription() == null ? "" : present.getDescription());
			form.setLitpic(present.getLitpic());
			form.setBigpic(present.getBigpic());
			form.setFlag(present.getFlag());
			form.setResale(present.getResale());
			form.setUnit(present.getUnit());
			form.setSpec(present.getSpec());
			form.setItsize(present.getItsize());
			form.setPacksize(present.getPacksize());
			form.setWeight(present.getWeight());
			form.setMaterial(present.getMaterial());
			form.setWords(present.getWords());
			form.setOrdersn(present.getOrdersn());
			form.setNote(present.getNote());
			form.setBuynum(present.getBuynum());
		}

	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see com.tycomputer.web.IPresentManager#getAllPhoto()
	// */
	// //@Override
	// public List getAllPhoto() {
	//
	// return dao.getList(Present.class);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tycomputer.web.IPresentManager#getDataDetailJson()
	 */
	//@Override
	public List<String[]> getDataDetail() {
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < CacheData.DATACATA.size(); i++) {
			Datacata cata = CacheData.DATACATA.get(i);
			Iterator<Datatype> iter = cata.getDatatypes().iterator();
			while (iter.hasNext()) {
				Datatype type = (Datatype) iter.next();
				String[] str = new String[3];
				str[0] = cata.getCataId();
				str[1] = type.getTypeId();
				str[2] = type.getTypeName();
				result.add(str);
			}
		}
		return result;
	}

	private String getMaxCode(Datatype type) {
		int maxid = 0;
		@SuppressWarnings("rawtypes")
		List l = dao.find("select max(t.uid) from Present t where t.datatype=?", new Object[] { type });
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
	// obj[0] = CacheData.getNameOfDatatype(obj[0].toString());
	// obj[1] = CacheData.getNameOfDatadeta(obj[1].toString());
	// obj[5] = ((Boolean) obj[5]) ? "启用" : "停产";
	// }
	// return list;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.IPresentManager#delOldImg(com.tycomputer.web.PresentForm
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
	 * com.tycomputer.web.IPresentManager#getQuerySQL(com.tycomputer.web.PresentForm
	 * )
	 */
	//@Override
	public String getQuerySQL(PresentForm form) {
		if (form == null) {
			form = new PresentForm();
		}
		StringBuffer sb = new StringBuffer();
		boolean where = false;
		sb.append("select t.datacata.cataName,t.datatype.typeName,t.pname,t.price,t.inprice,t.flag,t.resale,t.words,t.ordersn,t.uid from Present t");
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
	 * com.tycomputer.web.IPresentManager#delPresent(com.tycomputer.web.PresentForm
	 * )
	 */
	//@Override
	public void delPresent(PresentForm form) {
		Present present = (Present) dao.load(Present.class, form.getUid());
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
