package com.example.app.Order;

import com.example.app.Order.dto.OrderCreateDto;
import com.example.app.Order.dto.OrderItemDto;
import com.example.app.Order.dto.OrderResponseDto;
import com.example.app.Order.repositories.OrderRepository;
import com.example.app.product.Product;
import com.example.app.product.ProductRepository;
import com.example.app.user.User;
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

	@Autowired
	public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
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

	public ResponseEntity<String> getUserOrders(User user) {
		List<Order> orders = getUserOrders(user.getId());
		return ResponseEntity.ok(new OrderResponseDto(orders).toString());
	}

	public List<Order> getUserOrders(Long userId) {
		return userId != null ? orderRepository.findOrdersByUserId(userId) : new ArrayList<>();
	}

}
