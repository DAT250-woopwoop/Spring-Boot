package no.hvl.dat250.feedApp.dto;

import no.hvl.dat250.feedApp.entity.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.*;

public class Mapper {

    public AccountDTO toDTO(Account account) {
        String username = account.getUsername();
        String f_name = account.getF_name();
        String l_name = account.getL_name();
        String e_mail = account.getE_mail();
        List<Long> polls = account
                .getPolls()
                .stream()
                .map(Poll::getId)
                .collect(Collectors.toList());
        return new AccountDTO(username, e_mail, f_name, l_name, polls);
    }
    public PollDTO toDTO(Poll poll) {
        String pollDesc = poll.getPollDesc();
        String pollName = poll.getPollName();
        Date startTime = poll.getStartTime();
        Date endTime = poll.getEndTime();
        Date timeLimit = poll.getTimeLimit();
        boolean privatePoll = poll.isPrivatePoll();
        boolean closed = poll.isClosed();
        int yesOption = poll.getYesOption();
        int noOption = poll.getNoOption();
        long accountId = poll.getAccount().getId();

        return new PollDTO(pollDesc, pollName, startTime, endTime, timeLimit, privatePoll, closed, yesOption, noOption, accountId);
    }
    public Poll toDTO(PollCreationDTO poll) {
        String pollDesc = poll.getPollDesc();
        String pollName = poll.getPollName();
        Date startTime = Date.valueOf(poll.getStartTime());
        Date endTime = Date.valueOf(poll.getEndTime());
        Date timeLimit = Date.valueOf(poll.getTimeLimit());
        boolean privatePoll = poll.isPrivatePoll();
        boolean closed = poll.isClosed();
        int yesOption = poll.getYesOption();
        int noOption = poll.getNoOption();

        return new Poll(pollDesc, pollName, startTime, endTime, timeLimit, privatePoll, closed, yesOption, noOption);

    }
}
