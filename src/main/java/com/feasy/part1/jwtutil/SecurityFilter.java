package com.feasy.part1.jwtutil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public SecurityFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Reading Token from Authorization Header
        String tokenHeader= request.getHeader("Authorization");
        String token = null;

        if(tokenHeader!=null){
            token = tokenHeader.split(" ")[1].trim();
        }

        if(token !=null) {
            String username= jwtUtil.getSubject(token);
            //if username is not null & Context Authentication must be null
            if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                UserDetails user= userDetailsService.loadUserByUsername(username);
                boolean isValid=jwtUtil.isValidToken(token, user.getUsername());
                if(isValid) {
                    UsernamePasswordAuthenticationToken authToken=
                            new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
