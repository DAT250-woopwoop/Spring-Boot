package no.hvl.dat250.feedApp.dto;

import lombok.*;

import java.sql.*;


@Getter
@Setter
public class PollDTO {
    private String pollDesc;
    private String pollName;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean privatePoll;
    private boolean closed;
    private int yesOption;
    private int noOption;

    private Long accountId;

    public PollDTO(String pollDesc, String pollName, Timestamp startTime, Timestamp endTime, boolean privatePoll, boolean closed, int yesOption, int noOption, Long accountId) {
        this.pollDesc = pollDesc;
        this.pollName = pollName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privatePoll = privatePoll;
        this.closed = closed;
        this.yesOption = yesOption;
        this.noOption = noOption;
        this.accountId = accountId;
    }
}

