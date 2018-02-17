/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Sharath Kulal
 */
public class JedisTest {

    Jedis jedis;
    
    @Before
    public void beforeTest() {
        jedis = new Jedis("localhost");

        System.out.println("Connected to Redis");
    }

    @Test
    public void testPut() {
        String key = "1";
        String value = "One";
        
        jedis.set(key, value);

        String cacheValue = jedis.get(key);
        assertThat(cacheValue, is(value));
    }

    @Test
    public void testGet() {
        String key = "key1";
        String value = "value1";
        
        jedis.set(key, value);

        String cacheValue = jedis.get(key);
        assertThat(cacheValue, is(value));
    }
}
