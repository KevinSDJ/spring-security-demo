package com.example.springSecCrudUser.Security.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.springSecCrudUser.Security.SecService.impl.UserDetailsServiceImpl;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsconfig = new CorsConfiguration();
        /*por el momento no hay restricciones de origenes*/
        corsconfig.setAllowedOriginPatterns(List.of("*"));
        corsconfig.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        corsconfig.setAllowedHeaders(List.of("Access-Control-Allow-Origin", "X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        corsconfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsconfig);
        return source;
    }
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
        .cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/register**","/auth/login**").permitAll()
	.antMatchers("/metadata","/favicon.ico","/*.css","/*.js").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/login")
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login?logout")
        .and()
        .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }


}
