package no.hvl.dat250.feedApp.dto;

import lombok.*;

import java.sql.*;
import java.util.*;


@Getter
@Setter
public class PollDTO {
    private Long id;
    private String pollDesc;
    private String pollName;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean privatePoll;
    private boolean closed;
    private List<Long> answers;

    private Long accountId;

    public PollDTO(Long id, String pollDesc, String pollName, Timestamp startTime, Timestamp endTime,
                   boolean privatePoll, boolean closed, List<Long> answers, Long accountId) {
        this.id = id;
        this.pollDesc = pollDesc;
        this.pollName = pollName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privatePoll = privatePoll;
        this.closed = closed;
        this.answers = answers;
        this.accountId = accountId;
    }
}

