package com.how2java.pojo;

import java.util.List;

public class Order {
	private int id;
	private String code;
	List<OrderItem> orderItems;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", orderItems=");
		builder.append(orderItems);
		builder.append("]");
		return builder.toString();
	}
	
}
