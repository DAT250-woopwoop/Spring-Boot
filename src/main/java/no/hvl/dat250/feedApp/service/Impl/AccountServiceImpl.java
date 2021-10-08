package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PollRepository pollRepository;

    @Override
    public Account makeNewAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> account = Optional.of(accountRepository.findById(id)).get();
        return account.orElse(null);
    }

    @Override
    public Account updateAccount(Account account, Long id) {
        Optional<Account> optionalAccount = Optional.of(accountRepository.findById(id)).get();
        if (optionalAccount.isEmpty()) return null;
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

        if (account.isEmpty()) return null;

        Account acc = account.get();

        poll.setAccount(acc);

        acc.getPolls().add(poll);
        pollRepository.saveAndFlush(poll);
        accountRepository.save(acc);


        return poll;
    }
}
