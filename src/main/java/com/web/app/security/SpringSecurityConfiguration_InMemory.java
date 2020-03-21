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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter {
   /* @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().
                withUser("user").password("password")
                .roles("USER");
        auth.
                inMemoryAuthentication().withUser("admin").
                password("password")
                .roles("USER", "ADMIN");
    }*/

    @Bean
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;

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