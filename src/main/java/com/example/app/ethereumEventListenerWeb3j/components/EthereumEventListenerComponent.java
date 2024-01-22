package com.example.app.ethereumEventListenerWeb3j.components;

import com.example.app.ethereumEventListenerWeb3j.EthereumEventListenerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.utils.Numeric;

@Component
public class EthereumEventListenerComponent {
	private final Web3j web3j;
	private final EthereumEventListenerService ethereumEventListenerService;
	@Value("${app.exchange_address}")
	private String exchange_address;


	@Autowired
	public EthereumEventListenerComponent(Web3j web3j, EthereumEventListenerService ethereumEventListenerService) {
		this.web3j = web3j;
		this.ethereumEventListenerService = ethereumEventListenerService;
	}

	@PostConstruct
	private void init() {
		listeningTokensPurchased();
	}

	private void listeningTokensPurchased() {
		String eventSignatureHash = Numeric.toHexString(Hash.sha3("TokensPurchased(address,address,uint256)".getBytes()));

		web3j.ethLogFlowable(getEthFilter(exchange_address)).subscribe(log -> {
			if (log.getTopics().size() > 0 && log.getTopics().get(0).equals(eventSignatureHash)) {
				ethereumEventListenerService.listeningTokensPurchased(log);
			}
		});
	}

	private EthFilter getEthFilter(String contractAddress) {
		return new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contractAddress);
	}
}
