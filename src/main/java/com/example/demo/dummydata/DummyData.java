package com.example.demo.dummydata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.model.Product;

public class DummyData {

	private static DummyData instance;
	
	private ArrayList<Product> products;
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	private DummyData() {
		products = new ArrayList<Product>(Arrays.asList(
				new Product(Long.valueOf(1), "Samsung galaxy Tab 2", "Phone or tablet.", new BigDecimal(8000)),
				new Product(Long.valueOf(1), "DELL XPS 13", "Business laptop.", new BigDecimal(40000))
				));
	}
	
	public static DummyData getInstance() {
		if (instance == null) {
			instance = new DummyData();
		}
		return instance;
	}
	
}
