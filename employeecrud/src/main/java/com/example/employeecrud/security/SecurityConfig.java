package com.example.employeecrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails john = User.builder()
//                .username("John")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails mary = User.builder()
//                .username("Mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//        UserDetails susan = User.builder()
//                .username("Susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw,active from members where user_id = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/employees/**").hasRole("ADMIN"));
        // Sử dụng http basic authentication
        http.httpBasic(Customizer.withDefaults());
        // Vô hiệu csrf
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
