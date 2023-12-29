package com.example.app.Order.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class OrderCreateDto {
	@NotEmpty(message = "Order items list cannot be empty")
	private List<OrderItemDto> orderItems;

	@NotNull(message = "Order Date cannot be null")
	private Date orderDate;

	@NotNull(message = "Total Cost cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Total Cost must be greater than 0")
	private Double totalCost;

	public OrderCreateDto(List<OrderItemDto> orderItems, Date orderDate, Double totalCost) {
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
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
