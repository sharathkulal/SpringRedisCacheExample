/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.repository;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.sharath.spring.test.SpringJpaDataTestConfig;
import org.sharath.springredis.domain.Address;
import org.sharath.springredis.domain.DomainCreator;
import org.sharath.springredis.domain.Person;
import org.sharath.springredis.domain.PersonAddress;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sharath Kulal
 */
public class PersonAddressRepositoryIT extends SpringJpaDataTestConfig{
    
    @Autowired
    private PersonAddressRepository personAddressRepository;
    
    @Test
    public void testFindByPersonIdIn() {
        Person person = DomainCreator.createPerson(10);
        Address address1 = DomainCreator.createAddress(101);
        Address address2 = DomainCreator.createAddress(102);
        
        PersonAddress personAddress1 = new PersonAddress();
        personAddress1.setId(1);
        personAddress1.setPerson(person);
        personAddress1.setAddress(address1);
        em.persist(personAddress1);
        
        PersonAddress personAddress2 = new PersonAddress();
        personAddress2.setId(2);
        personAddress2.setPerson(person);
        personAddress2.setAddress(address2);
        em.persist(personAddress2);
        
        em.flush();
        em.clear();
        
        List<PersonAddress> lstPersonAddress = personAddressRepository.findByPersonIdIn(10);
        assertThat(lstPersonAddress.size(), is(2));
    }
}
