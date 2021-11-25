package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryAccount;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryPoll;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryPollVote;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PollController {
    private final PollService pollService;
    private final RepositoryPoll repositoryPoll;
    private final RepositoryAccount repositoryAccount;
    private final RepositoryPollVote repositoryPollVote;
    private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be bean(?)

    public PollController(PollService pollService, RepositoryPoll repositoryPoll, RepositoryAccount repositoryAccount, RepositoryPollVote repositoryPollVote) {
        this.pollService = pollService;
        this.repositoryPoll = repositoryPoll;
        this.repositoryAccount = repositoryAccount;
        this.repositoryPollVote = repositoryPollVote;
    }

    @GetMapping("/polls")
    public List<PollDTO> findAll() {
        return pollService.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(toList());
    }

    @GetMapping("/polls/{id}")
    public PollDTO findPollById(@PathVariable Long id){
        return mapper.toDTO(pollService.find(id));
    }

    @PutMapping("polls/{id}")
    public PollDTO updatePoll(@RequestBody Poll update, @PathVariable Long id) {
        Poll poll = pollService.update(id, update);
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> mongoPoll = repositoryPoll.findById(id);
        no.hvl.dat250.feedApp.mongoDB.collections.Poll newPoll;
        if (mongoPoll.isPresent()){
            newPoll = mongoPoll.get();
            newPoll.update(update);
        } else {
            newPoll = new no.hvl.dat250.feedApp.mongoDB.collections.Poll();
            newPoll.setId(poll.getId());
            newPoll.update(update);
        }
        repositoryPoll.save(newPoll);
        return mapper.toDTO(poll);
    }

    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable Long id) {
        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> mongoPoll = repositoryPoll.findById(id);
        if (mongoPoll.isPresent()){
            repositoryPoll.delete(mongoPoll.get());
        }
        pollService.delete(id);
    }

    @PostMapping("polls/{userId}/{pollId}")
    public PollDTO updateYes(@PathVariable Long pollId, @RequestBody PollVote pollVote, @PathVariable Long userId) {
        PollDTO temp = mapper.toDTO(pollService.voted(pollVote, pollId, userId));

        Optional<no.hvl.dat250.feedApp.mongoDB.collections.Poll> mongoPoll = repositoryPoll.findById(pollId);
        if (mongoPoll.isPresent()){
            no.hvl.dat250.feedApp.mongoDB.collections.Poll newMonogPoll = mongoPoll.get();
            no.hvl.dat250.feedApp.mongoDB.collections.Account mongoAcc = new no.hvl.dat250.feedApp.mongoDB.collections.Account();
            mongoAcc = repositoryAccount.findById(userId).get();
            no.hvl.dat250.feedApp.mongoDB.collections.PollVote mongoPollVote
                    = new no.hvl.dat250.feedApp.mongoDB.collections.PollVote(
                            pollVote.getId(),
                            newMonogPoll,
                            mongoAcc
            );

            mongoPollVote.setAnswer(pollVote.getAnswer());

            repositoryPollVote.save(mongoPollVote);

            newMonogPoll.addPollVote(mongoPollVote);
        }

        System.out.println(pollVote);
        return temp;
    }

}
