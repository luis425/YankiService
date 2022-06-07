package com.bootcamp.Yanki.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor 
@Data
@Builder
public class TypeBankAccounts { 
	
	private Integer id;
	 
	private String description;
	
	// Manejo Por porcentaje 
	private Integer commission;
	
	// Limite maximo
	private Integer maximumLimit;
	
	
}