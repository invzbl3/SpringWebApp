package com.web.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.
        AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.
        WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password"))
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("password"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user/")
                .hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/user/")
                .hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user/**")
                .hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/user/**")
                .hasRole("ADMIN")
                .and()
                .csrf()
                .disable();
    }
}