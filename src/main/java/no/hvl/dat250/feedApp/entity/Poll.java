package no.hvl.dat250.feedApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.*;
import java.util.*;
import java.util.function.*;

@Entity
@Getter
@Setter
public class Poll extends Updatable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pollDesc;
    private String pollName;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean privatePoll;
    private boolean closed;

    @OneToMany(mappedBy = "poll")
    private List<PollVote> votes;

    @ManyToOne
    private Account account;

    public Poll(){}

    public Poll(String pollDesc, String pollName, Timestamp startTime, Timestamp endTime,
                Boolean privatePoll, Boolean closed) {
        this.pollDesc = pollDesc;
        this.pollName = pollName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privatePoll = privatePoll;
        this.closed = closed;
        this.votes = new ArrayList<>();

    }

    public void update(Poll updatedPoll) {
        setIfNotNull(this::setAccount, updatedPoll.getAccount());
        setIfNotNull(this::setPollDesc, updatedPoll.getPollDesc());
        setIfNotNull(this::setPollName, updatedPoll.getPollName());
        setIfNotNull(this::setStartTime, updatedPoll.getStartTime());
        setIfNotNull(this::setEndTime, updatedPoll.getEndTime());
        setIfNotNull(this::setPrivatePoll, updatedPoll.isPrivatePoll());
        setIfNotNull(this::setClosed, updatedPoll.isClosed());
        setIfNotNull(this::setVotes, updatedPoll.getVotes());
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", pollDesc='" + pollDesc + '\'' +
                ", pollName='" + pollName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", privatePoll=" + privatePoll +
                ", closed=" + closed +
                ", votes=" + votes +
                ", account=" + account +
                '}';
    }
}
