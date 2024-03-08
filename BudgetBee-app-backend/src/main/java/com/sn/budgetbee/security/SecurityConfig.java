package com.sn.budgetbee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests( configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/**/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/**/**/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());
        //http.csrf(csrg -> csrg.disable());

        return http.build();
    }
}
