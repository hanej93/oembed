package com.example.oembed.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OEmbedResponse {

	private String type;
	private String version;
	private String providerName;
	private String providerUrl;
	private String title;
	private String authorName;
	private String authorUrl;
	private String isPlus;
	private String accountType;
	private String html;
	private String width;
	private String height;
	private String duration;
	private String description;
	private String thumbnailUrl;
	private String thumbnailWidth;
	private String thumbnailHeight;
	private String thumbnailUrlWithPlayButton;
	private String uploadDate;
	private String videoId;
	private String uri;
	private String cacheAge;

}
