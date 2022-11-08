package com.imd.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imd.project.security.JwtAuthFilter;
import com.imd.project.security.JwtService;
import com.imd.project.service.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserServiceImpl userService;

  @Bean
  public OncePerRequestFilter jwtFilter() {
    return new JwtAuthFilter(jwtService, userService);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests((authManager) -> {
          try {
            authManager
                .antMatchers(
                    HttpMethod.GET,
                    "/address/**", "/procedure/**", "/customer/**", "/employee/**")
                .hasAnyRole("USER", "ADMIN")

                .antMatchers(
                    HttpMethod.POST,
                    "/appointment/**")
                .hasAnyRole("USER", "ADMIN")

                .antMatchers("/address/**", "/appointment/**", "/customer/**", "/employee/**", "/procedure/**")
                .hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/user/**")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        );

    return http.build();
  }

}
