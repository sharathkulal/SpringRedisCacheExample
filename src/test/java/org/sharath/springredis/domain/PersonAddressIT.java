/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.domain;

import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.sharath.spring.test.SpringJpaDataCacheTestConfig;

/**
 *
 * @author Sharath Kulal
 */
public class PersonAddressIT extends SpringJpaDataCacheTestConfig{
    
    @Test
    public void testDomainSimple() {
        Person person1 = DomainCreator.createPerson(10);
        Address address1 = DomainCreator.createAddress(5);
        
        PersonAddress pa1 = new PersonAddress();
        pa1.setId(1);
        pa1.setPerson(person1);
        pa1.setAddress(address1);
        
        em.persist(pa1);
        em.flush();
        em.clear();
        
        Person personDb = em.find(Person.class, 10);
        Address addressDb = em.find(Address.class, 5);
        
        assertThat(personDb, is(CoreMatchers.notNullValue()));
        assertThat(addressDb, is(CoreMatchers.notNullValue()));
    }
    
    @Test
    public void testDomainMultipleAddress() {
        Person person1 = DomainCreator.createPerson(10);
        Address address1 = DomainCreator.createAddress(5);
        Address address2 = DomainCreator.createAddress(7);
        
        PersonAddress pa1 = new PersonAddress();
        pa1.setId(1);
        pa1.setPerson(person1);
        pa1.setAddress(address1);
        
        PersonAddress pa2 = new PersonAddress();
        pa2.setId(2);
        pa2.setPerson(person1);
        pa2.setAddress(address2);
        
        em.persist(pa1);
        em.persist(pa2);
        em.flush();
        em.clear();
        
        Person personDb = em.find(Person.class, 10);
        Address addressDb1 = em.find(Address.class, 5);
        Address addressDb2 = em.find(Address.class, 7);
        
        assertThat(personDb, is(CoreMatchers.notNullValue()));
        assertThat(addressDb1, is(CoreMatchers.notNullValue()));
        assertThat(addressDb2, is(CoreMatchers.notNullValue()));
    }
}
