package org.example.springbootjwt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.springbootjwt.repositories.TokenRepository;
import org.example.springbootjwt.services.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    Spring Security filter - JwtAuthenticationFilter for processing JWT based authentication for HTTP requests

    It first checks if the request contains and "Authorization" header and if it starts with
    "Bearer". If not, it proceeds to the next filter in the chain

    If the header is present and starts with "Bearer", it extracts JWT token from the header

    Then it extracts the user email form the JWT token

    If the user email is not null and there's no existing authentication in the SecurityContextHolder, it loads
    the user details from the UserDetailsService based on the extracted email

    Then checks if the JWT token is valid for the user details obtained

    If the token is valid, it creates UsernamePasswordAuthenticationToken object with the user details and sets
    in the SecurityContextHolder.

    Finally, it proceeds to the next filter in the chain
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        if(authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authenticationHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            boolean isTokenValid = tokenRepository.findByToken(jwtToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);
            if(jwtService.isTokenValid(jwtToken, userDetails) && isTokenValid){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
