package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.entity.Poll;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PollController {
    private final PollService pollService;
    private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be bean(?)

    public PollController(PollService pollService) {
        this.pollService = pollService;
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
        return mapper.toDTO(pollService.update(id, update));
    }

    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable Long id) {
        pollService.delete(id);
    }

    @PutMapping("polls/{id}/yes")
    public PollDTO updateYes(@PathVariable Long id) {
        return mapper.toDTO(pollService.votedYes(id));
    }

    @PutMapping("polls{id}/no")
    public PollDTO updateNo(@PathVariable Long id) {
        return mapper.toDTO(pollService.votedNo(id));
    }


}
