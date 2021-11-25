package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.dweet.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.exceptions.*;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoAccountRepository;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoPollRepository;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    DweetService dweetService;

    private final AccountRepository accountRepository;
    private final MongoAccountRepository mongoAccountRepository;

    private final PollRepository pollRepository;
    private final MongoPollRepository mongoPollRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PollRepository pollRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder, DweetService dweetService,
                              MongoAccountRepository mongoAccountRepository, MongoPollRepository mongoPollRepository) {
        this.accountRepository = accountRepository;
        this.mongoAccountRepository = mongoAccountRepository;
        this.pollRepository = pollRepository;
        this.mongoPollRepository = mongoPollRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dweetService = dweetService;
    }

    @Override
    public Account makeNewAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Account savedAccount = accountRepository.save(account);
        no.hvl.dat250.feedApp.mongoDB.collections.Account mongoAccount = new no.hvl.dat250.feedApp.mongoDB.collections.Account();
        mongoAccount.setId(savedAccount.getId());
        mongoAccount.update(savedAccount);
        mongoAccountRepository.save(mongoAccount);
        return savedAccount;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id)).get();
        if (account.isEmpty()) throw new NoAccountFoundException("No account with given id");
        return account.orElse(null);
    }

    @Override
    public Account updateAccount(Account account, Long id) {
        Optional<Account> optionalAccount = Optional.of(accountRepository.findById(id)).get();
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> optionalMongoAccount = mongoAccountRepository.findById(id);
        if (optionalAccount.isEmpty()) throw new NoAccountFoundException("No account with given id");
        Account storedAcc = optionalAccount.get();
        storedAcc.update(account);
        Account updatedAccount = accountRepository.save(storedAcc);

        no.hvl.dat250.feedApp.mongoDB.collections.Account updatedMongoAccount;
        if (optionalMongoAccount.isPresent()){
            updatedMongoAccount = optionalMongoAccount.get();
        } else {
            updatedMongoAccount = new no.hvl.dat250.feedApp.mongoDB.collections.Account();
            updatedMongoAccount.setId(updatedAccount.getId());
        }
        updatedMongoAccount.update(updatedAccount);
        mongoAccountRepository.save(updatedMongoAccount);

        return updatedAccount;


    }

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> optionalMongoAccount = mongoAccountRepository.findById(id);
        if (optionalMongoAccount.isPresent()) {
            mongoAccountRepository.deleteById(id);
        }
        if (optionalAccount.isPresent()){
            accountRepository.deleteById(id);
        }
    }

    @Override
    public Poll makeNewPoll(Poll poll, Long userId) {
        Optional<Account> optionalAccount = accountRepository.findById(userId);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> optionalMongoAccount = mongoAccountRepository.findById(userId);

        if (optionalAccount.isEmpty() || optionalMongoAccount.isEmpty()) throw new NoAccountFoundException("No account with given id");

        Account account = optionalAccount.get();
        no.hvl.dat250.feedApp.mongoDB.collections.Account mongoAccount = optionalMongoAccount.get();

        poll.setAccount(account);

        Poll newPoll = pollRepository.saveAndFlush(poll);

        dweetService.send(poll, !poll.isClosed());

        no.hvl.dat250.feedApp.mongoDB.collections.Poll mongoPoll = new no.hvl.dat250.feedApp.mongoDB.collections.Poll();
        mongoPoll.setId(newPoll.getId());
        mongoPoll.update(newPoll);
        mongoPollRepository.save(mongoPoll);


        return newPoll;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
