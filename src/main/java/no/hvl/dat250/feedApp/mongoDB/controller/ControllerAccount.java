package no.hvl.dat250.feedApp.mongoDB.controller;

//import no.hvl.dat250.feedApp.dto.AccountDTO;
//import no.hvl.dat250.feedApp.dto.Mapper;
//import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.mongoDB.collections.Account;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryAccount;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControllerAccount {

    private final RepositoryAccount repositoryAccount;

    // private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be a bean(?)

    ControllerAccount(RepositoryAccount accountService){
        this.repositoryAccount = accountService;
    }

    @GetMapping("/mongodb/users")
    public List<no.hvl.dat250.feedApp.mongoDB.collections.Account> all() {
        return repositoryAccount.findAll();
    }

    @GetMapping("/mongodb/users/{id}")
    public Account findAccountById(@PathVariable Long id) {
        Optional<Account> acc = repositoryAccount.findById(id);
        if (acc.isPresent()) {
            return acc.get();
        }
        return null;
    }

    @PostMapping("/mongodb/users/signup")
    public Account newAccount(@RequestBody Account account) {
        return repositoryAccount.save(account);

    }

    @PutMapping("/mongodb/users/{id}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        Optional<Account> account = repositoryAccount.findById(id);
        if (account.isPresent()){
            Account updatedAccount = account.get();
            updatedAccount.update(newAccount);
            return updatedAccount;
        }
        return repositoryAccount.save(newAccount);
    }

    @DeleteMapping("/mongodb/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        Optional<Account> acc = repositoryAccount.findById(id);
        if (acc.isPresent()){
            repositoryAccount.delete(acc.get());
        }


    }

    /*@PostMapping("/users/{userId}/newPoll")
    public PollDTO makeNewPoll(@PathVariable Long userId, @RequestBody PollCreationDTO pollCreationDTO) {
        Poll poll = mapper.toDTO(pollCreationDTO);
        return mapper.toDTO(repositoryAccount.makeNewPoll(poll, userId));
    }*/
}
