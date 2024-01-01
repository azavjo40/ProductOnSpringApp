package com.example.app.order.entities;

import com.example.app.product.entities.Product;
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
	private Integer quantity;
	private Long order_id;

	public OrderItem() {

	}

	public OrderItem(Product product, Integer quantity) {
		this.product = product;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return order_id;
	}

	public void setOrderId(Long order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", product=" + product +
				", quantity=" + quantity +
				'}';
	}
}

