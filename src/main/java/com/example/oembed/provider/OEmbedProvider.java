package com.example.oembed.provider;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OEmbedProvider {

	private String providerName;
	private String providerUrl;
	private List<Endpoint> endpoints;

	@Getter
	@Setter
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Endpoint {
		private String[] schemes;
		private String url;
		private boolean discovery;
		private List<String> formats;
	}
}
