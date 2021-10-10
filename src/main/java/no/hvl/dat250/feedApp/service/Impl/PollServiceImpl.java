package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    PollRepository pollRepository;

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll find(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);

        return poll.orElse(null);

    }

    @Override
    public Poll update(Long id, Poll updatedPoll) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) return null;
        Poll storedPoll = poll.get();

        storedPoll.update(updatedPoll);
        return pollRepository.save(storedPoll);
    }

    @Override
    public void delete(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty()) return;

        pollRepository.delete(poll.get());
    }
}
