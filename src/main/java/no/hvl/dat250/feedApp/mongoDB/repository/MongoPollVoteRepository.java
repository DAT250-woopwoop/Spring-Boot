package no.hvl.dat250.feedApp.mongoDB.repository;

import no.hvl.dat250.feedApp.mongoDB.collections.PollVote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPollVoteRepository extends MongoRepository<PollVote, Long> {
}
