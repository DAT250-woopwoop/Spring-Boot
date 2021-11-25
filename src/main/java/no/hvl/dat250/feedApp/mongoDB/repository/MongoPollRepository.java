package no.hvl.dat250.feedApp.mongoDB.repository;

import no.hvl.dat250.feedApp.mongoDB.collections.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPollRepository extends MongoRepository<Poll, Long> {
}
