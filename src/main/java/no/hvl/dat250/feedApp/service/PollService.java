package no.hvl.dat250.feedApp.service;

import no.hvl.dat250.feedApp.entity.*;

import java.util.*;

public interface PollService {

    List<Poll> findAll();

    Poll find(Long id);

    Poll update(Long id, Poll updatedPoll);

    void delete(Long id);

    Poll votedYes(Long id);
}
