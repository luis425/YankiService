package com.bootcamp.Yanki.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.Yanki.model.Transfer;

@Repository
public interface YankiTransferRepository  extends ReactiveCrudRepository<Transfer, String>{

}
