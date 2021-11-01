package no.hvl.dat250.feedApp.reposetory;

import no.hvl.dat250.feedApp.entity.*;
import org.springframework.data.jpa.repository.*;

public interface PollVoteRepository extends JpaRepository<PollVote, Long> {
}
