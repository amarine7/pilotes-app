package com.great.MiquelMontoro.pilotes.security.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private static final Integer ACCESS_TOKEN_VALIDITY_IN_SECONDS = 300;

    @Autowired
    private AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwt = Jwts.builder()
                .setSubject(username)
                .claim("authority", authorities)
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_IN_SECONDS*1000))
                .compact();

        response.setHeader("Authorization", jwt);
    }

    // This filter is active only for the /login endpoint
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
