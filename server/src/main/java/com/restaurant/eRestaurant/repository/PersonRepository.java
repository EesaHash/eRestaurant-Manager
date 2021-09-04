package com.restaurant.eRestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.eRestaurant.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    void deletePersonById(Long id);

    Optional<Person> findPersonById(Long id);


    Optional<Person> findPersonByEmail(String email);
}
