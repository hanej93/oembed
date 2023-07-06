package com.example.oembed.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.oembed.dto.ErrorResponse;
import com.example.oembed.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerAdvisor {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
		log.error(e.getLocalizedMessage(), e);

		int statusCode = e.getStatusCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.code(String.valueOf(statusCode))
			.message(e.getMessage())
			.build();

		return ResponseEntity.status(statusCode).body(errorResponse);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResponse internalServerErrorHandler(Exception e) {
		log.error(e.getLocalizedMessage(), e);

		ErrorResponse errorResponse = ErrorResponse.builder()
			.code("500")
			.message("Internal Server Error")
			.build();

		return errorResponse;
	}

}
