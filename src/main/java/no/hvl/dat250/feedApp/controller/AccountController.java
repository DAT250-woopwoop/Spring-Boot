package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.dweet.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryAccount;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryPoll;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final RepositoryAccount repositoryAccount;
    private final RepositoryPoll repositoryPoll;

    private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be a bean(?)

    AccountController(AccountService accountService, RepositoryAccount repositoryAccount, RepositoryPoll repositoryPoll){
        this.accountService = accountService;
        this.repositoryAccount = repositoryAccount;
        this.repositoryPoll = repositoryPoll;

    }

    @GetMapping("/users")
    public List<AccountDTO> all() {
        return accountService
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(toList());
    }

    @GetMapping("/users/{id}")
    public AccountDTO findAccountById(@PathVariable Long id) {
        return mapper.toDTO(accountService.findAccountById(id));
    }

    @GetMapping("/users/username/{username}")
    public AccountDTO findAccountByUsername(@PathVariable String username) {
        return mapper.toDTO(accountService.getAccountByUsername(username));
    }

    @PostMapping("/users/signup")
    public AccountDTO newAccount(@RequestBody Account account) {
        Account temp = accountService.makeNewAccount(account);
        no.hvl.dat250.feedApp.mongoDB.collections.Account acc = new no.hvl.dat250.feedApp.mongoDB.collections.Account();
        acc.setId(temp.getId());
        acc.update(account);
        repositoryAccount.save(acc);
        return mapper.toDTO(temp);

    }

    @PutMapping("users/{id}")
    public AccountDTO updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> acc = repositoryAccount.findById(id);
        no.hvl.dat250.feedApp.mongoDB.collections.Account newAcc;
        if (acc.isPresent()){
            newAcc = acc.get();
            newAcc.update(newAccount);
        } else {
            newAcc = new no.hvl.dat250.feedApp.mongoDB.collections.Account();
            newAcc.update(newAccount);
        }
        repositoryAccount.save(newAcc);
        return mapper.toDTO(accountService.updateAccount(newAccount, id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        /*Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> acc = repositoryAccount.findById(id);
        if (acc.isPresent()){
            repositoryAccount.delete(acc.get());
        }*/
        accountService.deleteAccount(id);
    }

    @PostMapping("/users/{userId}/newPoll")
    public PollDTO makeNewPoll(@PathVariable Long userId, @RequestBody PollCreationDTO pollCreationDTO) {
        Poll poll = mapper.toDTO(pollCreationDTO);
        Poll temp = accountService.makeNewPoll(poll, userId);

        no.hvl.dat250.feedApp.mongoDB.collections.Poll mongoPoll = new no.hvl.dat250.feedApp.mongoDB.collections.Poll();
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> mongoAcc = repositoryAccount.findById(userId);

        if (mongoAcc.isPresent()){
            mongoPoll.setId(temp.getId());
            mongoPoll.setAccount(mongoAcc.get());
            mongoPoll.update(poll);
            repositoryPoll.save(mongoPoll);
        }
        else {
            System.out.println("___________________________________________");
            System.out.println("userId: " + userId);

        }


        return mapper.toDTO(temp);
    }
}
