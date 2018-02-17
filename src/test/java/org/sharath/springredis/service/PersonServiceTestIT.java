/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.Set;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.sharath.spring.test.SpringServiceDataCacheTestConfig;
import org.sharath.springredis.domain.Address;
import org.sharath.springredis.domain.DomainCreator;
import org.sharath.springredis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sharath Kulal
 */
public class PersonServiceTestIT extends SpringServiceDataCacheTestConfig{
    
    @Autowired
    private PersonService personService;
    
    @Test
    public void testSave() {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("number 1");
        
        personService.save(p1);
        
        em.flush();
        em.clear();
        
        Person fromDb = em.find(Person.class, 1);
        assertThat(fromDb, is(CoreMatchers.notNullValue()));
        assertThat(fromDb.getId(), is(p1.getId()));
    }
    
    @Test
    public void testUpdate() {
        String name = "Person 1";
        Person person1 = new Person();
        person1.setId(1);
        person1.setName(name);
        personService.save(person1);
        
        em.flush();
        em.clear();
        
        Person fromDb = personService.findById(1);
        assertThat(fromDb.getName(), is(name));
        
        name = name + " Edit";
        person1.setName(name);
        personService.save(person1);
        
        fromDb = personService.findById(1);
        assertThat(fromDb.getName(), is(name));
    }
    
    @Test
    public void testMultipleAddress() {
        Person person = DomainCreator.createPerson(5);
        Address address1 = DomainCreator.createAddress(10);
        Address address2 = DomainCreator.createAddress(20);

        person.addAddress(address1);
        person.addAddress(address2);

        personService.save(person);
        em.flush();
        em.clear();
        
        Person fromDb = personService.findById(5);
        Set<Address> address = fromDb.getPersonAddress();
        assertThat(address.size(), is(2));
    }
    
}
