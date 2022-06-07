package com.bootcamp.Yanki.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bootcamp.Yanki.Service.TransferYankiService;
import com.bootcamp.Yanki.model.Transfer; 
import com.bootcamp.Yanki.model.YankiAccount;
import com.bootcamp.Yanki.repository.YankiRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/yankiTransfer")
public class TransferController {

	@Autowired
	TransferYankiService transferYankiService;
	
	@Autowired
	YankiRepository yankiRepository;
	
	private String messageBadRequest;
	
	@PostMapping(value = "/transfer")
	public Mono<Transfer> createYankiTransfer(@RequestBody Transfer yankiTransfer) {

		boolean validationvalue = this.validationRegisterRequest(yankiTransfer);  
		
		if (validationvalue) {

			try {

				Flux<YankiAccount> yankiAccount = this.transferYankiService.getAccountYankibyphoneNumber(yankiTransfer.getPhoneNumberorigen());

				List<YankiAccount> listyankiOrigen = new ArrayList<>();

				yankiAccount.collectList().subscribe(listyankiOrigen::addAll);
				
				Flux<YankiAccount> yankiAccountDesti = this.transferYankiService.getAccountYankibyphoneNumber(yankiTransfer.getPhoneNumberdestination());

				List<YankiAccount> listyankiDestinatario = new ArrayList<>();

				yankiAccountDesti.collectList().subscribe(listyankiDestinatario::addAll);
				
				long temporizador = (7 * 1000);

				Thread.sleep(temporizador);
				
				if(listyankiOrigen.isEmpty()) {
					return Mono.error(new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Cuenta Yanki Origen, no existe."));
				}

				if (listyankiDestinatario.isEmpty()) {

					return Mono.error(new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"Cuenta Yanki Destinatario, no existe."));

				} 

						// Descuento del Origen 
				
						Double descuento = listyankiOrigen.get(0).getAmount() - yankiTransfer.getAmount();

						listyankiOrigen.get(0).setAmount(descuento); 
						
						YankiAccount yankiAccountModel = this.registerDto(listyankiOrigen);
						 
						this.yankiRepository.save(yankiAccountModel).subscribe();
						
						// Deposito hacia el Destinatario
						 
						Double deposito = listyankiDestinatario.get(0).getAmount() + yankiTransfer.getAmount();

						listyankiDestinatario.get(0).setAmount(deposito); 
						
						YankiAccount yankiAccountModelDestinatario = this.registerDto(listyankiDestinatario);
						 
						this.yankiRepository.save(yankiAccountModelDestinatario).subscribe();
						
						// Registro del Movimiento 
						
						yankiTransfer.setId(UUID.randomUUID().toString());
						yankiTransfer.setTransferstatus(true);
						return this.transferYankiService.createYankiTransfer(yankiTransfer);
						
			} catch (InterruptedException e) {
				log.info(e.toString());
				Thread.currentThread().interrupt();
				return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()));
			}


		} else {
			return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, messageBadRequest));
		}
	}
	
	@GetMapping(value = "/yankibyphone/{phonenumber}")
	public Mono<ResponseEntity<YankiAccount>> getCustomerByDNIResponse(@PathVariable String phonenumber) {

		try {

			Flux<YankiAccount> customerflux = this.transferYankiService.getAccountYankibyphoneNumber(phonenumber);

			List<YankiAccount> list1 = new ArrayList<>();

			customerflux.collectList().subscribe(list1::addAll);

			long temporizador = (5 * 1000);

			Thread.sleep(temporizador);

			if (list1.isEmpty()) {
				return null;

			} else {
				return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list1.get(0)))
						.defaultIfEmpty(ResponseEntity.notFound().build());
			}

		} catch (InterruptedException e) {
			log.info(e.toString());
			Thread.currentThread().interrupt();
			return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()));
		}
	}
	
	public YankiAccount registerDto(List<YankiAccount> listyankiDestinatario) {

		YankiAccount yankiAccount = new YankiAccount(); 
		
		yankiAccount.setId(listyankiDestinatario.get(0).getId());
		yankiAccount.setYunkiAccountstatus(listyankiDestinatario.get(0).isYunkiAccountstatus());
		yankiAccount.setAmount(listyankiDestinatario.get(0).getAmount()); 
		yankiAccount.setDocumentType(listyankiDestinatario.get(0).getDocumentType());
		yankiAccount.setNumberDocument(listyankiDestinatario.get(0).getNumberDocument());
		yankiAccount.setNumberDebitCard(listyankiDestinatario.get(0).getNumberDebitCard());
		yankiAccount.setPhoneNumber(listyankiDestinatario.get(0).getPhoneNumber());
		yankiAccount.setImei(listyankiDestinatario.get(0).getImei());
		yankiAccount.setEmail(listyankiDestinatario.get(0).getEmail()); 
		return yankiAccount;
	}
	
	public boolean validationRegisterRequest(Transfer transfer) {

		boolean validatorbankAccounts;

		if (transfer.getId() != null) {
			validatorbankAccounts = false;
			messageBadRequest = "id es autogenerado, no se puede enviar en el Request";
		} else if (transfer.getAmount() == 0) {
			validatorbankAccounts = false;
			messageBadRequest = "Amount  no puede ser vacio";
		} else if (transfer.getPhoneNumberorigen().equals("")) {
			validatorbankAccounts = false;
			messageBadRequest = "PhoneNumberOrigen no puede ser vacio";
		} else if (transfer.getPhoneNumberdestination().equals("")) {
			validatorbankAccounts = false;
			messageBadRequest = "PhoneNumberDestination no puede ser vacio";
		} else {
			validatorbankAccounts = true;
		}

		return validatorbankAccounts;
	}
	
}
