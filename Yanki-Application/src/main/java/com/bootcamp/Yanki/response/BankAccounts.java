package com.bootcamp.Yanki.response;

import java.util.Date;

import com.bootcamp.Yanki.model.CustomerResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class BankAccounts {

	private String id;
	 
	private String numberAccount;
	
	// Clave de cuenta - Deberia incriptarse
	private int keyAccount;
	
	// Monto Dispoible de la cuenta
	private double availableBalanceAccount;
	  
	// Postman enviar 2022-05-16T08:55:17.688+00:00
	@JsonFormat(pattern="dd-MM-yyyy" , timezone="GMT-05:00")
	private Date dateLastBankAccount;
	
	// Estado 
	private boolean statusAccount; 
	
	private CustomerResponse customer;
}
