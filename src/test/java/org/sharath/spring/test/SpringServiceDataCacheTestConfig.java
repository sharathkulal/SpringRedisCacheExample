/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.spring.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.runner.RunWith;
import org.sharath.spring.config.SpringCacheConfig;
import org.sharath.spring.config.SpringJpaDataConfig;
import org.sharath.spring.config.SpringServiceConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mark as abstract, so Unit test doesn't try to run them
 * @author Sharath Kulal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringServiceConfig.class, SpringJpaDataConfig.class, SpringCacheConfig.class})
@Transactional
public abstract class SpringServiceDataCacheTestConfig {
    
    @PersistenceContext
    protected EntityManager em;
    
    
}
