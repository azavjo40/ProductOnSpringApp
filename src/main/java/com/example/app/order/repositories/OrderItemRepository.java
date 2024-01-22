package com.example.app.order.repositories;

import com.example.app.ethereumEventListenerWeb3j.entities.TokensPurchased;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<TokensPurchased, Long> {
}
