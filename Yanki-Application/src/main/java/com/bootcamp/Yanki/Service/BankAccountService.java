package com.bootcamp.Yanki.Service;

import java.util.List;

import com.bootcamp.Yanki.redis.Model.BankAccountCache;

public interface BankAccountService {

	List<BankAccountCache> getAllbyNumberAccount();
	String storageAssuranceList(List<BankAccountCache> bankAccountCacheList);
	
}
