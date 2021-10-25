package no.hvl.dat250.feedApp.dto;

import no.hvl.dat250.feedApp.entity.*;

import java.util.*;
import java.util.stream.*;

public class Mapper {

    public AccountDTO toDTO(Account account){
        String username = account.getUsername();
        String f_name = account.getF_name();
        String l_name = account.getL_name();
        String e_mail = account.getE_mail();
        List<Long> polls = account
                .getPolls()
                .stream()
                .map(Poll::getId)
                .collect(Collectors.toList());
        return new AccountDTO(username, e_mail, f_name, l_name, polls);
    }
}
