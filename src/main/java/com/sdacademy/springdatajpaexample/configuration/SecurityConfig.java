package com.sdacademy.springdatajpaexample.configuration;

import com.sdacademy.springdatajpaexample.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE).hasAuthority(User.Roles.ROLE_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // in memory authentication
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("$2a$10$8KUURAMjDlA3gcFVDcdAZ.utOYuEezK.tNdJZGyO16AaeTJEjKCNe").roles("USER")
//                .and()
//                .withUser("admin").password("$2a$10$/H7l7ZQNm/IAAX6eOBrSZu4sFBd9W2eQ9GDQzs0kXtIH1CRg6rOGi").roles("ADMIN");

        auth.userDetailsService(userDetailsService);
    }
}
