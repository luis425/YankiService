package com.bootcamp.Yanki.Service;

import com.bootcamp.Yanki.model.Yanki;

import reactor.core.publisher.Mono;

public interface YankiService {
	
	String processCreateYankiAccount(Yanki transaction) throws InterruptedException; 
}
