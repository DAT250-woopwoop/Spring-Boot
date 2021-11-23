package no.hvl.dat250.feedApp.mongoDB.controller;

import no.hvl.dat250.feedApp.dto.Mapper;
// import no.hvl.dat250.feedApp.dto.PollVoteDTO;
// import no.hvl.dat250.feedApp.service.PollVoteService;
import no.hvl.dat250.feedApp.mongoDB.collections.PollVote;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryPollVote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ControllerPollVote {

    private final RepositoryPollVote repositoryPollVote;
    private final Mapper mapper = new Mapper();

    public ControllerPollVote(RepositoryPollVote pollVoteService) {
        this.repositoryPollVote = pollVoteService;
    }

    @GetMapping("/mongodb/pollvotes")
    public List<PollVote> findAll(){
        return repositoryPollVote.findAll();
    }

    @GetMapping("/mongodb/pollvotes/{id}")
    public PollVote findById(@PathVariable Long id){
        Optional<PollVote> pollVote = repositoryPollVote.findById(id);
        if (pollVote.isPresent()){
            return pollVote.get();
        }
        return null;
    }
}
