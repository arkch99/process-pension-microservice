package com.cognizant.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ProcessPensionInput {
	@Pattern(regexp = "\\d{12}", message = "Adhaar format invalid")
	@NotEmpty
	private String adhaar;
	
	@NotNull(message = "Pension amount cannot be empty")
	private double pensionAmount;
	
	@NotNull(message = "Bank service charge cannot be empty")	
	@Range(min = 0, message = "Bank service charge cannot be negative")
	private double bankServiceCharge;
}
