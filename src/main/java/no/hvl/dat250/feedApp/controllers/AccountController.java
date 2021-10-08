package no.hvl.dat250.feedApp.controllers;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService accountService;
    private final PollRepository pollRepository;

    AccountController(PollRepository pollRepository, AccountService accountService){
        this.accountService = accountService;
        this.pollRepository = pollRepository;
    }

    @GetMapping("/users")
    public List<Account> all(){
        return accountService.findAll();
    }

    @GetMapping("/users/{id}")
    public Account findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PostMapping("/users")
    public Account newAccount(@RequestBody Account account) {
        return accountService.makeNewAccount(account);
    }

    @PutMapping("users/{id}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        return accountService.updateAccount(newAccount, id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/users/{userId}/newPoll")
    public Poll makeNewPoll(@PathVariable Long userId, @RequestBody Poll poll) {
        return pollRepository.save(poll); // TODO: 08/10/2021   need to stop recursive column
        // accountService.makeNewPoll(poll, userId);
    }

}
