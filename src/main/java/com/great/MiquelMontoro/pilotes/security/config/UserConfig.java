package com.great.MiquelMontoro.pilotes.security.config;

import com.great.MiquelMontoro.pilotes.security.model.User;
import com.great.MiquelMontoro.pilotes.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    public static final String PHONE_NUMBER_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
//    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createInMemoryUser() {
        return args -> {
            User user = User.builder()
                    .username("alemari")
                    .password(passwordEncoder.encode("1234"))
                    .authority("CAN_SEARCH")
                    .firstName("Alessandro")
                    .lastName("Marinelli")
                    .phoneNumber("+39 (392) 491-5990")
                    .build();
            User user2 = User.builder()
                    .username("someone")
                    .password(passwordEncoder.encode("5678"))
                    .authority("CAN_NOT_SEARCH")
                    .firstName("Alessandro")
                    .lastName("Marinelli")
                    .phoneNumber("+1 (306) 280-6587")
                    .build();
            userRepo.save(user);
            userRepo.save(user2);
        };
    }

    public static Authentication retrieveUserAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
