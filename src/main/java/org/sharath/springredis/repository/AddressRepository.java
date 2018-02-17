/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.repository;

import org.sharath.springredis.domain.Address;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sharath Kulal
 */
public interface AddressRepository extends CrudRepository<Address, Integer>{
    
}
