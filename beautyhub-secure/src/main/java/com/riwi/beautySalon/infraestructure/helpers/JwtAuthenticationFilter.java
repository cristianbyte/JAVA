package com.riwi.beautySalon.infraestructure.helpers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Filter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.var;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService; 

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        //get token
        final String token = getTokenFromRequest(request);

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        // get user from token 
        String userName = this.jwtService.getUsernameFromToken(token);

        // if not find in spring security context
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            //get user from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            //if usera and token is valid
            if(this.jwtService.isTokenValid(token, userDetails)){
                //create authentication and spring security context
                var authToken = new UsernamePasswordAuthenticationToken(userName, null,userDetails.getAuthorities());
                // add authentication details based on request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //   
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }

    public String getTokenFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //Bearer
        if(StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer")){
            return authHeader.substring(7);
        }
        return null;
    }
}
