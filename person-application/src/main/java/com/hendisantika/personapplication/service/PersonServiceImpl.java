package com.hendisantika.personapplication.service;

import com.hendisantika.personapplication.entity.Person;
import com.hendisantika.personapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : person-application
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/11/18
 * Time: 06.58
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepo;
    /**
     * Convert {@link Person} Object to {@link Person} object Set the
     * personId if present else return object with id null/0
     */
    private final Function<Person, Person> personToEntity = new Function<Person, Person>() {
        @Override
        public Person apply(Person person) {
            if (person.getPersonId() == 0) {
                return new Person(person.getFirstName(), person.getLastName(), person.getEmail());
            } else {
                return new Person(person.getPersonId(), person.getFirstName(), person.getLastName(),
                        person.getEmail());
            }
        }
    };
    /**
     * Convert {@link Person} to {@link Person} object
     */
    private final Function<Person, Person> entityToPerson = new Function<Person, Person>() {
        @Override
        public Person apply(Person entity) {
            return new Person(entity.getPersonId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
        }
    };

    @Autowired
    PersonServiceImpl(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    /**
     * If record is present then convert the record else return the empty {@link Optional}
     */
    @Override
    public Optional<Person> getPersonById(int personId) {
        return personRepo.findById(personId).map(s -> entityToPerson.apply(s));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepo.findAll().parallelStream()
                .map(s -> entityToPerson.apply(s))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removePerson(int personId) {
        personRepo.deleteById(personId);
        return true;
    }

    @Override
    public Optional<Person> saveUpdatePerson(Person person) {
        if (person.getPersonId() == 0 || personRepo.existsById(person.getPersonId())) {
            Person entity = personRepo.save(personToEntity.apply(person));
            return Optional.of(entityToPerson.apply(entity));
        } else {
            return Optional.empty();
        }
    }

}