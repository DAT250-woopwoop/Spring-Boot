package no.hvl.dat250.feedApp.mongoDB.collections;

import lombok.Getter;
import lombok.Setter;
import no.hvl.dat250.feedApp.entity.Updatable;
import no.hvl.dat250.feedApp.entity.enums.Answer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
@Document(collection = "PollVote")
public class PollVote extends Updatable {
    @Id
    private Long id;

    private Answer answer;

    private Poll poll;

    private Account account;

    public PollVote(Long id, Poll poll, Account account) {
        this.id = id;
        this.poll = poll;
        this.account = account;
    }

    public PollVote() {

    }

    @Override
    public String toString() {
        return "PollVote{" +
                "id=" + id +
                ", answer=" + answer +
                ", poll=" + poll +
                ", account=" + account +
                '}';
    }
}
