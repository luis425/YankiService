package com.bootcamp.Yanki.repository;
 
import org.springframework.data.mongodb.repository.ReactiveMongoRepository; 
import org.springframework.stereotype.Repository;
 
import com.bootcamp.Yanki.model.YankiAccount;
 
@Repository
public interface YankiRepository extends ReactiveMongoRepository<YankiAccount, String> {
}
