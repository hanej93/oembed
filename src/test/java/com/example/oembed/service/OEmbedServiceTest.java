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
		// 테스트할 데이터 준비
		// String requestUrl = "https://twitter.com/hellopolicy/status/867177144815804416";
		String requestUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";
		// String requestUrl = "https://vimeo.com/20097015";


		// 테스트 수행
		OEmbedResponse test = oEmbedService.getOEmbedResponse(requestUrl).block();

		System.out.println("test = " + test);

		// 테스트 결과 검증
		// 예를 들어, OEmbedResponse가 null이 아닌지 확인할 수 있습니다.
	}

	@Test
	public void test() {
		String str = " testste set ";
		String trim = str.trim();
		System.out.println("str = " + str);
		System.out.println("trim = " + trim);
	}
}