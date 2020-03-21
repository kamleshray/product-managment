package com.product.model;

public class Product {
	private int id;
	private String name;
	private Double price;
	private String productColour;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getProductColour() {
		return productColour;
	}
	public void setProductColour(String productColour) {
		this.productColour = productColour;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", productColour=" + productColour + "]";
	}
	
}
