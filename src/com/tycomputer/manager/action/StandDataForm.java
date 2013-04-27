package com.tycomputer.manager.action;


public class StandDataForm  {

	private String cataId;// 目录编号
	private String cataName;// 目录名称
	private String cataShort;//目录简写
	private Integer cataSn;//目录简写
	private String flag;// 类别是否启用
	private String[] typeId; // 数据ID
	private Integer[] sn;// 数据sn
	private String[] typeName;// 数据名称
	private String[] typeSort;// 数据简写


	
	
	public String getCataShort() {
		return cataShort;
	}
	public void setCataShort(String cataShort) {
		this.cataShort = cataShort;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String[] getTypeId() {
		return typeId;
	}
	public void setTypeId(String[] typeId) {
		this.typeId = typeId;
	}
	public Integer[] getSn() {
		return sn;
	}
	public void setSn(Integer[] sn) {
		this.sn = sn;
	}
	public String[] getTypeName() {
		return typeName;
	}
	public void setTypeName(String[] typeName) {
		this.typeName = typeName;
	}
	
	public String getCataId() {
		return cataId;
	}
	public void setCataId(String cataId) {
		this.cataId = cataId;
	}
	public String getCataName() {
		return cataName;
	}
	public void setCataName(String cataName) {
		this.cataName = cataName;
	}
	public Integer getCataSn() {
		return cataSn;
	}
	public void setCataSn(Integer cataSn) {
		this.cataSn = cataSn;
	}
	public String[] getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(String[] typeSort) {
		this.typeSort = typeSort;
	}

}
