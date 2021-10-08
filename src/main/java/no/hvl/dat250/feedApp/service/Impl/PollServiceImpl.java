package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PollRepository pollRepository;

    @Override
    public Poll makeNewPoll(Long accountId, Poll poll) {
        Account account = accountRepository.getById(accountId);

        poll.setAccount(account);

        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }
}
