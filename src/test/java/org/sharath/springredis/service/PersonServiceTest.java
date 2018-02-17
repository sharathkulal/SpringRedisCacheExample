/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.sharath.spring.test.SpringServiceDataCacheTestConfig;
import org.sharath.springredis.domain.Person;
import org.sharath.springredis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * This test uses Mock Repository, so all method invocations will be NoOp/return null.
 * @author Sharath Kulal
 */
@DirtiesContext //Since we are Mocking SchemaRepository
@Configuration
@Profile("repo-mock")
@ActiveProfiles("repo-mock")
public class PersonServiceTest extends SpringServiceDataCacheTestConfig {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepositoryMock;

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        reset(personRepositoryMock);//reset mock so verify times(?) doesn't need to know about other tests.
    }
    
    @Primary
    @Bean
    public PersonRepository getPersonRepository() {
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        return personRepositoryMock;
    }
    

    @Test
    public void testService() {
        int person1Id = 1;
        int person2Id = 2;
        
        Person person1 = new Person();
        person1.setId(person1Id);
        person1.setName("Person 1");

        Person person2 = new Person();
        person2.setId(person2Id);
        person2.setName("person 2");

        personService.save(person1);
        personService.save(person2);

        //Repository methods will be called both times save is called
        verify(personRepositoryMock, times(2)).save(personCaptor.capture());

        personService.findById(person1Id);
        //Since this bean should be cached, repository method should not be called
        verify(personRepositoryMock, times(0)).findById(person1Id);

        personService.delete(person1Id);
        verify(personRepositoryMock, times(1)).deleteById(person1Id);

        personService.findById(person1Id);
        verify(personRepositoryMock, times(1)).findById(person1Id);
    }
    
    @Test
    public void testServiceFindByIdIn() {
        int person1Id = 10;
        int person2Id = 20;
        
        Person person1 = new Person();
        person1.setId(person1Id);
        person1.setName("Person 1");

        Person person2 = new Person();
        person2.setId(person2Id);
        person2.setName("person 2");

        personService.save(person1);
        personService.save(person2);
        

        //Repository methods will be called both times save is called
        verify(personRepositoryMock, times(2)).save(personCaptor.capture());

        List<Integer> lstId = new ArrayList<>();
        lstId.add(person1Id);
        personService.findByIdIn(lstId);

        //Make sure repository method is called
        verify(personRepositoryMock, times(1)).findByIdIn(lstId);

        lstId.add(person2Id);

        personService.findByIdIn(lstId);
        //Make sure repository method is called since the inputs are different
        verify(personRepositoryMock, times(2)).findByIdIn(lstId);

        personService.findByIdIn(lstId);
        //Make sure repository method is not called since the inputs are same
        verify(personRepositoryMock, times(2)).findByIdIn(lstId);

        personService.delete(person1Id);
        verify(personRepositoryMock, times(1)).deleteById(person1Id);
    }
}
