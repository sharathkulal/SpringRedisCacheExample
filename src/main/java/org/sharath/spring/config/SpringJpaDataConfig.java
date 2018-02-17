/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Sharath kulal
 */
@Configuration
@EnableJpaRepositories(basePackages = {
    "org.sharath.*.repository"
})
@EnableTransactionManagement
public class SpringJpaDataConfig extends AbstractSpringJpaDataConfig{

    @Override
    public String[] getEntityPackagesToScan() {
        String packagesToScan[] = {"org.sharath.*.domain"};
        return packagesToScan;
    }
    
}
