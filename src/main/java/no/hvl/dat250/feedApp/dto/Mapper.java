package no.hvl.dat250.feedApp.dto;

import no.hvl.dat250.feedApp.entity.*;

import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class
Mapper {

    public AccountDTO toDTO(Account account) {
        long id = account.getId();
        String username = account.getUsername();
        String f_name = account.getF_name();
        String l_name = account.getL_name();
        String e_mail = account.getE_mail();
        List<Long> polls = account
                .getPolls()
                .stream()
                .map(Poll::getId)
                .collect(Collectors.toList());

        return new AccountDTO(id, username, e_mail, f_name, l_name, polls);
    }
    public PollDTO toDTO(Poll poll) {
        long id = poll.getId();
        String pollDesc = poll.getPollDesc();
        String pollName = poll.getPollName();
        Timestamp startTime = poll.getStartTime();
        Timestamp endTime = poll.getEndTime();
        boolean privatePoll = poll.isPrivatePoll();
        boolean closed = poll.isClosed();
        List<Long> answers = poll
                .getVotes()
                .stream()
                .map(PollVote::getId)
                .collect(Collectors.toList());
        long accountId = poll.getAccount().getId();
        return new PollDTO(id,  pollDesc, pollName, startTime, endTime, privatePoll, closed, answers, accountId);
    }
    public Poll toDTO(PollCreationDTO poll) {
        String pollDesc = poll.getPollDesc();
        String pollName = poll.getPollName();
        Timestamp startTime = Timestamp.valueOf(poll.getStartTime());
        Timestamp endTime = Timestamp.valueOf(poll.getEndTime());
        boolean privatePoll = poll.isPrivatePoll();
        boolean closed = poll.isClosed();

        return new Poll(pollDesc, pollName, startTime, endTime, privatePoll, closed);
    }

    public PollVoteDTO toDTO(PollVote pollVote) {
        Long id = pollVote.getId();
        Answer answer = pollVote.getAnswer();
        Long pollId = pollVote.getId();
        Long accountId = pollVote.getAccount().getId();

        return new PollVoteDTO(id, answer, pollId, accountId);
    }
}
