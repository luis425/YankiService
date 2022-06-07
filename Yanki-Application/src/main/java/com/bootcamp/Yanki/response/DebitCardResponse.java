package com.bootcamp.Yanki.response;

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
public class DebitCardResponse {
	
	private String numberDebitCard;
	
	private BankAccounts bankAccounts;
}
