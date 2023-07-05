package com.example.oembed.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.oembed.provider.OEmbedProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OEmbedProviderService {

	private final ObjectMapper objectMapper;
	private List<OEmbedProvider> providers;

	@PostConstruct
	public void init() throws IOException {
		InputStream inputStream = new ClassPathResource("static/providers.json").getInputStream();
		OEmbedProvider[] providersArray = objectMapper.readValue(inputStream, OEmbedProvider[].class);
		providers = Arrays.asList(providersArray);
	}

	public List<OEmbedProvider> getProviders() {
		return providers;
	}
}
