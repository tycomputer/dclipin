package com.tycomputer.hj.entity;



/**
 * Datatype entity. @author MyEclipse Persistence Tools
 */

public class Hjdatatype  implements java.io.Serializable {


    // Fields    

     private String typeId;
     private Hjdatacata datacata;
     private String typeName;
     private String typeSort;
     private Integer sn;


    // Constructors

    /** default constructor */
    public Hjdatatype() {
    }

	/** minimal constructor */
    public Hjdatatype(String typeId, String typeName, String typeSort, Integer sn) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeSort = typeSort;
        this.sn = sn;
    }
    
    /** full constructor */
    public Hjdatatype(String typeId, Hjdatacata datacata, String typeName, String typeSort, Integer sn) {
        this.typeId = typeId;
        this.datacata = datacata;
        this.typeName = typeName;
        this.typeSort = typeSort;
        this.sn = sn;
    }

   
    // Property accessors

    public String getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

   

    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeSort() {
        return this.typeSort;
    }
    
    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
    }

	public Hjdatacata getDatacata() {
		return datacata;
	}

	public void setDatacata(Hjdatacata datacata) {
		this.datacata = datacata;
	}
   








}