package com.example.app.order.dto;

import com.example.app.order.enums.PaymentMethod;
import com.example.app.order.validations.ConditionalValidation;
import com.example.app.order.validations.ConditionalValidations;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@ConditionalValidations({
		@ConditionalValidation(
				conditionalProperty = "paymentMethod",
				values = {"TOKEN"},
				requiredProperties = {"transactionHash"},
				message = "Transaction hash is required for token payment"
		),
		@ConditionalValidation(
				conditionalProperty = "paymentMethod",
				values = {"CARD"},
				requiredProperties = {"paymentTransactionId"},
				message = "Payment transaction ID is required for card payment"
		)
})
public class OrderCreateDto {
	@NotEmpty(message = "Order items list cannot be empty")
	private List<OrderItemDto> orderItems;

	@NotNull(message = "Order Date cannot be null")
	private Date orderDate;

	@NotNull(message = "Total Cost cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Total Cost must be greater than 0")
	private Double totalCost;

	@NotNull(message = "Payment Method cannot be null")
	private PaymentMethod paymentMethod;

	private String transactionHash;
	private String paymentTransactionId;


	public OrderCreateDto(List<OrderItemDto> orderItems, Date orderDate, Double totalCost, PaymentMethod paymentMethod, String transactionHash, String paymentTransactionId) {
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.paymentMethod = paymentMethod;
		this.transactionHash = transactionHash;
		this.paymentTransactionId = paymentTransactionId;
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

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
}
