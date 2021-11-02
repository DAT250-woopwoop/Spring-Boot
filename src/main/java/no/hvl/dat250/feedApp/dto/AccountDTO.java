package no.hvl.dat250.feedApp.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class AccountDTO {

    private Long id;
    private String username;
    private String e_mail;
    private String f_name;
    private String l_name;
    private List<Long> polls;
    private List<Long> myVotes;

    public AccountDTO(Long id, String username, String e_mail, String f_name, String l_name, List<Long> polls, List<Long> myVotes) {
        this.id = id;
        this.username = username;
        this.e_mail = e_mail;
        this.f_name = f_name;
        this.l_name = l_name;
        this.polls = polls;
        this.myVotes = myVotes;

    }
}
