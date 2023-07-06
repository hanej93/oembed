package com.example.oembed.exception;

import org.springframework.http.HttpStatus;

public class UrlNotFoundException extends CustomException {

	public UrlNotFoundException(String url) {
		super("Not found url : " + url);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
}
