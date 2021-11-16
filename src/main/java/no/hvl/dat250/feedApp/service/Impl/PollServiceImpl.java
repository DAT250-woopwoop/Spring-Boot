package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.exceptions.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    private final AccountRepository accountRepository;

    private final PollVoteRepository pollVoteRepository;

    public PollServiceImpl(PollRepository pollRepository, AccountRepository accountRepository, PollVoteRepository pollVoteRepository) {
        this.pollRepository = pollRepository;
        this.accountRepository = accountRepository;
        this.pollVoteRepository = pollVoteRepository;
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll find(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) throw new NoPollFoundException("No poll with given id");
        return poll.orElse(null);

    }

    @Override
    public Poll update(Long id, Poll updatedPoll) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) throw new NoPollFoundException("No poll with given id");
        Poll storedPoll = poll.get();

        storedPoll.update(updatedPoll);
        return pollRepository.save(storedPoll);
    }

    @Override
    public void delete(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) throw new NoPollFoundException("No poll with given id");

        pollRepository.delete(poll.get());
    }

    @Override
    public Poll voted(PollVote pollVote, Long pollId, Long accId) {
        Optional<Account> account = accountRepository.findById(accId);
        if (account.isEmpty()) throw new NoAccountFoundException("No account with given id");

        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll.isEmpty()) throw new NoPollFoundException("No poll with given id");

        if (poll.get()
                .getVotes()
                .stream()
                .anyMatch(e -> Objects.equals(e.getAccount().getId(), account.get().getId()))
        ) throw new NoDuplicateException("Given Account have already voted");

        Account storedAccount = account.get();
        Poll storedPoll = poll.get();

        pollVote.setPoll(storedPoll);
        pollVote.setAccount(storedAccount);

        pollVoteRepository.saveAndFlush(pollVote);
        return storedPoll;
    }

}
