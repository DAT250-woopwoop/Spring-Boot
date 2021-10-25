package no.hvl.dat250.feedApp.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class AccountDTO {

    private String username;
    private String e_mail;
    private String f_name;
    private String l_name;
    private List<Long> polls;

    public AccountDTO(String username, String e_mail, String f_name, String l_name, List<Long> polls) {
        this.username = username;
        this.e_mail = e_mail;
        this.f_name = f_name;
        this.l_name = l_name;
        this.polls = polls;
    }
}
