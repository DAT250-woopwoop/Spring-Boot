package no.hvl.dat250.feedApp.mongoDB.controller;

//import no.hvl.dat250.feedApp.dto.PollDTO;
//import no.hvl.dat250.feedApp.entity.Poll;
//import no.hvl.dat250.feedApp.entity.PollVote;
import no.hvl.dat250.feedApp.mongoDB.collections.Poll;
import no.hvl.dat250.feedApp.mongoDB.collections.PollVote;
import no.hvl.dat250.feedApp.mongoDB.repository.RepositoryPoll;
        import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ControllerPoll {
    private final RepositoryPoll repositoryPoll;
    private final MongoOperations mongoOperations;

    //private final Mapper mapper = new Mapper(); // TODO: 25/10/2021 Should be bean(?)

    public ControllerPoll(RepositoryPoll pollService, MongoOperations mongoOperations) {
        this.repositoryPoll = pollService;
        this.mongoOperations = mongoOperations;
    }

    @GetMapping("/mongodb/polls")
    public List<Poll> findAll() {
        return repositoryPoll.findAll();
    }

    @GetMapping("/mongodb/polls/{id}")
    public Poll findPollById(@PathVariable Long id){
        Optional<Poll> poll = repositoryPoll.findById(id);
        if (poll.isPresent()){
            return poll.get();
        }
        return null;
    }

    @PutMapping("/mongodb/polls/{id}")
    public Poll updatePoll(@RequestBody Poll update, @PathVariable Long id) {
        Optional<Poll> poll = repositoryPoll.findById(id);
        if (poll.isPresent()){
            Poll updatedPoll = poll.get();
            // updatedPoll.setPollName(update.getPollName());
            // updatedPoll.setPollDesc(update.getPollDesc());
            updatedPoll.update(update);
            return updatedPoll;
        }
        return repositoryPoll.save(update);
    }

    @DeleteMapping("/mongodb/polls/{id}")
    public void deletePoll(@PathVariable Long id) {
        Optional<Poll> poll = repositoryPoll.findById(id);
        if (poll.isPresent()){
            repositoryPoll.delete(poll.get());
        }

    }

    @PostMapping("/mongodb/polls/{userId}/{pollId}")
    public Poll updateYes(@PathVariable Long pollId, @RequestBody PollVote pollVote, @PathVariable Long userId) {
        System.out.println(pollVote);
        org.springframework.data.mongodb.core.query.Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Poll poll = mongoOperations.findOne(query, Poll.class);
        poll.addPollVote(pollVote);
        return repositoryPoll.save(poll);
    }

}
