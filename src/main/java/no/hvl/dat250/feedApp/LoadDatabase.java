package no.hvl.dat250.feedApp;

import no.hvl.dat250.feedApp.entity.Account;
import no.hvl.dat250.feedApp.reposetory.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("OlaNordmann", "Password", "ola@mail.com", "Ola", "Nordmann")));
        };
    }
}
