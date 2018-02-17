/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.Test;
import org.sharath.spring.test.SpringRedisTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 *
 * @author Sharath Kulal
 */
public class SpringRedisTest extends SpringRedisTestConfig{
    
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    
    private String prefix = "unittest:";//Redis (Fasto No Sql displays this as folder)
    private String key = prefix+UUID.randomUUID().toString();
    
    @After
    public void afterTest() {
        jedisConnectionFactory.getConnection().del(key.getBytes());
    }
    
    @Test
    public void testPut() {
        String value = "value";
        
        jedisConnectionFactory.getConnection().set(key.getBytes(), value.getBytes());
        
        byte bytes[] = jedisConnectionFactory.getConnection().get(key.getBytes());
        
        assertThat(new String(bytes), is(value));
        
    }
    
    @Test
    public void testSpringRedisTemplate() {
        String value = "value";
        redisTemplate.opsForValue().set(key, value);
        String valueCache = (String)redisTemplate.opsForValue().get(key);
        
        
    }
 
}
