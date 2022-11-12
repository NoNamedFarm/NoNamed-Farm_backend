package com.nonamed.farm.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nonamed.farm.global.jwt.FilterConfig;
import com.nonamed.farm.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().and()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().disable()

            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
            .antMatchers(HttpMethod.POST, "/user/login").permitAll()
            .antMatchers(HttpMethod.PUT, "/user/refresh").permitAll()
            .antMatchers(HttpMethod.GET, "/user/check").permitAll()
            .anyRequest().authenticated()

            .and().apply(new FilterConfig(jwtTokenProvider));
    }

}