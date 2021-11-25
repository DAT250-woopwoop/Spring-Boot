package no.hvl.dat250.feedApp.mongoDB.collections;

import lombok.Getter;
import lombok.Setter;
import no.hvl.dat250.feedApp.entity.Updatable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
@Document(collection = "Account")
public class Account extends Updatable {
    @Id
    private Long id;
    private String username;
    private String password;
    private String e_mail;
    private String f_name;
    private String l_name;

    private List<Poll> polls = new ArrayList<>();

    private List<PollVote> myVotes = new ArrayList<>();;

    public Account() {}

    public Account(String username, String password, String e_mail, String f_name, String l_name) {
        this.username = username;
        this.password = password;
        this.e_mail = e_mail;
        this.f_name = f_name;
        this.l_name = l_name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", polls=" + polls +
                ", myVotes=" + myVotes +
                '}';
    }

    public void update(Account updatedAccount) {
        setIfNotNull(this::setUsername, updatedAccount.getUsername());
        setIfNotNull(this::setPassword, updatedAccount.getPassword());
        setIfNotNull(this::setE_mail, updatedAccount.getE_mail());
        setIfNotNull(this::setF_name, updatedAccount.getF_name());
        setIfNotNull(this::setL_name, updatedAccount.getL_name());
    }

    public void update(no.hvl.dat250.feedApp.entity.Account updatedAccount) {
        setIfNotNull(this::setId, updatedAccount.getId());
        setIfNotNull(this::setUsername, updatedAccount.getUsername());
        setIfNotNull(this::setPassword, updatedAccount.getPassword());
        setIfNotNull(this::setE_mail, updatedAccount.getE_mail());
        setIfNotNull(this::setF_name, updatedAccount.getF_name());
        setIfNotNull(this::setL_name, updatedAccount.getL_name());
    }


}
