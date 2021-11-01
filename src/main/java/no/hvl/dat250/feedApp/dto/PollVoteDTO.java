package no.hvl.dat250.feedApp.dto;

import lombok.*;
import no.hvl.dat250.feedApp.entity.*;

@Data
public class PollVoteDTO {
    private Long id;
    private Answer answer;
    private Long pollId;
    private Long accountId;

    public PollVoteDTO(Long id, Answer answer, Long pollId, Long accountId) {
        this.id = id;
        this.answer = answer;
        this.pollId = pollId;
        this.accountId = accountId;
    }
}
