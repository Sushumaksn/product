package com.example.Product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.Product.model.Item;
import com.example.Product.model.QualityDetails;
import com.example.Product.model.ResultItem;
import com.example.Product.repository.ProductData;

@Service
public class ProductService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void addProduct(Item item) {
		item.setEntryDate(new Date());

		Date sellInDate = new Date();
		sellInDate.setDate(sellInDate.getDate() + item.getSellIn());
		item.setSellDate(sellInDate);
		ProductData.getProductsList().add(item);
	}

	public List<ResultItem> getProductList(Date reportDate) {
		List<ResultItem> result = new ArrayList();
		for (Item item : ProductData.getProductsList()) {
			result.add(getProcessedItem(item, reportDate));
		}

		return result;
	}

	public static int getDateDiff(Date date1, Date date2) {// long diffInMillies = Math.abs(date1.getTime() -
															// date2.getTime());
		long diffInMillies = date1.getTime() - date2.getTime();
		int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

	public ResultItem getProcessedItem(Item item, Date reportDate) {
		QualityDetails qualityDetails = ProductData.getProductQualityMap().get(item.getName());
		if (qualityDetails == null) {
			qualityDetails = ProductData.getProductQualityMap().get("Default");
		}
		int sellDiff = getDateDiff(item.getSellDate(), reportDate);
		int entryDiff = getDateDiff(reportDate, item.getEntryDate());
		int afterSellDiff = getDateDiff(reportDate, item.getSellDate());
		int entryToSell = getDateDiff(item.getSellDate(), item.getEntryDate());

		ResultItem item1 = new ResultItem();
		item1.setName(item.getName());
item1.setSellIn(sellDiff>0?sellDiff:0);

		if (sellDiff > 0) {

			if (entryDiff > 10) {
				int tenDay = 10 - (sellDiff);
				int remaining = entryDiff - tenDay;
				item1.setQuality(item.getQuality() - (remaining * qualityDetails.getDecrement())
						- (tenDay * qualityDetails.getDecrementBefore10()));
			} else {
				item1.setQuality(item.getQuality() - (entryDiff * qualityDetails.getDecrementBefore10()));
			}
		} else {
			if (qualityDetails.isZeroAfterSellDate()) {
				item1.setQuality(0);
			} else {
				if (entryToSell > 10) {
					int tenDay = 10;
					int remaining = entryToSell - 10;
					item1.setQuality(item.getQuality() - (remaining * qualityDetails.getDecrement())
							- (tenDay * qualityDetails.getDecrementBefore10())
							- (afterSellDiff * qualityDetails.getDecrementAfterSellDate()));
				} else {
					item1.setQuality(item.getQuality() - (entryToSell * qualityDetails.getDecrementBefore10())
							- (afterSellDiff * qualityDetails.getDecrementAfterSellDate()));
				}
			}
		}
		//item1.setSellDate(item.getSellDate());

		return item1;
	}

	/*
	 * public Item getProcessedItem(Item item, Date reportDate) { QualityDetails
	 * qualityDetails = ProductData.getProductQualityMap().get(item.getName()); if
	 * (qualityDetails == null) { qualityDetails =
	 * ProductData.getProductQualityMap().get("Default"); } int sellDiff =
	 * getDateDiff(item.getSellDate(), reportDate); int entryDiff =
	 * getDateDiff(reportDate, item.getEntryDate()); int afterSellDiff =
	 * getDateDiff(reportDate, item.getSellDate());
	 * 
	 * Item item1 = new Item(); item1.setName(item.getName());
	 * item1.setSellIn(sellDiff); if (sellDiff > 10) {
	 * //item1.setQuality(item.getQuality() - (entryDiff *
	 * qualityDetails.getDecrement())); if (entryDiff > 10) {
	 * item1.setQuality(item.getQuality() - ((entryDiff - 10) *
	 * qualityDetails.getDecrement()) - (10 * qualityDetails.getDecrementBefore10())
	 * - (afterSellDiff * qualityDetails.getDecrementAfterSellDate())); }else
	 * {item1.setQuality(item.getQuality() - (10 *
	 * qualityDetails.getDecrementBefore10()) - (afterSellDiff *
	 * qualityDetails.getDecrementAfterSellDate()));} } else if (sellDiff <= 10 &&
	 * sellDiff > 0) { int calcDiff = 10 - sellDiff;
	 * item1.setQuality(item.getQuality() - ((entryDiff - calcDiff) *
	 * qualityDetails.getDecrement()) - (calcDiff *
	 * qualityDetails.getDecrementBefore10())); } else { if
	 * (qualityDetails.isZeroAfterSellDate()) { item1.setQuality(0); } else { if
	 * (entryDiff > 10) { item1.setQuality(item.getQuality() - ((entryDiff - 10) *
	 * qualityDetails.getDecrement()) - (10 * qualityDetails.getDecrementBefore10())
	 * - (afterSellDiff * qualityDetails.getDecrementAfterSellDate())); }else
	 * {item1.setQuality(item.getQuality() - (10 *
	 * qualityDetails.getDecrementBefore10()) - (afterSellDiff *
	 * qualityDetails.getDecrementAfterSellDate()));} } }
	 * item1.setSellDate(item.getSellDate());
	 * 
	 * return item1; }
	 */}
