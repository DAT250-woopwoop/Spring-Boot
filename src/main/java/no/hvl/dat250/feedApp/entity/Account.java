package no.hvl.dat250.feedApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private transient String password;
    private String e_mail;
    private String f_name;
    private String l_name;

    public Account() {}

    public Account(String username, String password, String e_mail, String f_name, String l_name) {
        this.username = username;
        this.password = password;
        this.e_mail = e_mail;
        this.f_name = f_name;
        this.l_name = l_name;
    }

    /*@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Poll> polls = new ArrayList<>();

    public void addPolls(Poll poll) {
        polls.add(poll);
    }*/

    public String toString(){
        return "User{ id = " + this.id + ", name = " + this.f_name +" "+ this.l_name + ", e_mail = " +
        this.e_mail + ", Username = " + this.username + " }";
    }
}
