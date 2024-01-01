package com.example.app.order;

import com.example.app.order.dto.OrderCreateDto;
import com.example.app.order.dto.OrderItemDto;
import com.example.app.order.entities.Order;
import com.example.app.order.entities.OrderItem;
import com.example.app.order.repositories.OrderItemRepository;
import com.example.app.order.repositories.OrderRepository;
import com.example.app.product.entities.Product;
import com.example.app.product.repositories.ProductRepository;
import com.example.app.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Transactional
	public ResponseEntity<String> createOrder(OrderCreateDto orderDto, User user) {
		Order order = buildOrder(orderDto);
		order.setUser(user);
		orderRepository.save(order);
		saveOrderItems(orderDto, order);
		return ResponseEntity.ok("Order created successfully");
	}

	private Order buildOrder(OrderCreateDto orderDto) {
		Order order = new Order();
		order.setOrderDate(orderDto.getOrderDate());
		order.setTotalCost(orderDto.getTotalCost());
		return order;
	}

	private void saveOrderItems(OrderCreateDto orderDto, Order order) {
		List<OrderItem> orderItems = orderDto.getOrderItems().stream()
				.map(itemDto -> {
					Product product = findProduct(itemDto.getProductId());
					return buildOrderItem(order, product, itemDto);
				})
				.collect(Collectors.toList());

		orderItems.forEach(orderItemRepository::save);
	}

	private Product findProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found: " + productId));
	}

	private OrderItem buildOrderItem(Order order, Product product, OrderItemDto itemDto) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getId());
		orderItem.setProduct(product);
		orderItem.setQuantity(itemDto.getQuantity());
		return orderItem;
	}


	public ResponseEntity<List<Order>> getUserOrders(User user) {
		List<Order> orders = getUserOrders(user.getId());
		return ResponseEntity.ok(orderRepository.findAll());
	}

	public List<Order> getUserOrders(Long userId) {
		return userId != null ? orderRepository.findOrdersByUserId(userId) : new ArrayList<>();
	}

}
