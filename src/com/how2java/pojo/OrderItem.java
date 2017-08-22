package com.how2java.pojo;

public class OrderItem {
	private int id;
	private int number;
	private Order order;
	private Product product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", order=");
		builder.append(order);
		builder.append(", product=");
		builder.append(product);
		builder.append("]");
		return builder.toString();
	}
	
}
