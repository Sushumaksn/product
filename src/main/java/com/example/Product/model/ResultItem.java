package com.example.Product.model;

import java.util.Calendar;
import java.util.Date;

public class ResultItem {

	public String name;
	public Integer sellIn;
	public Integer quality;

	public ResultItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultItem(String name, Integer sellIn, Integer quality) {
		super();
		this.name = name;
		this.sellIn = sellIn;
		this.quality = quality;
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
}