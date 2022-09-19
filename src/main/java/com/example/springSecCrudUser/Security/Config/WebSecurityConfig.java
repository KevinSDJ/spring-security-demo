package com.example.springSecCrudUser.Security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import com.example.springSecCrudUser.Security.SecService.impl.UserDetailsServiceImpl;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
        .authorizeRequests()
        .antMatchers("/auth/register**","/auth/login**").permitAll()
	.antMatchers("/metadata","/favicon.ico","/*.css","/*.js").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/login")
        .and()
        .logout(logout->logout
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login?logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        )
        .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }


}
