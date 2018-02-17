/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Sharath Kulal
 */
public class DomainCreator {
    
    public static Person createPerson(int id) {
        Person person = new Person();
        person.setId(id);
        person.setName("person "+id);
        person.setDob(Instant.now().minus(10, ChronoUnit.MINUTES));
        return person;
    }
    
    public static Address createAddress(int id) {
        Address address = new Address();
        address.setId(id);
        address.setStreet(id+", main Street");
        address.setCity("Boston");
        address.setState("MA");
        address.setZip("02101");
        address.setCountry("US");
        
        return address;
    }
    
}
