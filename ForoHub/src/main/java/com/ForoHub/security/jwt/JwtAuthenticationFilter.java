package com.ForoHub.security.jwt;

import com.ForoHub.security.core.JpaUserDetailsService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    private final JwtService jwtService;
    private final JpaUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, JpaUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;

        String header = http.getHeader("Authorization");
        String token = null;
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                var decoded = jwtService.verify(token);
                String username = decoded.getSubject();

                var userDetails = userDetailsService.loadUserByUsername(username);
                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(http));
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ex) {

            }
        }

        chain.doFilter(request, response);
    }
}
