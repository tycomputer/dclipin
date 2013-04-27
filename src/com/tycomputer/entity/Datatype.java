package com.tycomputer.entity;



/**
 * Datatype entity. @author MyEclipse Persistence Tools
 */

public class Datatype  implements java.io.Serializable {


    // Fields    

     private String typeId;
     private Datacata datacata;
     private String typeName;
     private String typeSort;
     private Integer sn;


    // Constructors

    /** default constructor */
    public Datatype() {
    }

	/** minimal constructor */
    public Datatype(String typeId, String typeName, String typeSort, Integer sn) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeSort = typeSort;
        this.sn = sn;
    }
    
    /** full constructor */
    public Datatype(String typeId, Datacata datacata, String typeName, String typeSort, Integer sn) {
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

    public Datacata getDatacata() {
        return this.datacata;
    }
    
    public void setDatacata(Datacata datacata) {
        this.datacata = datacata;
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
   








}