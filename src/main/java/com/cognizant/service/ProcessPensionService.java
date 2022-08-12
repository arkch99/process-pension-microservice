package com.cognizant.service;

import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.model.ProcessPensionInput;
import com.cognizant.model.ProcessPensionResponse;

public interface ProcessPensionService {
	
	// Fetches pension details if supplied information is valid
	public PensionDetail getPensionDetail(String token, PensionerInput pensionerInput);
	
	// verifies pension calculation
	public ProcessPensionResponse disbursePension(String token, ProcessPensionInput processPensionInput);		
}
