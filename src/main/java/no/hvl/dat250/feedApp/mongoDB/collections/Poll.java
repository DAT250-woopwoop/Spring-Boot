package no.hvl.dat250.feedApp.mongoDB.collections;

import lombok.Getter;
import lombok.Setter;
import no.hvl.dat250.feedApp.entity.Updatable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Poll")
public class Poll extends Updatable {

    @Id
    private Long id;

    private String pollDesc;
    private String pollName;
    private Date startTime;
    private Date endTime;
    private boolean privatePoll;
    private boolean closed;

    private List<PollVote> votes = new ArrayList<>();

    private Account account;

    public Poll(){

    }

    public Poll(String pollDesc, String pollName, Date startTime, Date endTime,
                Boolean privatePoll, Boolean closed) {
        this.pollDesc = pollDesc;
        this.pollName = pollName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privatePoll = privatePoll;
        this.closed = closed;

    }

    public void addPollVote(PollVote pollVote){
        this.votes.add(pollVote);
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

    public void update(no.hvl.dat250.feedApp.entity.Poll updatedPoll) {
        setIfNotNull(this::setPollDesc, updatedPoll.getPollDesc());
        setIfNotNull(this::setPollName, updatedPoll.getPollName());
        setIfNotNull(this::setStartTime, updatedPoll.getStartTime());
        setIfNotNull(this::setEndTime, updatedPoll.getEndTime());
        setIfNotNull(this::setPrivatePoll, updatedPoll.isPrivatePoll());
        setIfNotNull(this::setClosed, updatedPoll.isClosed());
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
               // ", votes=" + votes +
                ", account=" + account +
                '}';
    }
}
