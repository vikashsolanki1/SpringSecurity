package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecConfig {
@Autowired
DataSource ds;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }
    //UserDetailsService ia an inbuilt method we hae  to import it
    @Bean
    public  UserDetailsService userdet()
    {
    	UserDetails user1 = User.withUsername("user1")
    			//noop is used to store the password as text so that it can be used to encodde
    			.password("{noop}password")
    			.roles("USER1")
    			.build();
    	UserDetails admin = User.withUsername("admin")
    			//noop is used to store the password as text so that it can be used to encodde
    			.password("{noop}password1")
    			.roles("ADMIN")
    			.build();
    	// we have to import JdbcUserDetailsManager
    			JdbcUserDetailsManager userdet= new JdbcUserDetailsManager(ds);
    			//userdet  is the object
    			userdet.createUser(user1);
    			userdet.createUser(admin);
    			return userdet;
    			// import InMemoryUserDetailsManager
    	//return new InMemoryUserDetailsManager(user1, admin);
    }
}
