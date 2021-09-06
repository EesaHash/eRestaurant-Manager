package com.restaurant.service;

import com.restaurant.dto.RegistrationDto;
import com.restaurant.model.Person;
import com.restaurant.model.Role;
import com.restaurant.repository.PersonRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(RegistrationDto registration) {
        Person person = new Person(registration.getFirstName(),registration.getLastName(),
                registration.getEmail(),passwordEncoder.encode(registration.getPassword()),
                registration.getPhone(), Arrays.asList(new Role("CUSTOMER")));
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(email);
        //Alternatively use !isFound()
        if (optionalPerson.isEmpty()) {
            throw new UsernameNotFoundException("Invalid Email or Password");
        }
        Person person = optionalPerson.get();
        return new org.springframework.security.core.userdetails.User(
                person.getEmail(),person.getPassword(),mapRolesToAuthorties(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorties(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
