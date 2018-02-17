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
public class AddressIT extends SpringJpaDataCacheTestConfig{
    
    @Test
    public void testDomain() {
        Address address = DomainCreator.createAddress(1);
        
        em.persist(address);
        em.flush();
        em.clear();
        
        Address fromDb = em.find(Address.class, 1);
        
        assertThat(fromDb, is(CoreMatchers.notNullValue()));
        assertThat(fromDb.getId(), is(1));
    }
}
