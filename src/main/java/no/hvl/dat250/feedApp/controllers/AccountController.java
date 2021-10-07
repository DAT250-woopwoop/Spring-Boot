package no.hvl.dat250.feedApp.controllers;

import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.reposetory.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountRepository repository;

    AccountController(AccountRepository repository){
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<Account> all(){
        return repository.findAll();
    }

    @GetMapping("users/{id}")
    public Account one(@PathVariable Long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()){
            return account.get();
        }
        return null;
    }



}
