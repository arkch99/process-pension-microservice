package com.cognizant.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="AUTH-SERVICE", url = "${auth.service.url}")
public interface AuthorizationClient {
	@GetMapping("/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);
}
