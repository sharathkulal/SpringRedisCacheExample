/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.List;
import java.util.Optional;
import org.sharath.springredis.domain.Person;
import org.sharath.springredis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sharath Kulal
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    
    @CachePut(cacheNames="person", key="#person.id")
    public Person save(Person person) {
        Person saved = personRepository.save(person);
        return saved;
    }
    
    @Cacheable(cacheNames="person", key="#id")
    public Person findById(int id) {
        Person person = null;
        Optional<Person> optPerson = personRepository.findById(id);
        if(optPerson.isPresent()) {
            person = optPerson.get();
        }
        return person;
    }
    
    @Caching(evict = { 
        @CacheEvict(cacheNames="person", key="#id"),
        @CacheEvict(cacheNames="person-list", allEntries = true)
    })
    public void delete(int id) {
        personRepository.deleteById(id);
    }
    
    //Just Testing..
    //Not a good Idea, since these inputs could vary every call and cache size would grow.
    @Cacheable(cacheNames="person-list")
    public List<Person> findByIdIn(List<Integer> lstId) {
        return personRepository.findByIdIn(lstId);
    }
    
}
