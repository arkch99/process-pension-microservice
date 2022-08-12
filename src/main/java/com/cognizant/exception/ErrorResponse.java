package com.cognizant.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {	
	private String message;
	private LocalDateTime time;	
	
	public ErrorResponse(String message) { 
		this.time = LocalDateTime.now();
		this.message = message;		
	}
	
	@JsonInclude(Include.NON_NULL)
	private List<String> errors;
}
