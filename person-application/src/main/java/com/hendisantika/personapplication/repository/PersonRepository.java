package com.hendisantika.personapplication.repository;

import com.hendisantika.personapplication.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}