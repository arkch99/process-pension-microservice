package com.cognizant.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PensionerInput {
		
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date of birth cannot be empty")
	private Date dateOfBirth;
	
	@NotEmpty(message = "PAN cannot be empty")
	private String pan;
	
	// adhaar must not begin with 0 or 1, and must be 12 digits long
	@Pattern(regexp = "\\d{12}", message = "Adhaar format invalid")
	@NotEmpty
	private String adhaar;
	
	@Pattern(regexp = "self|family", flags = Flag.CASE_INSENSITIVE)
	@NotEmpty
	private String pensionType;
}
