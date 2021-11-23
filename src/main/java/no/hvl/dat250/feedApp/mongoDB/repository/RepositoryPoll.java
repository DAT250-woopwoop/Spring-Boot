package no.hvl.dat250.feedApp.mongoDB.repository;

import no.hvl.dat250.feedApp.mongoDB.collections.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryPoll extends MongoRepository<Poll, Long> {
}
