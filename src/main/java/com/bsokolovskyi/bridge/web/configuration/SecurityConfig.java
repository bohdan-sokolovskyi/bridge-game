package com.bsokolovskyi.bridge.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(20, new SecureRandom());
    }

    public AuthenticationManager authenticationManager(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) throws Exception {

        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationSuccessHandler handler) throws Exception {
        return httpSecurity
                .authorizeRequests()
                    .antMatchers("/", "/login", "/signup").permitAll()
                    .antMatchers("/dashboard/**").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(handler)
                    .loginPage("/login").failureUrl("/login?error=true")
                    .usernameParameter("login")
                    .passwordParameter("password")
                .and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").and().exceptionHandling()
                .and().build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
