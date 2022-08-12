package com.cognizant.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.model.ProcessPensionInput;
import com.cognizant.model.ProcessPensionResponse;

@FeignClient(name="DISBURSEMENT-SERVICE", url = "${pensiondisbursement.service.url}")
public interface PensionDisbursementClient {
	@PostMapping("/DisbursePension")
	public ProcessPensionResponse disbursePension(@RequestHeader(name = "Authorization") String token, @RequestBody ProcessPensionInput processPensionInput);
}
