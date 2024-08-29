package com.application.user.service;

import com.application.user.entities.Phone;

import java.util.List;
import java.util.Optional;

public interface IPhoneService {

    List<Phone> findAll();

    Optional<Phone> findById(Long id);

    Phone save(Phone phone);

    void deleteById(Long id);


}
