package com.hendisantika.personapplication.service;

import com.hendisantika.personapplication.entity.PersonEntity;
import com.hendisantika.personapplication.model.Person;
import com.hendisantika.personapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepo;
    /**
     * Convert {@link Person} Object to {@link Person} object Set the
     * personId if present else return object with id null/0
     */
    private final Function<Person, PersonEntity> personToEntity = new Function<Person, PersonEntity>() {
        @Override
        public PersonEntity apply(Person person) {
            if (person.getPersonId() == 0) {
                return new PersonEntity(person.getFirstName(), person.getLastName(), person.getEmail());
            } else {
                return new PersonEntity(person.getPersonId(), person.getFirstName(), person.getLastName(),
                        person.getEmail());
            }
        }
    };
    /**
     * Convert {@link Person} to {@link Person} object
     */
    private final Function<PersonEntity, Person> entityToPerson = new Function<PersonEntity, Person>() {
        @Override
        public Person apply(PersonEntity entity) {
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
            PersonEntity entity = personRepo.save(personToEntity.apply(person));
            return Optional.of(entityToPerson.apply(entity));
        } else {
            return Optional.empty();
        }
    }

}