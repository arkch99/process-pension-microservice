package com.cognizant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.feign.AuthorizationClient;
import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.model.ProcessPensionInput;
import com.cognizant.model.ProcessPensionResponse;
import com.cognizant.service.ProcessPensionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class ProcessPensionController {
	@Autowired
	private AuthorizationClient authorizationClient;
	
	@Autowired
	private ProcessPensionService processPensionService;
	
	@PostMapping("/pensionDetail")
	public ResponseEntity<PensionDetail> getPensionDetails(@RequestHeader(name = "Authorization") String token, 
			@RequestBody @Valid PensionerInput pensionerInput) {
		log.info("Inside ProcessPensionController: getPensionDetails()");
		// TODO: actually use authclient			
		log.info("Returning from ProcessPensionController: getPensionDetails()");
		return new ResponseEntity<>(processPensionService.getPensionDetail(token, pensionerInput), HttpStatus.OK);		
	}
	
	@PostMapping("/processPension")
	public ResponseEntity<ProcessPensionResponse> processPension(@RequestHeader(name = "Authorization") String token, 
			@RequestBody @Valid ProcessPensionInput pensionInput) {
		log.info("Inside ProcessPensionController: processPension()");
		// TODO: use auth
		log.info("Returning from ProcessPensionController: processPension()");
		return new ResponseEntity<>(processPensionService.disbursePension(token, pensionInput), HttpStatus.OK);
	}
}
