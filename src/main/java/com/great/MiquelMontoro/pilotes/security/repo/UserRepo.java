package com.great.MiquelMontoro.pilotes.security.repo;

import com.great.MiquelMontoro.pilotes.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
//    User getOne(Long id);
}
