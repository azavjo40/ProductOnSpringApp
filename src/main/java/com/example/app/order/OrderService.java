package com.example.app.order;

import com.example.app.common.dto.ResponseDto;
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
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(orderDto.getOrderDate());
		order.setTotalCost(orderDto.getTotalCost());

		List<OrderItem> orderItems = new ArrayList<>();
		for (OrderItemDto itemDto : orderDto.getOrderItems()) {
			Product product = productRepository.findById(itemDto.getProductId())
					.orElseThrow(() -> new RuntimeException("Product not found: " + itemDto.getProductId()));
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(itemDto.getQuantity());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}

		order.setOrderItems(orderItems);
		orderRepository.save(order);
		return ResponseEntity.ok("You ordered");
	}


	public ResponseEntity<ResponseDto<List<Order>>> getUserOrders(User user) {
		List<Order> orders = getUserOrders(user.getId());
		ResponseDto<List<Order>> orderResponse = new ResponseDto<>(orders, "Success");
		return ResponseEntity.ok(orderResponse);
	}

	public List<Order> getUserOrders(Long userId) {
		return userId != null ? orderRepository.findOrdersByUserId(userId) : new ArrayList<>();
	}

	public ResponseEntity<ResponseDto<List<Order>>> getOrders() {
		List<Order> orders = orderRepository.findAll();
		ResponseDto<List<Order>> orderResponse = new ResponseDto<>(orders, "Success");
		return ResponseEntity.ok(orderResponse);
	}
}
