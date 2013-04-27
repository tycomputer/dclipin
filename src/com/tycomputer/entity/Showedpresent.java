package com.tycomputer.entity;



/**
 * Showedpresent entity. @author MyEclipse Persistence Tools
 */

public class Showedpresent  implements java.io.Serializable {


    // Fields    

     private String uuid;
     private Present present;
     private String type;
     private Integer sn;


    // Constructors

    /** default constructor */
    public Showedpresent() {
    }

	/** minimal constructor */
    public Showedpresent(String uuid, Present present, String type) {
        this.uuid = uuid;
        this.present = present;
        this.type = type;
    }
    
    /** full constructor */
    public Showedpresent(String uuid, Present present, String type, Integer sn) {
        this.uuid = uuid;
        this.present = present;
        this.type = type;
        this.sn = sn;
    }

   
    // Property accessors

    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Present getPresent() {
        return this.present;
    }
    
    public void setPresent(Present present) {
        this.present = present;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
    }
   








}