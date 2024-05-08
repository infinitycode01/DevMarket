package com.infinity.devmarket.util;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.services.PeopleService;
import org.jetbrains.annotations.NotNull;
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
    public boolean supports(@NotNull Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Person person = (Person) target;

        if (peopleService.loadUserByUsername(person.getUsername()).isEmpty())
            return;

        errors.rejectValue("username", "", "Користувач з таким ім'ям вже існує");
    }
}
