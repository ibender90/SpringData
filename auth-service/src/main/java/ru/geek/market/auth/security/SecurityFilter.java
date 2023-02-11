package ru.geek.market.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.geek.market.auth.services.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjc0MDYwNDc4LCJpYXQiOjE2NzQwNjAxNzgsImF1dGhvcml0eSI6WyJBRE1JTiIsIk1BTkFHRVIiXX0.TpvK-k0aPab-XWNYZKgQVAR4Q3Y1pAcjsUB_0uSggsA
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
            String bearerTokenValue = authorizationHeaderValue.substring(7);

            String username = jwtService.getUsername(bearerTokenValue);
            List<GrantedAuthority> authorities = jwtService.getAuthorities(bearerTokenValue);

            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(username, null, authorities)
                );
            }
        }

        // f1 -> f2 -> f3 -> ... -> fn
        filterChain.doFilter(request, response);
    }
}
