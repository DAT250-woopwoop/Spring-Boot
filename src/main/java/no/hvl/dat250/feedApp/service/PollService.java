package no.hvl.dat250.feedApp.service;

import no.hvl.dat250.feedApp.entity.*;

import java.util.*;

public interface PollService {

    Poll makeNewPoll(Long accountId, Poll poll);

    List<Poll> findAll();
}
