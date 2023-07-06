package com.example.oembed.exception;

import org.springframework.http.HttpStatus;

public class NoOEmbedProviderException extends CustomException {

	public NoOEmbedProviderException(String requestUrl) {
		super("No oEmbed provider available for " + requestUrl);
	}

	@Override
	public int getStatusCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
}
