package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.entity.Poll;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/polls")
    public List<Poll> findAll() {
        return pollService.findAll();
    }

    @GetMapping("/polls/{id}")
    public Poll findPollById(@PathVariable Long id){
        return pollService.find(id);
    }

    @PutMapping("polls/{id}")
    public Poll updatePoll(@RequestBody Poll update, @PathVariable Long id) {
        return pollService.update(id, update);
    }

    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable Long id) {
        pollService.delete(id);
    }

}
