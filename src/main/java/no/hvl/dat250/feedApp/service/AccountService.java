package no.hvl.dat250.feedApp.service;

import no.hvl.dat250.feedApp.entity.*;

import java.util.*;

public interface AccountService {

    Account makeNewAccount(Account account);

    List<Account> findAll();

    Account findAccountById(Long id);

    Account updateAccount(Account account, Long id);

    void deleteAccount(Long id);

    Poll makeNewPoll(Poll poll, Long id);

    Account getAccountByUsername(String username);
}
