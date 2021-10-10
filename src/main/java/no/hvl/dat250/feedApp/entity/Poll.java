package no.hvl.dat250.feedApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.function.*;

@Entity
@Getter
@Setter
public class Poll {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pollDesc;
    private String pollName;
    private String startTime; //TODO: Change to given best time class
    private String endTime;
    private String timeLimit;
    private boolean privatePoll;
    private boolean closed;
    private int yesOption;
    private int noOption;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Poll(){}

    public Poll(String pollDesc, String pollName, String startTime, String endTime,
                String timeLimit, Boolean privatePoll, Boolean closed, int yesOption, int noOption) {
        this.pollDesc = pollDesc;
        this.pollName = pollName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeLimit = timeLimit;
        this.privatePoll = privatePoll;
        this.closed = closed;
        this.yesOption = yesOption;
        this.noOption = noOption;

    }

    public void update(Poll updatedPoll) {
        setIfNotNull(this::setAccount, updatedPoll.getAccount());
        setIfNotNull(this::setPollDesc, updatedPoll.getPollDesc());
        setIfNotNull(this::setPollName, updatedPoll.getPollName());
        setIfNotNull(this::setStartTime, updatedPoll.getStartTime());
        setIfNotNull(this::setEndTime, updatedPoll.getEndTime());
        setIfNotNull(this::setTimeLimit, updatedPoll.getEndTime());
        setIfNotNull(this::setPrivatePoll, updatedPoll.isPrivatePoll());
        setIfNotNull(this::setClosed, updatedPoll.isClosed());
        setIfNotNull(this::setYesOption, updatedPoll.getYesOption());
        setIfNotNull(this::setNoOption, updatedPoll.getNoOption());
    }

    private <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", pollDesc='" + pollDesc + '\'' +
                ", pollName='" + pollName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", privatePoll=" + privatePoll +
                ", closed=" + closed +
                ", yesOption=" + yesOption +
                ", noOption=" + noOption +
                ", account=" + account +
                '}';
    }
}
