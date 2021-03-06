package com.bootcamp.Yanki.model;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
	
	private String id;
	
	private String codeCustomer;
	
	private String nameCustomer;
	
	private String lastNameCustomer;
	
	private String dniCustomer;
	
	private CustomerType customertype;
	
	private Bank bank; 
	
}

