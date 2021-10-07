package no.hvl.dat250.feedApp.controllers;

import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.reposetory.AccountRepository;
import org.apache.derby.diag.ErrorMessages;
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

    @PostMapping("/users")
    public Account newAccount(@RequestBody Account account) {
        return repository.save(account);
    }

    @PutMapping("users/{id}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        return repository.findById(id).map(account -> {
            account.setE_mail(newAccount.getE_mail());
            account.setF_name(newAccount.getF_name());
            account.setL_name(newAccount.getL_name());
            account.setUsername(newAccount.getUsername());
            account.setPassword(newAccount.getPassword());
            return repository.save(account);
        }).orElseGet(() -> {
            newAccount.setId(id);
            return repository.save(newAccount);
        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
