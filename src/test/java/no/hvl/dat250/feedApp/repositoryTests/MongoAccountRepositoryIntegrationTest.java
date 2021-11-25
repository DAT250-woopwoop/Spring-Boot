package no.hvl.dat250.feedApp.repositoryTests;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MongoAccountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByUsername_thenReturnAccount() {

        // given
        Account mathias = new Account("mathiasJacobsen",
                "password123", "example@apple.no",
                "Mathias", "Jacobsen");

        entityManager.persist(mathias);
        entityManager.flush();

        // when
        Account found = accountRepository.findByUsername(mathias.getUsername());

        // then
        assertThat(found.getUsername()).isEqualTo(mathias.getUsername());
        assertThat(found.getPassword()).isEqualTo(mathias.getPassword());
        assertThat(found.getE_mail()).isEqualTo(mathias.getE_mail());
        assertThat(found.getF_name()).isEqualTo(mathias.getF_name());
        assertThat(found.getL_name()).isEqualTo(mathias.getL_name());
    }
}
