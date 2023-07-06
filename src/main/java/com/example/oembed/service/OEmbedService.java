package com.example.oembed.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.oembed.dto.OEmbedResponse;
import com.example.oembed.exception.CustomException;
import com.example.oembed.exception.NoMatchingSchemeException;
import com.example.oembed.exception.NoOEmbedProviderException;
import com.example.oembed.exception.UrlNotFoundException;
import com.example.oembed.provider.OEmbedProvider;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OEmbedService {

	private final WebClient webClient;
	private final List<OEmbedProvider> providers;
	private final AntPathMatcher matcher = new AntPathMatcher();

	public OEmbedService(WebClient webClient, OEmbedProviderService oEmbedProviderService) {
		this.webClient = webClient;
		this.providers = oEmbedProviderService.getProviders();
	}

	public Mono<OEmbedResponse> getOEmbedResponse(String requestUrl) {
		String trimmedRequestUrl = requestUrl.trim();
		String oEmbedUrl = findOEmbedUrl(trimmedRequestUrl)
			.orElseThrow(() -> new NoOEmbedProviderException(requestUrl));

		oEmbedUrl = replaceFormatInUrl(oEmbedUrl);

		return fetchOEmbedData(oEmbedUrl, requestUrl);
	}

	private Optional<String> findOEmbedUrl(String url) {
		String embedUrl = providers.stream()
			.flatMap(provider -> provider.getEndpoints().stream())
			.filter(endpoint -> endpoint.getSchemes() != null && Arrays.stream(endpoint.getSchemes())
				.anyMatch(scheme -> matchPattern(scheme, url)))
			.map(OEmbedProvider.Endpoint::getUrl)
			.findFirst()
			.orElseThrow(() -> new NoMatchingSchemeException(url));
		return Optional.of(embedUrl);
	}

	private boolean matchPattern(String pattern, String url) {
		return matcher.match(pattern, url);
	}

	private String replaceFormatInUrl(String oEmbedUrl) {
		return oEmbedUrl.replace("{format}", "json");
	}

	private Mono<OEmbedResponse> fetchOEmbedData(String oEmbedUrl, String url) {
		String builtUri = oEmbedUrl + "?url=" + url + "&format=json";

		return webClient.get()
			.uri(builtUri)
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new UrlNotFoundException(url)))
			.bodyToMono(OEmbedResponse.class);
	}

}
