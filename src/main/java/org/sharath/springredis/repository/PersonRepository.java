/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.repository;

import java.util.List;
import org.sharath.springredis.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sharath Kulal
 */
public interface PersonRepository extends CrudRepository<Person, Integer>{
    
    List<Person> findByIdIn(List<Integer> lstId);
}
