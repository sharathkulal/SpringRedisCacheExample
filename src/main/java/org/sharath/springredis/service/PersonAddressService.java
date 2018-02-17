/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import org.sharath.springredis.domain.PersonAddress;
import org.sharath.springredis.repository.PersonAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sharath Kulal
 */
@Service
public class PersonAddressService {
    
    @Autowired
    private PersonAddressRepository personAddressRepository;
    
    @Caching(evict = { 
        @CacheEvict(cacheNames="person", key="#personAddress.person.id"),
        @CacheEvict(cacheNames="person-list", allEntries = true)
    })
    public PersonAddress save(PersonAddress personAddress) {
        return personAddressRepository.save(personAddress);
    }
}
