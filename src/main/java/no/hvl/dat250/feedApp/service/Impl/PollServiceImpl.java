package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.exceptions.*;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoAccountRepository;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoPollRepository;
import no.hvl.dat250.feedApp.mongoDB.repository.MongoPollVoteRepository;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final MongoPollRepository mongoPollRepository;

    private final AccountRepository accountRepository;
    private final MongoAccountRepository mongoAccountRepository;

    private final PollVoteRepository pollVoteRepository;
    private final MongoPollVoteRepository mongoPollVoteRepository;

    public PollServiceImpl(PollRepository pollRepository, AccountRepository accountRepository, PollVoteRepository pollVoteRepository,
                           MongoPollRepository mongoPollRepository, MongoAccountRepository mongoAccountRepository, MongoPollVoteRepository mongoPollVoteRepository) {
        this.pollRepository = pollRepository;
        this.mongoPollRepository = mongoPollRepository;
        this.accountRepository = accountRepository;
        this.mongoAccountRepository = mongoAccountRepository;
        this.pollVoteRepository = pollVoteRepository;
        this.mongoPollVoteRepository = mongoPollVoteRepository;
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
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> optionalMongoPoll = mongoPollRepository.findById(id);

        if (optionalPoll.isEmpty()) throw new NoPollFoundException("No poll with given id");
        Poll storedPoll = optionalPoll.get();

        storedPoll.update(updatedPoll);
        Poll poll = pollRepository.save(storedPoll);

        no.hvl.dat250.feedApp.mongoDB.collections.Poll mongoPoll;
        if (optionalMongoPoll.isPresent()) {
            mongoPoll = optionalMongoPoll.get();
        } else {
            mongoPoll = new no.hvl.dat250.feedApp.mongoDB.collections.Poll();
            mongoPoll.setId(poll.getId());
        }
        mongoPoll.update(poll);
        mongoPollRepository.save(mongoPoll);

        return poll;
    }

    @Override
    public void delete(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> optionalMongoPoll = mongoPollRepository.findById(id);

        if (poll.isEmpty()) throw new NoPollFoundException("No poll with given id");
        pollRepository.delete(poll.get());

        if (optionalMongoPoll.isPresent()){
            mongoPollRepository.delete(optionalMongoPoll.get());
        }
    }

    @Override
    public Poll voted(PollVote pollVote, Long pollId, Long accId) {
        Optional<Account> optionalAccount = accountRepository.findById(accId);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Account> optionalMongoAccount = mongoAccountRepository.findById(accId);
        if (optionalAccount.isEmpty() || optionalMongoAccount.isEmpty()) throw new NoAccountFoundException("No account with given id");

        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> optionalMongoPoll = mongoPollRepository.findById(pollId);
        if (optionalPoll.isEmpty() || optionalMongoPoll.isEmpty()) throw new NoPollFoundException("No poll with given id");

        if (optionalPoll.get()
                .getVotes()
                .stream()
                .anyMatch(e -> Objects.equals(e.getAccount().getId(), optionalAccount.get().getId()))
        ) throw new NoDuplicateException("Given Account have already voted");

        Account storedAccount = optionalAccount.get();
        no.hvl.dat250.feedApp.mongoDB.collections.Account mongoAccount = optionalMongoAccount.get();

        Poll storedPoll = optionalPoll.get();
        no.hvl.dat250.feedApp.mongoDB.collections.Poll mongoPoll = optionalMongoPoll.get();

        pollVote.setPoll(storedPoll);
        pollVote.setAccount(storedAccount);

        PollVote savedPollVote = pollVoteRepository.saveAndFlush(pollVote);

        no.hvl.dat250.feedApp.mongoDB.collections.PollVote mongoPollVote = new no.hvl.dat250.feedApp.mongoDB.collections.PollVote(
                savedPollVote.getId(),
                mongoPoll,
                mongoAccount
        );
        mongoPollVote.setAnswer(savedPollVote.getAnswer());
        mongoPollVoteRepository.save(mongoPollVote);

        return storedPoll;
    }

}
