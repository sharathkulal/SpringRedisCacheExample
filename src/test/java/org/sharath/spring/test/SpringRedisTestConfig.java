/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.spring.test;

import org.junit.runner.RunWith;
import org.sharath.spring.config.SpringCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * super class for Spring Redis Test
 * Mark as abstract, so Unit test doesn't try to run them
 * @author Sharath Kulal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCacheConfig.class})
public abstract class SpringRedisTestConfig {

    @Autowired
    protected RedisOperations<String, Object> redisOperations;

    @Autowired
    protected RedisTemplate redisTemplate;
}
