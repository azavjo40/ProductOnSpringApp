package com.example.app.Order;

import com.example.app.Order.dto.OrderCreateDto;
import com.example.app.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(path = "buy")
	public ResponseEntity<String> createOrder(@RequestBody @Valid OrderCreateDto order, @AuthenticationPrincipal User userLocal) {
		return orderService.createOrder(order, userLocal);
	}

	@GetMapping
	public ResponseEntity<String> getUserOrders(@AuthenticationPrincipal User userLocal) {
		return orderService.getUserOrders(userLocal);
	}
}
