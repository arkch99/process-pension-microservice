package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.exception.NotFoundException;
import com.cognizant.feign.PensionDisbursementClient;
import com.cognizant.feign.PensionerDetailsClient;
import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.model.ProcessPensionInput;
import com.cognizant.model.ProcessPensionResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessPensionServiceImpl implements ProcessPensionService{
	@Autowired
	private PensionerDetailsClient pensionerDetailsClient;
	
	@Autowired 
	private PensionDisbursementClient pensionDisbursementClient;	
	
	private boolean verifyDetails(PensionerInput pensionerInput, PensionerDetail pensionerDetail) {
		log.info("Entered ProcessPensionService: verifyDetails()");
		return pensionerInput.getName().equalsIgnoreCase(pensionerDetail.getName())
				&& (pensionerInput.getDateOfBirth().compareTo(pensionerDetail.getDateofBirth()) == 0)
				&& pensionerInput.getPan().equalsIgnoreCase(pensionerDetail.getPanNumber())
				&& pensionerInput.getPensionType().equalsIgnoreCase(pensionerDetail.getPensionType());
	}
	
	private double calculatePension(PensionerDetail pensionerDetail) {
		log.info("Entered ProcessPensionService: calculatePension()");
		final String pensionType = pensionerDetail.getPensionType();
		if(pensionType.equalsIgnoreCase("self")) {
			log.debug("Pension type is self");
			return pensionerDetail.getSalary() * 0.8 + pensionerDetail.getAllowances();
		}
		else {
			log.debug("Pension type is family");
			return pensionerDetail.getSalary() * 0.5 + pensionerDetail.getAllowances();
		}
		
	}
	
	@Override
	public PensionDetail getPensionDetail(String token, PensionerInput pensionerInput) {
		log.info("In ProcessPensionService: getPensionDetail()");
		// TODO actually use client 
		PensionerDetail pensionerDetail = pensionerDetailsClient.getPensionerDetailByAadhaar(token, pensionerInput.getAdhaar());
//		PensionerDetail pensionerDetail = new PensionerDetail(pensionerInput.getName(), pensionerInput.getDateOfBirth(), pensionerInput.getPan(), 100, 10, pensionerInput.getPensionType(), null);
		log.debug("Received following from PensionerDetails Microservice:");
		log.debug(pensionerDetail.toString());
		if(verifyDetails(pensionerInput, pensionerDetail)) {
			log.info("Pensioner details verified from PensionerDetails Microservice");
			log.info("Returning from ProcessPensionService: getPensionDetail()");
			return new PensionDetail(pensionerInput.getName(), pensionerInput.getDateOfBirth(), pensionerInput.getPan(), pensionerDetail.getPensionType(), calculatePension(pensionerDetail));			 
		}
		else {
			log.info("Pensioner details could not be verified");
			throw new NotFoundException("Invalid details");
		}
	}

	@Override
	public ProcessPensionResponse disbursePension(String token, ProcessPensionInput processPensionInput) {
		log.info("In ProcessPensionService: disbursePension()");
		// TODO actually use disbursement service
		// ProcessPensionResponse pensionResponse = pensionDisbursementClient.disbursePension(token, processPensionInput);
		int hitCount = 0;
		
		ProcessPensionResponse pensionResponse = new ProcessPensionResponse(10);
		while(pensionResponse.getStatusCode() == 21 && hitCount++ < 2) {
			log.info("Retrying disbursement service again");		
		}
		log.info("Returning from ProcessPensionService: disbursePension()");
		return pensionResponse;
	}
	
}
