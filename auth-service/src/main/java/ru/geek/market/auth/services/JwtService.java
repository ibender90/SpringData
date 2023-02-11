package ru.geek.market.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.geek.market.auth.security.JwtProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private JwtProperties properties;

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireTime().toMillis()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public String getUsername(String value) {
        return parse(value).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(String value) {
        List<String> authorities = (List<String>) parse(value).get("authority");
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }

}
