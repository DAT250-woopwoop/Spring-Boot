package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.dweet.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.exceptions.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    DweetService dweetService;

    AccountRepository accountRepository;

    PollRepository pollRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PollRepository pollRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DweetService dweetService) {
        this.accountRepository = accountRepository;
        this.pollRepository = pollRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dweetService = dweetService;
    }

    @Override
    public Account makeNewAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
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
        if (optionalAccount.isEmpty()) throw new NoAccountFoundException("No account with given id");
        Account storedAcc = optionalAccount.get();

        storedAcc.update(account);
        return accountRepository.save(storedAcc);

    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Poll makeNewPoll(Poll poll, Long userId) {
        Optional<Account> account = accountRepository.findById(userId);

        if (account.isEmpty()) throw new NoAccountFoundException("No account with given id");

        Account acc = account.get();

        poll.setAccount(acc);

        pollRepository.saveAndFlush(poll);

        dweetService.send(poll, !poll.isClosed());

        return poll;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
