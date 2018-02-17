/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.service;

import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 *
 * @author Sharath Kulal
 */
@Ignore
public class JedisClusterTest {

    JedisCluster jc;
    
    @Before
    public void beforeTest() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
        //jedisClusterNodes.add(new HostAndPort("127.0.0.1", 9001));
        //jedisClusterNodes.add(new HostAndPort("127.0.0.1", 9002));
        
        jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");

        System.out.println("Connected to Redis");
    }
    
    @After
    public void afterTest() {
        
    }
    
    @Test
    public void testPut() {
        String key = "1";
        String value = "One";
        
        jc.set(key, value);

        String cacheValue = jc.get(key);
        assertThat(cacheValue, is(value));
    }

    @Test
    public void testGet() {
        String key = "key1";
        String value = "value1";

        String cacheValue = jc.get(key);
        assertThat(cacheValue, is(value));
    }
}
