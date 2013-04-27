package com.tycomputer.manager.action;

import java.util.List;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.common.web.CacheData;
import com.tycomputer.entity.Datacata;
import com.tycomputer.manager.service.IStandDataService;

public class StandDataAction extends BaseAction {

	private static final long serialVersionUID = -7862844717902946501L;

	private IStandDataService standDataService;

	private StandDataForm form;

	private CacheData cacheData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tycomputer.common.web.BaseAction#execute()
	 */
	@Override
	public String execute() throws Exception {
		List<Datacata> l = standDataService.getAllDatacata();

		getRequest().setAttribute("datacata", l);
		return "datatypelist";
	}

	/**
	 * 添加目录
	 */
	public String addDatacata() throws Exception {

		// request.setAttribute("datatypes", standDataService.getAllDataType());
		form = new StandDataForm();
		form.setCataShort(null);

		getRequest().setAttribute("form", form);

		return "addDatatype";
	}

	/**
	 * 修改目录
	 */
	public String editDatacata() throws Exception {

		standDataService.loadEntityToForm(form);

		return "addDatatype";
	}

	/**
	 * 删除数据类别
	 */
	public String delDatacata() throws Exception {
		standDataService.delDatacata(form);
		setMessage("删除礼品目录成功！！");
		cacheData.cache();
		return execute();
	}

	/**
	 * 保存数据类别
	 */
	public String saveDatacata() throws Exception {
		standDataService.saveDatacata(form);
		cacheData.cache();
		setMessage("添加礼品目录成功！！");
		return execute();
	}

	/**
	 * 进入数据明细
	 */
	public String toDetaList() throws Exception {
		Object obj = standDataService.loaDatacata(form);

		getRequest().setAttribute("datacata", obj);
		return "detaList";
	}

	/**
	 * 保存数据明细
	 */
	public String saveDeta() throws Exception {

		standDataService.saveTypes(form);

		setMessage("礼品类别保存成功！");
		cacheData.cache();
		return execute();
	}

	public void setStandDataService(IStandDataService standDataService) {
		this.standDataService = standDataService;
	}

	public StandDataForm getForm() {
		return form;
	}

	public void setForm(StandDataForm form) {
		this.form = form;
	}

	public void setCacheData(CacheData cacheData) {
		this.cacheData = cacheData;
	}

}
