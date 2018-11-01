package com.hendisantika.personapplication.service;

import com.hendisantika.personapplication.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : person-application
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/11/18
 * Time: 06.56
 * To change this template use File | Settings | File Templates.
 */

public interface PersonService {
    /**
     * @param personId
     * @return {@link Optional} {@link Person} objects if present in database
     * for supplied person ID
     */
    Optional<Person> getPersonById(int personId);

    /**
     * @return {@link List} of {@link Person} model class fo rall available
     * entities
     */
    List<Person> getAllPersons();

    /**
     * @param personId
     * @return Delete the person from database for supplied id
     */
    boolean removePerson(int personId);

    /**
     * @param person
     * @return {@link Optional} {@link Person} objects after save or update Save
     * if no personId present else update
     */
    Optional<Person> saveUpdatePerson(Person person);
}
