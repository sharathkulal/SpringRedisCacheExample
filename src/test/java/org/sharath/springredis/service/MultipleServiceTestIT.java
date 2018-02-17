/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.sharath.spring.test.SpringServiceDataCacheTestConfig;
import org.sharath.springredis.domain.Address;
import org.sharath.springredis.domain.DomainCreator;
import org.sharath.springredis.domain.Person;
import org.sharath.springredis.domain.PersonAddress;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sharath Kulal
 */
public class MultipleServiceTestIT extends SpringServiceDataCacheTestConfig{
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonAddressService personAddressService;
    
    @Test
    public void testAddAddress() {
        Person person = DomainCreator.createPerson(5);
        
        personService.save(person);
        em.flush();
        em.clear();
        
        Address address1 = DomainCreator.createAddress(10);
        PersonAddress personAddress1 = new PersonAddress();
        personAddress1.setId(1);
        personAddress1.setPerson(person);
        personAddress1.setAddress(address1);
        personAddressService.save(personAddress1);
        
        em.flush();
        em.clear();
        
        Person fromDb = personService.findById(5);
        assertThat(fromDb.getPersonAddress().size(), is(1));
    }
    
    /**
     * Demonstrate why its not a good idea to cache Objects with Proxy Since we save a person using entity manager. person object is not in cache. 
     * when we retreive person using findById, it puts the person object in cache (PersonAddress property within Person Object is proxied for Lazy Initialization) 
     * when we retreive this object later and try to pull thru Address, it will throw Lazy Initializaton Exceptoin.
     */
    @Test(expected = LazyInitializationException.class)
    public void testAddress_Session() {
        Person person = DomainCreator.createPerson(5);
        
        em.persist(person);
        em.flush();
        em.clear();
        
        Person fromDb = personService.findById(5);
        
        Address address1 = DomainCreator.createAddress(10);
        PersonAddress personAddress1 = new PersonAddress();
        personAddress1.setId(1);
        personAddress1.setPerson(fromDb);
        personAddress1.setAddress(address1);
        em.persist(personAddress1);
        
        em.flush();
        em.clear();
        
        fromDb = personService.findById(5);
        assertThat(fromDb.getPersonAddress().size(), is(1));
    }
}
