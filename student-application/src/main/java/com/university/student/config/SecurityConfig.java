package com.university.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

//    @Bean
//    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity.csrf(AbstractHttpConfigurer::disable).build();
//    }

    //For permit all request without auth
   /** @Bean
    SecurityFilterChain permitAllFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request-> request.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
   */

    //For deny all request without auth
   /** @Bean
    SecurityFilterChain denyAllFilterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeHttpRequests(request-> request.anyRequest().denyAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
    }
   */

    @Bean
    SecurityFilterChain customFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request-> request.requestMatchers("","/","/home").permitAll()
                        .requestMatchers("/student","/mark").authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("Adarsh")
                .password("password")
                .authorities("USER")
                .build();
        UserDetails admin = User.withUsername("Ashwin")
                .password("qwerty")
                .authorities("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
