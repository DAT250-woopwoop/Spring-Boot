package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PollVoteServiceImpl implements PollVoteService {

    @Autowired
    PollVoteRepository pollVoteRepository;

    @Override
    public List<PollVote> findAll() {
        return pollVoteRepository.findAll();
    }

    @Override
    public PollVote find(Long id) {
        Optional<PollVote> pollVote = pollVoteRepository.findById(id);
        return pollVote.orElse(null);
    }
}
