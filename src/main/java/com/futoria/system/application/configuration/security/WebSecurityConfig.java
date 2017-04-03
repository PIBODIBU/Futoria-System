package com.futoria.system.application.configuration.security;

import com.futoria.core.application.configuration.security.FutoriaWebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends FutoriaWebSecurityConfig {
    @Autowired
    public WebSecurityConfig(@Qualifier("CoreDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_SYS_ADMIN') or hasRole('ROLE_UNI_ADMIN')")
                .antMatchers("/me/**").access("isAuthenticated()")
                .antMatchers("/me/tests/**").access("hasRole('ROLE_UNI_PROFESSOR')")
                .and()
                .formLogin().defaultSuccessUrl("/me/").loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login/?logout")
                .and()
                .httpBasic().disable()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();
    }
}