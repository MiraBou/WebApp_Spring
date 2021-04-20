package com.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder= passwordEncoder();
        //super.configure(auth);
        //auth.inMemoryAuthentication().withUser("mira").password("{noop}mira").roles("USER");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("mira")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("mira")).roles("ADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/AddPatient**/**","/UpdatePatient**/**","/DeletePatient**/**","/validerPatient**/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
        http.csrf();

    }

@Bean
        public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}