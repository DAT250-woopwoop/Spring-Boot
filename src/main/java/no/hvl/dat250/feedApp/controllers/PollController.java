package no.hvl.dat250.feedApp.controllers;

import no.hvl.dat250.feedApp.entity.Poll;
import no.hvl.dat250.feedApp.reposetory.PollRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PollController {
    private final PollRepository repository;

    public PollController(PollRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/polls")
    public List<Poll> findAll() {
        return repository.findAll();
    }

    @GetMapping("/polls/{id}")
    public Poll one(@PathVariable Long id){
        Optional<Poll> poll = repository.findById(id);
        if (poll.isPresent()) {
            return poll.get();
        }
        return null;
    }

    @PostMapping("/newPoll")
    public Poll newPoll(@RequestBody Poll poll) {
        return repository.save(poll);
    }

    @PutMapping("polls/{id}")
    public Poll updatePoll(@RequestBody Poll newPoll, @PathVariable Long id) {
        return repository.findById(id).map(poll -> {
            poll.setPollDesc(newPoll.getPollDesc());
            poll.setPollName(newPoll.getPollName());
            poll.setStartTime(newPoll.getStartTime());
            poll.setEndTime(newPoll.getEndTime());
            poll.setTimeLimit(newPoll.getTimeLimit());
            poll.setPrivatePoll(newPoll.isPrivatePoll());
            poll.setClosed(newPoll.isClosed());
            poll.setYesOption(newPoll.getYesOption());
            poll.setNoOption(newPoll.getNoOption());

            return repository.save(poll);
        }).orElseGet(() -> {
            newPoll.setId(id);
            return repository.save(newPoll);
        });
    }

    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
