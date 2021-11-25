package no.hvl.dat250.feedApp.mongoDB.controller;

//import no.hvl.dat250.feedApp.dto.AccountDTO;
//import no.hvl.dat250.feedApp.dto.Mapper;
//import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.mongoDB.collections.Account;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MongoAccountController {

    private final MongoAccountRepository mongoAccountRepository;

    // private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be a bean(?)

    MongoAccountController(MongoAccountRepository accountService){
        this.mongoAccountRepository = accountService;
    }

    @GetMapping("/mongodb/users")
    public List<no.hvl.dat250.feedApp.mongoDB.collections.Account> all() {
        return mongoAccountRepository.findAll();
    }

    @GetMapping("/mongodb/users/{id}")
    public Account findAccountById(@PathVariable Long id) {
        Optional<Account> acc = mongoAccountRepository.findById(id);
        if (acc.isPresent()) {
            return acc.get();
        }
        return null;
    }

    @PostMapping("/mongodb/users/signup")
    public Account newAccount(@RequestBody Account account) {
        return mongoAccountRepository.save(account);

    }

    @PutMapping("/mongodb/users/{id}")
    public Account updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        Optional<Account> account = mongoAccountRepository.findById(id);
        if (account.isPresent()){
            Account updatedAccount = account.get();
            updatedAccount.update(newAccount);
            return updatedAccount;
        }
        return mongoAccountRepository.save(newAccount);
    }

    @DeleteMapping("/mongodb/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        Optional<Account> acc = mongoAccountRepository.findById(id);
        if (acc.isPresent()){
            mongoAccountRepository.delete(acc.get());
        }


    }

    /*@PostMapping("/users/{userId}/newPoll")
    public PollDTO makeNewPoll(@PathVariable Long userId, @RequestBody PollCreationDTO pollCreationDTO) {
        Poll poll = mapper.toDTO(pollCreationDTO);
        return mapper.toDTO(mongoAccountRepository.makeNewPoll(poll, userId));
    }*/
}
