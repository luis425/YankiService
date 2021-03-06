package com.bootcamp.Yanki.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.Yanki.api.client.BankAccountApiClient;
import com.bootcamp.Yanki.model.Yanki;
import com.bootcamp.Yanki.model.YankiAccount;
//import com.bootcamp.Yanki.repository.BankAccountRepository;
import com.bootcamp.Yanki.repository.YankiRepository; 
import com.bootcamp.Yanki.response.DebitCardResponse;

import lombok.extern.slf4j.Slf4j; 
 
@Slf4j
@Service 
public class YankiServiceImpl implements YankiService {

	//private final BankAccountService bankAccountService;
	
	@Autowired
	BankAccountApiClient bankAccountApiClient; 
	
	@Autowired
	YankiRepository yankiRepository;
	
	@Override
	public String processCreateYankiAccount(Yanki yanki) throws InterruptedException {

		List<DebitCardResponse> listDebitCardAsociado = new ArrayList<>();
		
		//if (bankAccountService.getAllbyNumberAccount().isEmpty()) {

			// Obtener Lista para validacion con Numero de Cuenta
		
			//List<BankAccountResponse> listAccount = this.bankAccountApiClient.getBankAccountbyNumberAccount(yanki.getNumberAccount());
			
			if(yanki.getNumberDebitCard() != null ) {
				listDebitCardAsociado = this.bankAccountApiClient.getDebitCardbyCustomerNumberCreditCard(yanki.getNumberDebitCard());	
			}

			// Al no encontrar el numero de cuenta 
			
			if(listDebitCardAsociado.isEmpty()) {

				// Crea un nueva cuenta Yanki
				
				yanki.setYunkiAccountstatus(true);
				
				if(yanki.getAmount() == null) {
					yanki.setAmount(0.0);
				}
				
				YankiAccount yankiAccountModel = this.registerDto(yanki);
				
				this.yankiRepository.save(yankiAccountModel).subscribe();
				
				log.info("Yanki Register -->" + yankiAccountModel);
				
				return "Proceso Satisfactorio Nueva Cuenta Yanki sin relacion a un BankAccount";

			} else {

				 yanki.setAmount(listDebitCardAsociado.get(0).getBankAccounts().getAvailableBalanceAccount());
				 yanki.setDocumentType("D");
				 yanki.setNumberDocument(listDebitCardAsociado.get(0).getBankAccounts().getCustomer().getDniCustomer());
				 yanki.setYunkiAccountstatus(true);
				 YankiAccount yankiAccountModel = this.registerDto(yanki);
				 
				 this.yankiRepository.save(yankiAccountModel).subscribe();
				  
				return "Proceso Satisfactorio Nueva Cuenta Yanki relacionado a un BankAccount";
			}
			
		//}
			//log.info("From Redis cache " + bankAccountService.getAllbyNumberAccount().toString());
			//return "Processing Draft..."; 
		}
	 
	
	public YankiAccount registerDto(Yanki yanki) {

		YankiAccount yankiAccount = new YankiAccount(); 
		
		yankiAccount.setId(yanki.getId());
		yankiAccount.setYunkiAccountstatus(yanki.isYunkiAccountstatus());
		yankiAccount.setAmount(yanki.getAmount()); 
		yankiAccount.setDocumentType(yanki.getDocumentType());
		yankiAccount.setNumberDocument(yanki.getNumberDocument());
		yankiAccount.setNumberDebitCard(yanki.getNumberDebitCard());
		yankiAccount.setPhoneNumber(yanki.getPhoneNumber());
		yankiAccount.setImei(yanki.getImei());
		yankiAccount.setEmail(yanki.getEmail()); 
		return yankiAccount;
	}
 
}
