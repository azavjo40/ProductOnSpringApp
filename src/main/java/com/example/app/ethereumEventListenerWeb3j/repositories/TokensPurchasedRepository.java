package com.example.app.ethereumEventListenerWeb3j.repositories;

import com.example.app.ethereumEventListenerWeb3j.entities.TokensPurchased;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokensPurchasedRepository extends JpaRepository<TokensPurchased, Long> {
	Optional<TokensPurchased> findByTransactionHash(String transactionHash);
}
