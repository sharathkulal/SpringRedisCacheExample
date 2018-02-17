/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sharath Kulal
 */
@Configuration
@ComponentScan(basePackages = {
    "org.sharath.*.service"
})
public class SpringServiceConfig {
    
}
