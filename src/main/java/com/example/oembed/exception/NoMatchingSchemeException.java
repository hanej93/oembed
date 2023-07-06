package com.example.oembed.exception;

import org.springframework.http.HttpStatus;

public class NoMatchingSchemeException extends CustomException {

	public NoMatchingSchemeException(String url) {
		super("No matching scheme found for " + url);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
}
