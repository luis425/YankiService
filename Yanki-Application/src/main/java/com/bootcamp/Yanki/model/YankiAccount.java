package com.bootcamp.Yanki.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "yankiAccount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YankiAccount {
	
	@Id
	private String id;  
	private boolean yunkiAccountstatus;
	private Double amount; 
	//private boolean yunkirelationBankFlag;
	// Tipo de Documento D = DNI, C = CEX, P = PASAPORTE
	private String documentType;
	private String numberDocument;
	private String phoneNumber;
	private String imei;
	private String email;
	private String numberDebitCard; 
	
}