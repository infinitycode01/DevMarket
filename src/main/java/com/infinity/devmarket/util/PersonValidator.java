package com.infinity.devmarket.util;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.services.PeopleService;
import com.infinity.devmarket.services.PersonDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.loadUserByUsername(person.getUsername()).isEmpty())
            return;

        errors.rejectValue("username", "", "User by that name already exists");
    }
}
