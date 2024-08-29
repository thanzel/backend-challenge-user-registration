package com.application.user.service.impl;

import com.application.user.configuration.SecureKeyGenerator;
import com.application.user.entities.User;
import com.application.user.persistence.IUserDAO;
import com.application.user.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Services del Usuario
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO iUserDAO;

    @Autowired
    private SecureKeyGenerator secureKeyGenerator;

    public SecureKeyGenerator getSecureKeyGenerator() {
        return secureKeyGenerator;
    }

    @Override
    public List<User> findAll() {
        return iUserDAO.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return iUserDAO.findById(id);
    }

    @Override
    public User save(User user) throws Exception {

        if (findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado. No se puede repetir");
        }

        user.setToken(generateToken(user.getEmail()));
        return iUserDAO.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        iUserDAO.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return iUserDAO.findByEmail(email);
    }

    @Override
    public String generateTokenById(UUID id) throws Exception {
        Optional<User> userOptional = findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = generateToken(user.getEmail());
            user.setToken(token);
            iUserDAO.save(user);
            return token;
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    private String generateToken(String email) throws Exception {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) //expira en una hora
                .signWith(SignatureAlgorithm.HS512, getSecureKeyGenerator().generateKey() )
                .compact();
    }
}
