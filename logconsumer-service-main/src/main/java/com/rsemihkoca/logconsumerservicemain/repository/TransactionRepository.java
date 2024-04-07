package com.rsemihkoca.logconsumerservicemain.repository;

import com.rsemihkoca.logconsumerservicemain.document.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionRepository extends MongoRepository<Transaction, String> {


}