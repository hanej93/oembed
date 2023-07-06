package com.example.oembed.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oembed.dto.OEmbedResponse;

@SpringBootTest
class OEmbedServiceTest {

	@Autowired
	private OEmbedService oEmbedService;

	@Test
	public void testFetchOEmbedData() {
		// String requestUrl = "https://twitter.com/hellopolicy/status/867177144815804416";
		String requestUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";
		// String requestUrl = "https://vimeo.com/20097015";

		OEmbedResponse test = oEmbedService.getOEmbedResponse(requestUrl).block();

		System.out.println("test = " + test);
	}

}