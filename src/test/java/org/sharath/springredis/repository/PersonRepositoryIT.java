/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.repository;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.sharath.spring.test.SpringJpaDataTestConfig;
import org.sharath.springredis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sharath Kulal
 */
public class PersonRepositoryIT extends SpringJpaDataTestConfig {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void testFindByIdIn() {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("number 1");
        
        Person p2 = new Person();
        p2.setId(2);
        p2.setName("number 1");
        
        em.persist(p1);
        em.persist(p2);
        em.flush();
        em.clear();
        
        List<Integer> lstId = new ArrayList<>();
        lstId.add(1);
        lstId.add(2);
        
        List<Person> lstPerson = personRepository.findByIdIn(lstId);
        assertThat(lstPerson, is(CoreMatchers.notNullValue()));
        assertThat(lstPerson.size(), is(2)); 
    }
    
}
