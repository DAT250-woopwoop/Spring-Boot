package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.reposetory.*;
import no.hvl.dat250.feedApp.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.mockito.quality.*;

import java.sql.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class PollServiceImplTest {


    @Mock
    private PollRepository pollRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PollVoteRepository pollVoteRepository;

    private PollService pollService;

    private final String pollDesc = "Dette er en test poll";
    private final String pollName = "Test";
    private final String startDate = "2021-03-30 18:10:00";
    private final String endDate = "2021-03-30 18:10:00";
    private final boolean privatePoll = false;
    private final boolean closed = false;
    private Poll testPoll = new Poll(pollDesc, pollName, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), privatePoll, closed);

    @BeforeEach
    void setUp() {
        this.pollService = new PollServiceImpl(pollRepository, accountRepository,pollVoteRepository);
    }

    @Test
    void findAll() {
        Mockito.when(pollRepository.findAll()).thenReturn(List.of());

        List<Poll> found = pollService.findAll();

        assertThat(found).isNotNull();
        assertThat(found.size()).isEqualTo(0);

        Mockito.when(pollRepository.findAll()).thenReturn(List.of(testPoll));

        List<Poll> found1 = pollService.findAll();

        assertThat(found1.size()).isEqualTo(1);
    }

    @Test
    void findWithElementInRepository() {
        Mockito.when(pollRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(testPoll));

        Poll found = pollService.find(1L);

        assertThat(found).isNotNull();
        assertThat(found.getPollDesc()).isEqualTo(pollDesc);
        assertThat(found.getPollName()).isEqualTo(pollName);
        assertThat(found.getEndTime()).isEqualTo(Timestamp.valueOf(endDate));
        assertThat(found.getStartTime()).isEqualTo(Timestamp.valueOf(startDate));
        assertThat(found.isClosed()).isFalse();
        assertThat(found.isPrivatePoll()).isFalse();
        assertThat(found.getVotes().size()).isEqualTo(0);

    }

    @Test
    void findWithNoElementInRepository() {
        Mockito.when(pollRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Poll found = pollService.find(1L);

        assertThat(found).isNull();
    }

    @Test
    void votedWithNoAccount() {
        Mockito.when(pollRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        PollVote pollVote = new PollVote(1L, testPoll, null);
        Poll poll = pollService.voted(pollVote, 1L, 1L);

        assertThat(poll).isNull();
    }

}