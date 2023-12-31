package com.example.app.Order.dto;

import com.example.app.Order.Order;

import java.util.List;

public class OrderResponseDto {
	List<Order> orders;

	public OrderResponseDto(List<Order> orders) {
		this.orders = orders;
	}


	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "response=" + orders;
	}

}
