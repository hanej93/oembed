package com.example.oembed.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	public static final int CONNECTION_TIMEOUT = 2000;
	public static final int READ_TIMEOUT = 2000;
	public static final int WRITE_TIMEOUT = 2000;

	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT) // Connect Timeout 설정
			.doOnConnected(conn -> conn
				.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT, TimeUnit.MILLISECONDS)) // Read Timeout 설정
				.addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)) // Write Timeout 설정
			);
		
		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();
	}
}
