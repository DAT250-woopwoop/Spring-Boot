package no.hvl.dat250.feedApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account extends Updatable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String e_mail;
    private String f_name;
    private String l_name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
    private List<Poll> polls = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
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


}
