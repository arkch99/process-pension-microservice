package com.cognizant.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PensionerDetail {
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date of birth cannot be empty")
	private Date dateofBirth;
	private String panNumber;
	private double salary;
	private double allowances;
	private String pensionType;
	private Bank bankDetail;
}
