package com.example.app.ethereumEventListenerWeb3j;

import com.example.app.common.dto.ResponseDto;
import com.example.app.ethereumEventListenerWeb3j.entities.TokensPurchased;
import com.example.app.ethereumEventListenerWeb3j.repositories.TokensPurchasedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
public class EthereumEventListenerService {
	private final TokensPurchasedRepository tokensPurchasedRepository;

	@Autowired
	public EthereumEventListenerService(TokensPurchasedRepository tokensPurchasedRepository) {
		this.tokensPurchasedRepository = tokensPurchasedRepository;
	}

	public void listeningTokensPurchased(Log log) {
		String transactionHash = log.getTransactionHash();
		Optional<TokensPurchased> existingEntry = tokensPurchasedRepository.findByTransactionHash(transactionHash);
		if (existingEntry.isPresent()) {
			return;
		}

		Address buyer = getTopicArg(log, 1, Address.class);
		Address stableCoin = getTopicArg(log, 2, Address.class);
		BigInteger amount = new BigInteger(log.getData().substring(2), 16);

		TokensPurchased tokensPurchased = new TokensPurchased();
		tokensPurchased.setBuyer(buyer.toString());
		tokensPurchased.setStableCoin(stableCoin.toString());
		tokensPurchased.setAmount(amount);
		tokensPurchased.setTransactionHash(transactionHash);

		tokensPurchasedRepository.save(tokensPurchased);
	}


	private <T extends Type> T getTopicArg(Log log, int index, Class<T> typeClass) {
		String topic = log.getTopics().get(index);
		TypeReference<T> typeReference = new TypeReference<T>() {
			@Override
			public java.lang.reflect.Type getType() {
				return typeClass;
			}
		};
		return (T) FunctionReturnDecoder.decodeIndexedValue(topic, typeReference);
	}

	public ResponseEntity<ResponseDto<List<TokensPurchased>>> getTokensPurchased() {
		List<TokensPurchased> tokensPurchased = tokensPurchasedRepository.findAll();
		ResponseDto<List<TokensPurchased>> tokensPurchasedResponse = new ResponseDto<>(tokensPurchased, "Success");
		return ResponseEntity.ok(tokensPurchasedResponse);
	}
}
