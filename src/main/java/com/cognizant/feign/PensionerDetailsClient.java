package com.cognizant.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.model.PensionerDetail;

@FeignClient(name="DETAILS-SERVICE", url = "${pensiondetails.service.url}")
public interface PensionerDetailsClient {	
	@GetMapping("/PensionerDetailByAadhaar/{aadhaar}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader(name = "Authorization") String token, @PathVariable String aadhaar);
}
