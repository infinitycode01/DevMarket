package com.infinity.devmarket.services;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.repositories.PeopleRepository;
import com.infinity.devmarket.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUserByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
}
