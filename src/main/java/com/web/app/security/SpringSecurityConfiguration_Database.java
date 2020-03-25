package com.web.app.security;

import com.web.app.service.UserInfoDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity

// Securing the H2 console
@ConditionalOnExpression("#{environment.getProperty('security.basic.authorize.mode') eq('authenticated') && environment.getProperty('security.basic.enabled') eq('true')}")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration_Database
        extends WebSecurityConfigurerAdapter {
    private final UserInfoDetailsService userInfoDetailsService;

    public SpringSecurityConfiguration_Database(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userInfoDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**")
                .authenticated()
                .and()
                .httpBasic()
                .realmName("User Registration System")
                .and()
                .csrf()
                .disable();
    }
}