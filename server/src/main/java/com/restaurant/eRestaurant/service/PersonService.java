package com.restaurant.eRestaurant.service;

import com.restaurant.eRestaurant.exceptions.UserNotFoundException;
import com.restaurant.eRestaurant.model.Person;
import com.restaurant.eRestaurant.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepo.findAll();
    }

    public Person updatePerson(Person person) {
        return personRepo.save(person);
    }

    public Person findPersonById(Long id) {
        return personRepo.findPersonById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
    }
    public Person findPersonByEmail(String email) {
        return personRepo.findPersonByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
    }

    public void deletePerson(Long id){
        personRepo.deletePersonById(id);
    }


}
