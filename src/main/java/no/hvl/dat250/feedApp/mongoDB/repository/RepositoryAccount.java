package no.hvl.dat250.feedApp.mongoDB.repository;

import no.hvl.dat250.feedApp.mongoDB.collections.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryAccount extends MongoRepository<Account, Long> {



}

