package com.bootcamp.Yanki.redis.Model;

import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import com.bootcamp.Yanki.response.BankAccountResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RedisHash("BankAccount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankAccountCache {

	private String id;
	
	//private TypeBankAccounts typeBankAccounts;
	
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
	
	//private Customer customer;

	public static BankAccountCache fromBankAccountResponse(BankAccountResponse bankAccountResponse) {
		return BankAccountCache.builder()
				.id(bankAccountResponse.getId())
				//.typeBankAccounts(bankAccountResponse.getTypeBankAccounts())
				.numberAccount(bankAccountResponse.getNumberAccount())
				.keyAccount(bankAccountResponse.getKeyAccount())
				.availableBalanceAccount(bankAccountResponse.getAvailableBalanceAccount())
				.dateLastBankAccount(bankAccountResponse.getDateLastBankAccount())
				.statusAccount(bankAccountResponse.isStatusAccount())
				//.customer(bankAccountResponse.getCustomer())
				.build();
	}
	
}
