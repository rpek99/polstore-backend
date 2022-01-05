package com.example.softwarebackend.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/authentication").permitAll()
                .antMatchers(HttpMethod.POST, "/users/updateUser").permitAll()
                .antMatchers(HttpMethod.POST, "/products/addProduct/**").permitAll()
                .antMatchers(HttpMethod.POST, "/products/deleteProduct/**").permitAll()
                .antMatchers(HttpMethod.POST, "/products/updateProduct/**").permitAll()
                .anyRequest().authenticated();
        http.cors();
    }


}
