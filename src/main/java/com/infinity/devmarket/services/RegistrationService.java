package com.infinity.devmarket.services;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final EncodeService encodeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, EncodeService encodeService, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.encodeService = encodeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setPrivateKey(encodeService.encode(person.getPrivateKey()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}
