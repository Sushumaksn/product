package com.example.Product.model;

public class QualityDetails {
	public QualityDetails(String name, Integer decrement, Integer decrementBefore10, Integer decrementAfterSellDate,
			boolean zeroAfterSellDate) {
		super();
		this.name = name;
		this.decrement = decrement;
		this.decrementBefore10 = decrementBefore10;
		this.decrementAfterSellDate = decrementAfterSellDate;
		this.zeroAfterSellDate = zeroAfterSellDate;

	}

	private String name;
	private Integer decrement;
	private Integer decrementBefore10;
	private Integer decrementAfterSellDate;
	private boolean zeroAfterSellDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDecrement() {
		return decrement;
	}

	public void setDecrement(Integer decrement) {
		this.decrement = decrement;
	}

	public Integer getDecrementBefore10() {
		return decrementBefore10;
	}

	public void setDecrementBefore10(Integer decrementBefore10) {
		this.decrementBefore10 = decrementBefore10;
	}

	public Integer getDecrementAfterSellDate() {
		return decrementAfterSellDate;
	}

	public void setDecrementAfterSellDate(Integer decrementAfterSellDate) {
		this.decrementAfterSellDate = decrementAfterSellDate;
	}

	public boolean isZeroAfterSellDate() {
		return zeroAfterSellDate;
	}

	public void setZeroAfterSellDate(boolean zeroAfterSellDate) {
		this.zeroAfterSellDate = zeroAfterSellDate;
	}

}
