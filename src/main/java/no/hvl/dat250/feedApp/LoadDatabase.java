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


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, PollRepository pollRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.save(new Account("OlaNordmann", "Password", "ola@mail.com", "Ola", "Nordmann")));
            log.info("Preloading " + pollRepository.save(new Poll("Second poll", "WDYM", "10.10.21", "11.11.21","1", false, false, 0,0 )));
        };
    }

}
