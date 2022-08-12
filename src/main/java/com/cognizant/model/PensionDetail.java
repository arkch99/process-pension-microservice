package com.cognizant.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PensionDetail {
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotEmpty(message = "Date of birth cannot be empty")
	private Date dateOfBirth;
	
	@NotEmpty(message = "PAN cannot be empty")
	private String pan;
	
	@Pattern(regexp = "self|family", flags = Flag.CASE_INSENSITIVE)
	@NotEmpty
	private String pensionType;
	
	@NotEmpty(message = "Pension amount cannot be empty")
	private double pensionAmount;
}
