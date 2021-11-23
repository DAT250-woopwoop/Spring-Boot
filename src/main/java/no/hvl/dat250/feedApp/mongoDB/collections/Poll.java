package no.hvl.dat250.feedApp.mongoDB.collections;

import lombok.Getter;
import lombok.Setter;
import no.hvl.dat250.feedApp.entity.Updatable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Poll")
public class Poll extends Updatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pollDesc;
    private String pollName;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean privatePoll;
    private boolean closed;

    private List<PollVote> votes;

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
