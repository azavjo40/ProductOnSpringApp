package com.example.app.order.entities;

import com.example.app.user.entities.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_order")
public class Order {
	@Id
	@SequenceGenerator(
			name = "order_sequence",
			sequenceName = "order_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "order_sequence"
	)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItems;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private Double totalCost;

	public Order() {

	}

	public Order(User user, List<OrderItem> orderItems, Date orderDate, Double totalCost) {
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

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", user=" + user +
				", orderItems=" + orderItems +
				", orderDate=" + orderDate +
				", totalCost=" + totalCost +
				'}';
	}
}
