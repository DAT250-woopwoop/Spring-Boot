package no.hvl.dat250.feedApp.reposetory;

import no.hvl.dat250.feedApp.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>{


}
