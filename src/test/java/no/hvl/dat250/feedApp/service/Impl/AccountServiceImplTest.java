package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.crypto.bcrypt.*;

import java.sql.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {


    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PollRepository pollRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AccountService accountService;

    private final String passord = "Passord";
    private final String e_mail = "test@test.no";
    private final String forname = "Ola";
    private final String lName = "Testet";
    private final String username = "Username";

    private Account alex = new Account(username, passord, e_mail, forname, lName);


    @BeforeEach
    public void setUp() {
        this.accountService = new AccountServiceImpl(accountRepository, pollRepository, bCryptPasswordEncoder);

    }


    @Test
    void makeNewAccount() {
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(alex);

        Account found = accountService.makeNewAccount(alex);

        assertThat(found.getUsername()).isEqualTo(username);
        assertThat(found.getL_name()).isEqualTo(lName);
        assertThat(found.getF_name()).isEqualTo(forname);
        assertThat(found.getE_mail()).isEqualTo(e_mail);

    }

    @Test
    void findAll() {
        Mockito.when(accountRepository.findAll()).thenReturn(List.of(alex));


        List<Account> found = accountService.findAll();
        assertThat(found.size() == 1).isTrue();
        assertThat(found.get(0)).isEqualTo(alex);
    }


    @Test
    void findAccountById() {
        Mockito.when(accountRepository.findById(any(Long.class))).thenReturn(Optional.of(alex));

        Account found = accountService.findAccountById(1L);

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(username);
        assertThat(found.getL_name()).isEqualTo(lName);
        assertThat(found.getF_name()).isEqualTo(forname);
        assertThat(found.getE_mail()).isEqualTo(e_mail);
    }

    @Test
    void updateAccount() {
        String username = "Updated";
        String passord = "Dette er et passord";
        String eMail = "mail@mail.no";
        String fName = "Mathias";
        String lName = "test";
        Account updatedAccount = new Account(username, passord, eMail, fName, lName);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(updatedAccount));
        Mockito.when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);

        Account found = accountService.updateAccount(updatedAccount, 1L);

        assertThat(found.getUsername()).isEqualTo(username);
        assertThat(found.getL_name()).isEqualTo(lName);
        assertThat(found.getF_name()).isEqualTo(fName);
        assertThat(found.getE_mail()).isEqualTo(eMail);
        assertThat(found.getPassword()).isEqualTo(passord);

    }

    @Test
    void makeNewPoll() {

        Poll poll = new Poll("Dette er en test poll", "Test", Timestamp.valueOf("2021-03-30 18:10:00"), Timestamp.valueOf("2021-03-30 18:10:00"), false, false);

        Mockito.when(accountRepository.findById(any(Long.class))).thenReturn(Optional.of(alex));
        Mockito.when(pollRepository.saveAndFlush(any(Poll.class))).thenReturn(poll);
        Poll found = accountService.makeNewPoll(poll, 1L);

        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(poll);


    }

    @Test
    void getAccountByUsername() {
        Mockito.when(accountRepository.findByUsername(any(String.class))).thenReturn(alex);

        Account found = accountService.getAccountByUsername(username);

        assertThat(found.getUsername()).isEqualTo(username);
        assertThat(found.getL_name()).isEqualTo(lName);
        assertThat(found.getF_name()).isEqualTo(forname);
        assertThat(found.getE_mail()).isEqualTo(e_mail);
        assertThat(found.getPassword()).isEqualTo(passord);
    }


}