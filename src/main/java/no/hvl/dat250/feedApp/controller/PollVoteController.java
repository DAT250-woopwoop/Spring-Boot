package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
public class PollVoteController {

    private final PollVoteService pollVoteService;
    private final Mapper mapper = new Mapper();

    public PollVoteController(PollVoteService pollVoteService) {
        this.pollVoteService = pollVoteService;
    }

    @GetMapping("/pollvotes")
    public List<PollVoteDTO> findAll(){
        return pollVoteService
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
