package no.hvl.dat250.feedApp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class PollVote extends Updatable{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Answer answer;

    @ManyToOne()
    private Poll poll;

    @ManyToOne()
    private Account account;

    public PollVote(Long id, Poll poll, Account account) {
        this.id = id;
        this.poll = poll;
        this.account = account;
    }

    public PollVote() {

    }

}
