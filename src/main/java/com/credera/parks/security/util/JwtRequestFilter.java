package com.credera.parks.security.util;

import com.credera.parks.security.service.ParksUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

        @Autowired
        private ParksUserDetailsService parksUserDetailsService;

        @Autowired
        private JwtUtil jwtUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

                String requestTokenHeader = request.getHeader("Authorization");

                String username = null;
                String jwtToken = null;

                if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                        jwtToken = requestTokenHeader.substring(7);
                        try {
                                username = jwtUtil.getUsernameFromToken(jwtToken);
                        } catch (IllegalArgumentException e) {
                                System.out.println("Unable to get JWT Token");
                        } catch (ExpiredJwtException e) {
                                System.out.println("JWT Token has expired");
                        }
                }

                // Once we get the token validate it.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                        UserDetails userDetails = this.parksUserDetailsService.loadUserByUsername(username);

                        // if token is valid configure Spring Security to manually set
                        // authentication
                        if (jwtUtil.validateToken(jwtToken, userDetails)) {

                                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                                userDetails, null, userDetails.getAuthorities());
                                usernamePasswordAuthenticationToken
                                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                // After setting the Authentication in the context, we specify
                                // that the current user is authenticated. So it passes the
                                // Spring Security Configurations successfully.
                                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                }
                chain.doFilter(request, response);
        }
}
