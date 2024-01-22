package com.example.app.ethereumEventListenerWeb3j.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "app_tokens_purchased")
public class TokensPurchased {
	@Id
	@SequenceGenerator(
			name = "tokens_purchased_sequence",
			sequenceName = "tokens_purchased_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "tokens_purchased_sequence"
	)
	private Long id;

	@Column(nullable = false)
	private String buyer;

	@Column(nullable = false)
	private String stableCoin;

	@Column(nullable = false, precision = 19, scale = 0)
	private BigInteger amount;

	@Column(nullable = false, unique = true)
	private String transactionHash;

	public TokensPurchased() {

	}

	public TokensPurchased(Long id, String buyer, String stableCoin, BigInteger amount, String transactionHash) {
		this.id = id;
		this.buyer = buyer;
		this.stableCoin = stableCoin;
		this.amount = amount;
		this.transactionHash = transactionHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getStableCoin() {
		return stableCoin;
	}

	public void setStableCoin(String stableCoin) {
		this.stableCoin = stableCoin;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	@Override
	public String toString() {
		return "TokensPurchased{" +
				"id=" + id +
				", buyer='" + buyer + '\'' +
				", stableCoin='" + stableCoin + '\'' +
				", amount=" + amount +
				", transactionHash='" + transactionHash + '\'' +
				'}';
	}
}
