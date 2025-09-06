package com.example.iam.gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/** Routes requests to tenant-specific context by reading tenant header */
public class TenantRoutingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String tenant = request.getHeader("X-Tenant");
        if (tenant == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tenant header missing");
            return;
        }
        request.setAttribute("currentTenant", tenant);
        filterChain.doFilter(request, response);
    }
}
