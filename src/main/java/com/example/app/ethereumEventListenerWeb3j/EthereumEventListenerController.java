package com.example.app.ethereumEventListenerWeb3j;

import com.example.app.common.dto.ResponseDto;
import com.example.app.ethereumEventListenerWeb3j.entities.TokensPurchased;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/event")
public class EthereumEventListenerController {
	private final EthereumEventListenerService ethereumEventListenerService;

	@Autowired
	public EthereumEventListenerController(EthereumEventListenerService ethereumEventListenerService) {
		this.ethereumEventListenerService = ethereumEventListenerService;
	}

	@GetMapping(path = "tokens/purchased")
	public ResponseEntity<ResponseDto<List<TokensPurchased>>> getTokensPurchased() {
		return ethereumEventListenerService.getTokensPurchased();
	}
}
