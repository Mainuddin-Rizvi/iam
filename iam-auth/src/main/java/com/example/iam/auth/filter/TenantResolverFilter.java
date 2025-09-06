package com.example.iam.auth.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/** Resolves current tenant from request domain or header */
public class TenantResolverFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String host = request.getHeader("X-Tenant");
        if (host != null) {
            request.setAttribute("tenant", host);
        }
        filterChain.doFilter(request, response);
    }
}
