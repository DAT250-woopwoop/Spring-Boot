package no.hvl.dat250.feedApp.service;

import no.hvl.dat250.feedApp.dto.PollCreationDTO;
import no.hvl.dat250.feedApp.entity.*;

import java.util.*;

public interface PollVoteService {

    List<PollVote> findAll();

    PollVote find(Long id);
}
