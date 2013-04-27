/**
 * 
 */
package com.tycomputer.manager.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.web.CacheData;
import com.tycomputer.entity.Datacata;
import com.tycomputer.entity.Datatype;
import com.tycomputer.manager.action.StandDataForm;

/**
 * @author zhangliuhua
 * 
 */

@SuppressWarnings("unchecked")
public class StandDataServiceImpl implements IStandDataService {

	private HibernateTemplate dao;

	

	//@Override
	public List<Datacata> getAllDatacata() {
		return dao.find("from Datacata t order by t.cataSn");
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.base.IStandDataManager#saveDataType(com.tycomputer.base
	 * .StandDataForm)
	 */
	//@Override
	public void saveDatacata(StandDataForm form) {
		Datacata datacata = null;
		if ((form.getCataId() != null) && (!form.getCataId().trim().equals(""))) {
			Object obj = dao.load(Datacata.class, form.getCataId());
			if ((obj != null)) {
				datacata = (Datacata) obj;
			}
		}

		if (datacata != null) {
			//datacata.setCataSort(form.getCataShort().trim().toUpperCase());
			datacata.setCataName(form.getCataName().trim());
			datacata.setFlag(form.getFlag());
			datacata.setCataSn(form.getCataSn());
			dao.update(datacata);

		} else {
			datacata = new Datacata();
			datacata.setCataId(getMaxCataId());
			datacata.setCataSort(form.getCataShort().trim().toUpperCase());
			datacata.setCataName(form.getCataName().trim());
			datacata.setFlag(form.getFlag());
			datacata.setCataSn(form.getCataSn());
			dao.save(datacata);
		}
		//cacheData.cache();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.base.IStandDataManager#loadEntityToForm(com.tycomputer
	 * .base.StandDataForm)
	 */
	//@Override
	public void loadEntityToForm(StandDataForm form) {

		Object obj = dao.load(Datacata.class, form.getCataId());
		if (obj != null) {
			Datacata cata = (Datacata) obj;
			form.setCataId(cata.getCataId());
			form.setCataName(cata.getCataName());
			form.setCataShort(cata.getCataSort());
			form.setFlag(cata.getFlag());
			form.setCataSn(cata.getCataSn());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.base.IStandDataManager#delDataType(com.tycomputer.base
	 * .StandDataForm)
	 */
	//@Override
	public void delDatacata(StandDataForm form) {
		Object obj = dao.load(Datacata.class, form.getCataId());
		if (obj != null) {
			dao.delete(obj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.base.IStandDataManager#loaDatatype(com.tycomputer.base
	 * .StandDataForm)
	 */
	//@Override
	public Datacata loaDatacata(StandDataForm form) {
		Object obj = dao.load(Datacata.class, form.getCataId());
		if (obj != null) {
			Datacata datacata = (Datacata) obj;
			return datacata;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tycomputer.base.IStandDataManager#saveDeta(com.tycomputer.base.
	 * StandDataForm)
	 */
	//@Override
	public void saveTypes(StandDataForm form) {
		Datacata cata = loaDatacata(form);

		if (cata != null) {
			int max = getMaxTypeId(cata.getCataId());

			String[] typeIds = form.getTypeId();
			Integer[] sns = form.getSn();
			String[] typeShort = form.getTypeSort();
			String[] typeNames = form.getTypeName();
			for (int i = 0; i < typeIds.length; i++) {
				Datatype type = null;
				if ((typeIds[i] == null) || (typeIds[i].trim().equals(""))) {
					type = new Datatype();
					type.setSn(sns[i]);
					type.setTypeSort(typeShort[i].trim().toUpperCase());
					type.setTypeName(typeNames[i].trim());
					max++;
					String maxStr = max < 10 ? "000" + max : (max <100? "00" + max : (max<1000? "0" + max : "" + max));
					type.setTypeId(cata.getCataId() + maxStr);
					type.setDatacata(cata);
					cata.getDatatypes().add(type);
				} else {
					Iterator<Datatype> iter = cata.getDatatypes().iterator();
					while (iter.hasNext()) {
						Datatype datatype = (Datatype) iter.next();
						if (datatype.getTypeId().equals(typeIds[i])){
							datatype.setTypeSort(typeShort[i].trim().toUpperCase());
							datatype.setTypeName(typeNames[i].trim());
							datatype.setSn(sns[i]);
							break;
						}
						
					}
				}
			}
			dao.update(cata);
		}
	}

	private String getMaxCataId() {
		int max = 0;
		List l = dao.find("select max(t.cataId) from Datacata t");
		if ((l != null) && (l.size() > 0)) {
			String cataId = (String) l.get(0);
			if (cataId != null){
				max = Math.max(max, Integer.parseInt(cataId));
			}
			
		}
		max++;
		return max < 10 ? "0" + max : "" + max;
	}

	private int getMaxTypeId(String cataId) {
		int max = 0;
		List l = dao.find("select max(t.typeId) from Datatype t where t.datacata.cataId=?",new String[]{cataId});
		if ((l != null) && (l.size() > 0)) {
			String typeId = (String) l.get(0);
			if (typeId != null){
				typeId = typeId.substring(2);
				max = Math.max(max, Integer.parseInt(typeId));
			}
			
		}
		
		return max;
	}



	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}
}
