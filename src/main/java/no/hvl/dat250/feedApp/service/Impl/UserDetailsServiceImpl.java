package no.hvl.dat250.feedApp.service.Impl;

import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null) {
            //throw noe
            throw new RuntimeException();
        }
        Account account = accountService.getAccountByUsername(username);

        User.UserBuilder user = User.builder();
        user.username(username);
        user.password(account.getPassword());
        user.authorities(getAuthority());
        return user.build();
    }

    private Collection<? extends GrantedAuthority> getAuthority() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
