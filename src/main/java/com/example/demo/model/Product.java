package com.example.demo.model;

import java.math.BigDecimal;

public class Product {
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	private String description;
	private BigDecimal price;
	public Product(Long id, String title, String description, BigDecimal price) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", price=" + price + "]";
	}
	
}
