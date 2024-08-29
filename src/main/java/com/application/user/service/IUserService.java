package com.application.user.service;

import com.application.user.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(UUID id);

    User save(User user) throws Exception;

    void deleteById(UUID id);

    Optional<User> findByEmail(String email);

    String generateTokenById(UUID id) throws Exception;

}
