package com.bootcamp.Yanki.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.Yanki.redis.Model.BankAccountCache;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccountCache, String> {

}
