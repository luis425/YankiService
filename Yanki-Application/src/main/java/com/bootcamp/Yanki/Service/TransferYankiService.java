package com.bootcamp.Yanki.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.Yanki.model.Transfer;
import com.bootcamp.Yanki.model.YankiAccount;
import com.bootcamp.Yanki.repository.YankiRepository;
import com.bootcamp.Yanki.repository.YankiTransferRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransferYankiService {

	@Autowired
	YankiTransferRepository yankiTransferRepository;
	
	@Autowired
	YankiRepository yankiRepository;
	
	public Mono<Transfer> createYankiTransfer(Transfer transferyanki) {
		return yankiTransferRepository.save(transferyanki);
	}
	
	public Flux<YankiAccount> getAccountYankibyphoneNumber(String phoneNumber) {
		return yankiRepository.findAll().filter(x -> x.getPhoneNumber().equals(phoneNumber));
	}
	
}
