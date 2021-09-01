package com.restaurant.eRestaurant.service;

import com.restaurant.eRestaurant.exceptions.UserNotFoundException;
import com.restaurant.eRestaurant.model.Person;
import com.restaurant.eRestaurant.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findPersonById(Long id) {
        return personRepository.findPersonById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
    }
    public Person findPersonByEmail(String email) {
        return personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
    }

    public void deletePerson(Long id){
        personRepository.deletePersonById(id);
    }


}
