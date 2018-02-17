/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.repository;

import java.util.List;
import org.sharath.springredis.domain.Address;
import org.sharath.springredis.domain.PersonAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Sharath Kulal
 */
public interface PersonAddressRepository extends  CrudRepository<PersonAddress, Integer>{
    
    @Query("Select pa from PersonAddress pa where pa.person.id=:personId")
    List<PersonAddress> findByPersonIdIn(@Param("personId") int personId);
}
