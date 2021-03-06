package com.bootcamp.Yanki.model;

import java.io.Serializable;

import com.bootcamp.Yanki.response.BankAccountResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Yanki implements Serializable { 
	
	private String id;  
	private Double amount; 
	private String documentType;
	private String numberDocument;
	private String phoneNumber;
	private String imei;
	private String email;
	private String numberDebitCard;
	private boolean yunkiAccountstatus; 
}
