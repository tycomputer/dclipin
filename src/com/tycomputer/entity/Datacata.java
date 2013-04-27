package com.tycomputer.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Datacata entity. @author MyEclipse Persistence Tools
 */

public class Datacata  implements java.io.Serializable {


    // Fields    

     private String cataId;
     private String cataName;
     private String cataSort;
     private Integer cataSn;
     private String flag;
     private Set<Datatype> datatypes = new HashSet<Datatype>(0);


    // Constructors

    /** default constructor */
    public Datacata() {
    }

	/** minimal constructor */
    public Datacata(String cataId, String cataName, String cataSort, Integer cataSn, String flag) {
        this.cataId = cataId;
        this.cataName = cataName;
        this.cataSort = cataSort;
        this.cataSn = cataSn;
        this.flag = flag;
    }
    
    /** full constructor */
    public Datacata(String cataId, String cataName, String cataSort, Integer cataSn, String flag, Set<Datatype> datatypes) {
        this.cataId = cataId;
        this.cataName = cataName;
        this.cataSort = cataSort;
        this.cataSn = cataSn;
        this.flag = flag;
        this.datatypes = datatypes;
    }

   
    // Property accessors

    public String getCataId() {
        return this.cataId;
    }
    
    public void setCataId(String cataId) {
        this.cataId = cataId;
    }

    public String getCataName() {
        return this.cataName;
    }
    
    public void setCataName(String cataName) {
        this.cataName = cataName;
    }

    public String getCataSort() {
        return this.cataSort;
    }
    
    public void setCataSort(String cataSort) {
        this.cataSort = cataSort;
    }

    public Integer getCataSn() {
        return this.cataSn;
    }
    
    public void setCataSn(Integer cataSn) {
        this.cataSn = cataSn;
    }

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Set<Datatype> getDatatypes() {
        return this.datatypes;
    }
    
    public void setDatatypes(Set<Datatype> datatypes) {
        this.datatypes = datatypes;
    }
   








}