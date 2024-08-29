package com.application.user.service.impl;

import com.application.user.entities.Phone;
import com.application.user.persistence.IPhoneDAO;
import com.application.user.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private IPhoneDAO iPhoneDAO;

    @Override
    public List<Phone> findAll() {
        return iPhoneDAO.findAll();
    }

    @Override
    public Optional<Phone> findById(Long id) {
        return iPhoneDAO.findById(id);
    }

    @Override
    public Phone save(Phone phone) {
        return iPhoneDAO.save(phone);
    }

    @Override
    public void deleteById(Long id) {
        iPhoneDAO.deleteById(id);
    }
}
