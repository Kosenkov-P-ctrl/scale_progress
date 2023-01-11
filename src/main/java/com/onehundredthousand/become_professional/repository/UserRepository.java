package com.onehundredthousand.become_professional.repository;

import com.onehundredthousand.become_professional.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByLogin(String login);
}
