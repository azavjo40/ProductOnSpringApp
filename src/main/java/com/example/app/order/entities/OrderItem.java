package com.example.app.order.entities;

import com.example.app.product.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "app_order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;
	private Integer quantity;

	public OrderItem() {

	}

	public OrderItem(Product product, Order order, Integer quantity) {
		this.product = product;
		this.order = order;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", product=" + product +
				", order=" + order +
				", quantity=" + quantity +
				'}';
	}
}

