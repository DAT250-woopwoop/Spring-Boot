package no.hvl.dat250.feedApp;

import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.entity.Poll;
import no.hvl.dat250.feedApp.reposetory.AccountRepository;
import no.hvl.dat250.feedApp.reposetory.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;


@Configuration
public class LoadDatabase {
    /*
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final Timestamp startTime = new Timestamp(System.currentTimeMillis()); //Timestamp.valueOf("10.11.21");
    private final Timestamp endTime = new Timestamp(System.currentTimeMillis());
    private final Poll poll =  new Poll("Second poll", "WDYM", startTime, endTime, false, false, 11,3 );
    private final Account account = new Account("OlaNordmann", "Password", "ola@mail.com", "Ola", "Nordmann", poll);

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, PollRepository pollRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.save(account));
            //log.info("Preloading " + pollRepository.save(poll));
        };
    }

     */

}
