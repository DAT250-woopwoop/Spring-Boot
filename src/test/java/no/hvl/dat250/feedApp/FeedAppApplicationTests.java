package no.hvl.dat250.feedApp;

import no.hvl.dat250.feedApp.controller.AccountController;
import no.hvl.dat250.feedApp.controller.PollController;
import no.hvl.dat250.feedApp.controller.PollVoteController;
import no.hvl.dat250.feedApp.service.AccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FeedAppApplicationTests {

	@Autowired
	private AccountController accountController;
	@Autowired
	private PollController pollController;
	@Autowired
	private PollVoteController pollVoteController;

	@Test
	public void accountContextLoads() throws Exception {
		assertThat(accountController).isNotNull();
	}

	@Test
	public void pollContextLoads() throws Exception {
		assertThat(pollController).isNotNull();
	}

	@Test
	public void pollVoteContextLoads() throws Exception {
		assertThat(pollVoteController).isNotNull();
	}

}
