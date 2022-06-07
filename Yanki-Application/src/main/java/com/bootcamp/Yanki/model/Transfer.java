package com.bootcamp.Yanki.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "transferYanki")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
	
	@Id
	private String id;   
	
	private boolean transferstatus;
	
	private Double amount;   
	
	private String phoneNumberorigen;
	
	private String phoneNumberdestination;
	
}