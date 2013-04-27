package com.tycomputer.manager.service;

import java.util.List;

import com.tycomputer.entity.Datacata;
import com.tycomputer.entity.Datatype;
import com.tycomputer.manager.action.StandDataForm;

public interface IStandDataService {

	/**
	 * 载入所有的数据类别
	 * 
	 * @return
	 */
	public List<Datacata> getAllDatacata();

	/**
	 * 
	 * 功能说明 : 保存数据类别
	 * 
	 * @param form
	 */
	public void saveDatacata(StandDataForm form);

	 /**
	 *
	 * 功能说明 : 删除数据类别
	 *
	 * @param form
	 */
	 public void delDatacata(StandDataForm form);
	
	/**
	 * 
	 * 功能说明 : 根据datatypeid，载入实体类，并存入form中
	 * 
	 * @param form
	 */
	public void loadEntityToForm(StandDataForm form);

	/**
	 * 
	 * 功能说明 : 载入Datacata实体
	 * 
	 * @param form
	 * @return
	 */
	public Datacata loaDatacata(StandDataForm form);

	/**
	 * 
	 * 功能说明 : 保存数据明细
	 * 
	 * @param form
	 */
	public void saveTypes(StandDataForm form);
	
	

}
