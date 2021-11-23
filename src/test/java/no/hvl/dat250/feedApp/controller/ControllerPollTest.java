package no.hvl.dat250.feedApp.controller;

import no.hvl.dat250.feedApp.reposetory.PollRepository;
import no.hvl.dat250.feedApp.service.AccountService;
import no.hvl.dat250.feedApp.service.Impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
class ControllerPollTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PollController pollController;
    @MockBean
    private AccountService accountService;
    @MockBean
    private PollRepository pollRepository;
    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Test
    public void notGivenAuthRequestOnPrivateService_shouldBeForbiddenWith403() throws Exception {
        mvc.perform(get("/polls").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


    @WithMockUser(value = "spring")
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/polls").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}