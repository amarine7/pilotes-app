package com.great.MiquelMontoro.pilotes.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static List<String> nonSecuredPaths = Arrays.asList("/api/create-your-pilotes", "/api/search-pilotes", "/api/update-pilotes");

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader("Authorization");

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

//        String username = String.valueOf(claims.get("username"));
        String username = claims.getSubject();
        String authority = String.valueOf(claims.get("authority"));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);

        Authentication auth = new UsernamePasswordAuthenticationToken(username, null, List.of(grantedAuthority));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    // This filter is always active, except for the /api/** endpoints
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !nonSecuredPaths.stream().anyMatch(s -> s.equals(request.getServletPath()));
    }
}
