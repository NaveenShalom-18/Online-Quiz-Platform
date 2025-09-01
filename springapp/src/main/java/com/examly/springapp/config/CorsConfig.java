package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CorsConfig {

  // Password Encoder
  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Connection between api and application
  // @Bean
  // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

  //   // Disable CSRF token

  //   http.csrf(csrf -> csrf.disable()).authorizeRequests((authorize) -> authorize.anyRequest().authenticated())
  //       .httpBasic(Customizer.withDefaults());

  //   return http.build();
  // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf().disable() // disable CSRF for testing, not for prod
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/quizzes/**").permitAll() // allow all quiz endpoints
    //             .anyRequest().authenticated()
    //         );
    //     return http.build();
    // }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }



  // VERIFY USER AND ADMIN DETAILS
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.builder().username("abc").password(passwordEncoder().encode("abc")).roles("USER")
        .build();
    UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }

}
