package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder= passwordEncoder();
        //super.configure(auth);
        //auth.inMemoryAuthentication().withUser("mira").password("{noop}mira").roles("USER");
        //System.out.println("******************************"+passwordEncoder.encode("1234"));
        //System.out.println("******************************"+passwordEncoder.encode("mira"))
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal,password as credentials,active from user where username=?")
                .authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
        .passwordEncoder(passwordEncoder)
        .rolePrefix("ROLE_");
       // auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("mira")).roles("USER");
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