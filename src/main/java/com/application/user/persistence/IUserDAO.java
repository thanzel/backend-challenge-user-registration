package com.application.user.persistence;

import com.application.user.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserDAO {

    List<User> findAll();
    Optional<User> findById(UUID id);

    User save(User user);

    void deleteById(UUID id);

    Optional<User> findByEmail(String email);

}
