package com.bootcamp.Yanki.api.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.Yanki.config.BankAccountApiProperties;
import com.bootcamp.Yanki.response.BankAccountResponse;
import com.bootcamp.Yanki.response.DebitCardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountApiClient {

	private final WebClient webClient;
	private final BankAccountApiProperties bankAccountApiProperties;
	
	public List<BankAccountResponse> getBankAccountbyNumberAccount(String numberAccount) throws InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		List<BankAccountResponse> result = new ArrayList<>();
		webClient.get().uri(bankAccountApiProperties.getBaseUrl() + "/bankAccounts/BankAccountResponse/".concat(numberAccount))
				.accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(BankAccountResponse.class)
				.publishOn(Schedulers.fromExecutor(executor))
				.subscribe(assuranceResponse -> result.add(assuranceResponse));

		executor.awaitTermination(2, TimeUnit.SECONDS);
		log.info("Assurance list " + result);
		return result;
	}
	
	public List<DebitCardResponse> getDebitCardbyCustomerNumberCreditCard(String numberDebitCard) throws InterruptedException {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		List<DebitCardResponse> result = new ArrayList<>();
		webClient.get().uri(bankAccountApiProperties.getBaseUrl() + "/debitCard/debitCardbydniResponse/".concat(numberDebitCard))
				.accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(DebitCardResponse.class)
				.publishOn(Schedulers.fromExecutor(executor))
				.subscribe(debitcard -> result.add(debitcard));

		executor.awaitTermination(6, TimeUnit.SECONDS);
		log.info("Assurance list " + result);
		return result;
	}
}
