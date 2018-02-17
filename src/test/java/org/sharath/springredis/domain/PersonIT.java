/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.sharath.spring.test.SpringJpaDataCacheTestConfig;
import org.sharath.spring.test.SpringJpaDataTestConfig;

/**
 *
 * @author Sharath Kulal
 */

public class PersonIT extends SpringJpaDataTestConfig {
    
    @Test
    public void testDomain() {
        Person person = DomainCreator.createPerson(1);
        
        em.persist(person);
        em.flush();
        em.clear();
        
        Person fromDb = em.find(Person.class, 1);
        assertThat(fromDb, is(CoreMatchers.notNullValue()));
        assertThat(fromDb.getId(), is(1));
    }
    
    @Test
    public void testPersonWithAddress() {
        Person person = DomainCreator.createPerson(5);
        
        Address address1 = DomainCreator.createAddress(10);
        PersonAddress personAddress1 = new PersonAddress();
        personAddress1.setId(100);
        personAddress1.setPerson(person);
        personAddress1.setAddress(address1);
        
        Address address2 = DomainCreator.createAddress(20);
        PersonAddress personAddress2 = new PersonAddress();
        personAddress2.setId(200);
        personAddress2.setPerson(person);
        personAddress2.setAddress(address2);

        em.persist(personAddress1);
        em.persist(personAddress2);
        em.flush();
        em.clear();
        
        Person fromDb = em.find(Person.class, 5);
        assertThat(fromDb.getId(), is(5));
        Set<Address> setAddress = fromDb.getPersonAddress();
        assertThat(setAddress.size(), is(2));
    }
    
}
