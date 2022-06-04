package com.great.MiquelMontoro.pilotes.security.providers;

import com.great.MiquelMontoro.pilotes.security.repo.UserRepo;
import com.great.MiquelMontoro.pilotes.security.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Transactional
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        Optional<User> user = userRepo.findUserByUsername(username);

        if(!user.isPresent()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        if(!passwordEncoder.matches(password, user.get().getPassword())) {
            log.error("Bad credentials");
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority(user.get().getAuthority())));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
