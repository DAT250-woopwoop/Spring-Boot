package no.hvl.dat250.feedApp.reposetory;

import no.hvl.dat250.feedApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String e);
}
