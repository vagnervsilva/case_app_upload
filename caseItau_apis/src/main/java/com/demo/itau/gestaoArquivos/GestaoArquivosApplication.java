package com.demo.itau.gestaoArquivos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/



@SpringBootApplication
public class GestaoArquivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoArquivosApplication.class, args);
	}
	
 

/*    @Configuration
	   static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {

	            http

	                .authorizeRequests().anyRequest().authenticated()

	                .and().oauth2ResourceServer().jwt();

	        }

	    }*/
	

}
