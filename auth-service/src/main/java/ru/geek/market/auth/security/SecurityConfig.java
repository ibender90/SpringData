package ru.geek.market.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().requestMatchers("/auth/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(SecurityFilter filter, HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests()
                .requestMatchers("/api/v1/orders/**")
                .authenticated()
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("pass")
//                .authorities("ADMIN", "MANAGER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    // manager [provider1, provider2, provider3, ...] -> providerX.authenticate

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... providers) {
        return new ProviderManager(providers);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new StandardAuthenticationProvider();
    }

}
