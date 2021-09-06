package com.restaurant.service;

import com.restaurant.dto.RegistrationDto;
import com.restaurant.model.Person;
import com.restaurant.model.Role;
import com.restaurant.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(RegistrationDto registration) {
        Person person = new Person(registration.getFirstName(),registration.getLastName(),
                registration.getEmail(),registration.getPassword(),
                registration.getPhone(), Arrays.asList(new Role("CUSTOMER")));
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findPerson(Long id) {
        return personRepository.findById(id);
    }
}
