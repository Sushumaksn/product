package com.example.Product.model;

import java.util.Calendar;
import java.util.Date;

public class Item {

	public String name;
	public Integer sellIn;
	public Integer quality;
	public Date entryDate;
	public Date sellDate;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String name, Integer sellIn, Integer quality) {
		super();
		this.name = name;
		this.sellIn = sellIn;
		this.quality = quality;
		this.entryDate=new Date();
		this.sellDate = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSellIn() {
		return sellIn;
	}

	public void setSellIn(Integer sellIn) {
		this.sellIn = sellIn;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date entryDate) {
		this.sellDate = entryDate;
	}

}