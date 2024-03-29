package com.itau.demo.security;
 

import org.springframework.context.annotation.Configuration;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration  
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  
      
    @Override  
    public void configure(HttpSecurity http) throws Exception {  
        
        http
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .oauth2ResourceServer().jwt();
        
        
		http.cors().and().csrf().disable();
		

    }  
      
      
}
