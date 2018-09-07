package com.example.Product.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Product.model.Item;
import com.example.Product.model.QualityDetails;

public class ProductData {
	private static List<Item> productsList = new ArrayList<Item>();

	private static Map<String, QualityDetails> productQualityMap = new HashMap();

	public static List<Item> getProductsList() {
		return productsList;
	}

	public static Map<String, QualityDetails> getProductQualityMap() {
		return productQualityMap;
	}

	static {
		productQualityMap.put("Aged Brie", new QualityDetails("Aged Brie", -1, -1, -1, false));
		productQualityMap.put("Sulfuras", new QualityDetails("Sulfuras", 0, 0, 0, false));
		productQualityMap.put("Concert backstage passes",
				new QualityDetails("Concert backstage passes", -1, -2, 0, true));
		productQualityMap.put("Default", new QualityDetails("Default", 1, 1, 2, false));
	}
}
