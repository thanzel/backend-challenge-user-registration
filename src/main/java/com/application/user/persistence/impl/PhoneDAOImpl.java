package com.application.user.persistence.impl;

import com.application.user.entities.Phone;
import com.application.user.persistence.IPhoneDAO;
import com.application.user.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PhoneDAOImpl implements IPhoneDAO {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> findAll() {
        return (List<Phone>) phoneRepository.findAll();
    }

    @Override
    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }
}
