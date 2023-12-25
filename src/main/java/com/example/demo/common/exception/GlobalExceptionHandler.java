package com.example.demo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
		// Возвращаем клиенту сообщение об ошибке и соответствующий HTTP статус код
		return ResponseEntity
				.status(HttpStatus.CONFLICT) // 409 Conflict
				.body(ex.getMessage());
	}
}
