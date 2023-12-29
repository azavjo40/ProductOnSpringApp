package com.example.app.Order.repositories;

import com.example.app.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o WHERE o.user.id = ?1")
	List<Order> findOrdersByUserId(Long userId);
}
