package com.example.oembed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oembed.dto.OEmbedResponse;
import com.example.oembed.service.OEmbedService;

import reactor.core.publisher.Mono;

@Controller
public class OEmbedController {

	private final OEmbedService oEmbedService;

	public OEmbedController(OEmbedService oEmbedService) {
		this.oEmbedService = oEmbedService;
	}

	@ResponseBody
	@GetMapping("/oembed")
	public Mono<OEmbedResponse> getOEmbedData(@RequestParam String url) {
		return oEmbedService.getOEmbedResponse(url);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
