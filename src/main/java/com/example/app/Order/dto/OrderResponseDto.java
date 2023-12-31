package com.example.app.Order.dto;

import com.example.app.Order.OrderItem;
import com.example.app.user.User;

import java.util.Date;
import java.util.List;

public class OrderResponseDto {
	private Long id;
	private User user;
	private List<OrderItem> orderItems;
	private Date orderDate;
	private Double totalCost;

	public OrderResponseDto(Long id, User user, List<OrderItem> orderItems, Date orderDate, Double totalCost) {
		this.id = id;
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
}
