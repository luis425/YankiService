package com.bootcamp.Yanki.web.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.Yanki.producer.KafkaProducer;

@RestController
public class YankiController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostMapping("/customer/yanki")
	public ResponseEntity<String> requestYanki(@RequestBody String yanki) {
		kafkaProducer.publishMessage(yanki);
		return ResponseEntity.ok(yanki);
	}

}
