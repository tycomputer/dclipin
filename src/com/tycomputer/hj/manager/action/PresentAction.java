/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.hj.manager.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.hj.manager.service.IPresentService;
/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站技术文章管理 controller<br>
 */
public class PresentAction extends BaseAction {

	private IPresentService hjpresentService;
	
	
    private String picFileName;
	
	/**
	 * 大图片
	 */
	private File pic;
	
	/**
	 * form
	 */
	private PresentForm form;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.common.web.MultiFormActionController#initMethod(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public String execute() throws Exception{
		setRequest("datadeta", this.hjpresentService.getDataDetail() );
		setRequest("sql", hjpresentService.getQuerySQL(form));

		return "list";
	}

	public String addPresent() throws Exception {
		form = new PresentForm();
		setRequest("datadeta", this.hjpresentService.getDataDetail() );
		
		form.setFlag("0");
		form.setOrdersn(new Integer(500));
		form.setBuynum(new Integer(0));
		//form.setOrdersn()
		form.setDescription("");
		return "add";
	}

	public String toEdit() throws Exception {
		setRequest("datadeta", this.hjpresentService.getDataDetail() );
		//PresentForm form = (PresentForm) actionForm;
		this.hjpresentService.loadEntityAndSetFrom(form);

		return "add";
	}
	
	public String toDele() throws Exception {
		
		this.hjpresentService.delPresent(form);

		form.setTypeId(null);
		form.setCataId(null);
		form.setPname(null);
		return execute();
	}
	


	public String savePresent() throws Exception {	
		String uid = hjpresentService.savePresent(form,pic,ServletActionContext.getServletContext().getRealPath("/"),picFileName);
		
		form.setTypeId(null);
		form.setCataId(null);
		form.setPname(null);
		if (uid != null){
			setMessage("添加或修改货架成功！");
			setRequest("myuid",uid);
			return execute();
		} else {
			setMessage("出错了，请检查或联系tycomputer！");
			return "add";
		}
		
		
	}

	

	public PresentForm getForm() {
		return form;
	}

	public void setForm(PresentForm form) {
		this.form = form;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}



	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public IPresentService getHjpresentService() {
		return hjpresentService;
	}

	public void setHjpresentService(IPresentService hjpresentService) {
		this.hjpresentService = hjpresentService;
	}

	
	
}
