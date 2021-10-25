package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AccountController {


    private final AccountService accountService;
    private final PollRepository pollRepository;
    private final Mapper mapper = new Mapper();

    AccountController(PollRepository pollRepository, AccountService accountService){
        this.accountService = accountService;
        this.pollRepository = pollRepository;
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

    @PostMapping("/users")
    public AccountDTO newAccount(@RequestBody Account account) {
        return mapper.toDTO(accountService.makeNewAccount(account));

    }

    @PutMapping("users/{id}")
    public AccountDTO updateAccount(@RequestBody Account newAccount, @PathVariable Long id) {
        return mapper.toDTO(accountService.updateAccount(newAccount, id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/users/{userId}/newPoll")
    public Poll makeNewPoll(@PathVariable Long userId, @RequestBody Poll poll) {
        //return pollRepository.save(poll); // TODO: 08/10/2021   need to stop recursive column
         return accountService.makeNewPoll(poll, userId);
    }


}
