package com.tycomputer.entity;

import java.util.Calendar;


/**
 * Present entity. @author MyEclipse Persistence Tools
 */

public class Present  implements java.io.Serializable {


    // Fields    

     private String uid;
     private Datacata datacata;
     private Datatype datatype;
     private String pname;
     private Double price;
     private Double inprice;
     private String description;
     private String litpic;
     private String bigpic;
     private String unit;
     private String spec;
     private Integer buynum;
     private String itsize;
     private String packsize;
     private String weight;
     private String material;
     private String words;
     private Integer ordersn;
     private String note;
     private String factory;
     private String resale;
     private String flag;
     private Calendar addData;
     
     //不是数据库属性，只是为了页面显示方便
     private String priceStr;


    // Constructors

    /** default constructor */
    public Present() {
    }

	/** minimal constructor */
    public Present(String uid, Datacata datacata, Datatype datatype, String pname, Double price, String flag, Calendar addData) {
        this.uid = uid;
        this.datacata = datacata;
        this.datatype = datatype;
        this.pname = pname;
        this.price = price;
        this.flag = flag;
        this.addData = addData;
    }
    
    /** full constructor */
    public Present(String uid, Datacata datacata, Datatype datatype, String pname, Double price, Double inprice, String description, String litpic, String bigpic, String unit, String spec, Integer buynum, String itsize, String packsize, String weight, String material, String words, Integer ordersn, String note, String factory, String flag, Calendar addData) {
        this.uid = uid;
        this.datacata = datacata;
        this.datatype = datatype;
        this.pname = pname;
        this.price = price;
        this.inprice = inprice;
        this.description = description;
        this.litpic = litpic;
        this.bigpic = bigpic;
        this.unit = unit;
        this.spec = spec;
        this.buynum = buynum;
        this.itsize = itsize;
        this.packsize = packsize;
        this.weight = weight;
        this.material = material;
        this.words = words;
        this.ordersn = ordersn;
        this.note = note;
        this.factory = factory;
        this.flag = flag;
        this.addData = addData;
    }

   
    // Property accessors

    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }

    public Datacata getDatacata() {
        return this.datacata;
    }
    
    public void setDatacata(Datacata datacata) {
        this.datacata = datacata;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }
    
    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

    public String getPname() {
        return this.pname;
    }
    
    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInprice() {
        return this.inprice;
    }
    
    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLitpic() {
        return this.litpic;
    }
    
    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getBigpic() {
        return this.bigpic;
    }
    
    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpec() {
        return this.spec;
    }
    
    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getBuynum() {
        return this.buynum;
    }
    
    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public String getItsize() {
        return this.itsize;
    }
    
    public void setItsize(String itsize) {
        this.itsize = itsize;
    }

    public String getPacksize() {
        return this.packsize;
    }
    
    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public String getWeight() {
        return this.weight;
    }
    
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return this.material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWords() {
        return this.words;
    }
    
    public void setWords(String words) {
        this.words = words;
    }

    public Integer getOrdersn() {
        return this.ordersn;
    }
    
    public void setOrdersn(Integer ordersn) {
        this.ordersn = ordersn;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    public String getFactory() {
        return this.factory;
    }
    
    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Calendar getAddData() {
        return this.addData;
    }
    
    public void setAddData(Calendar addData) {
        this.addData = addData;
    }

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public String getResale() {
		return resale;
	}

	public void setResale(String resale) {
		this.resale = resale;
	}










}