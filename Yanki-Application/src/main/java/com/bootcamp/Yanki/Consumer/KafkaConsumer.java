package com.bootcamp.Yanki.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bootcamp.Yanki.Service.YankiService;
import com.bootcamp.Yanki.config.Util;
import com.bootcamp.Yanki.model.Yanki;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

	@Autowired
	private YankiService yankiService;

	@KafkaListener(topics = "${kafka.subscribed-topic.name}")
	public void consumeEvent(String message) throws JsonProcessingException, InterruptedException {
		Yanki yunki = Util.OBJECT_MAPPER.readValue(message, Yanki.class);
		log.info("Message received " + message);
		yankiService.processCreateYankiAccount(yunki);
	}
}