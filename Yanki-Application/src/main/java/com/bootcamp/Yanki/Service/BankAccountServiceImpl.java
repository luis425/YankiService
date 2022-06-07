package com.bootcamp.Yanki.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.Yanki.redis.Model.BankAccountCache;
import com.bootcamp.Yanki.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

	private final BankAccountRepository bankAccountRepository;
	
	@Override
	public List<BankAccountCache> getAllbyNumberAccount() {
		 try {
		      List<BankAccountCache> bankAccountCacheList = new ArrayList<>();
		      bankAccountRepository.findAll().forEach(bankAccountCacheList::add);
		      return bankAccountCacheList;
		    } catch (Exception e) {
		      log.error("Error while trying to get assurances from Redis cache. " + e.getMessage());
		      return Collections.EMPTY_LIST;
		    }
	}

	@Override
	public String storageAssuranceList(List<BankAccountCache> bankAccountCacheList) {
		try {
		      Iterable<BankAccountCache> bankaccountCacheIterable = bankAccountCacheList;
		      bankAccountRepository.saveAll(bankaccountCacheIterable);
		      return "Assurance list create successfully";
		    } catch (Exception e) {
		      return "Error saving assurance cache list. " + e.getMessage();
		    }
		  }
	}

