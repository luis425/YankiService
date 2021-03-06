package com.bootcamp.Yanki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.Yanki.model.YankiAccount;
import com.bootcamp.Yanki.repository.YankiRepository; 
 
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yankiAccountAct")
public class YankiController { 
	
	
	@Autowired
	YankiRepository yankiRepository;
	
	@PostMapping
	public Mono<YankiAccount> createYankiAccount(@RequestBody YankiAccount yankiAccount) { 
		 return this.yankiRepository.save(yankiAccount); 
	}
}
