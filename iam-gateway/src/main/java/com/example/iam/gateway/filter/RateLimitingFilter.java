package com.example.iam.gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** Simple in-memory API rate limiting filter per IP address */
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        AtomicInteger c = counter.computeIfAbsent(ip, k -> new AtomicInteger(0));
        if (c.incrementAndGet() > 50) {
            response.sendError(429, "Rate limit exceeded");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
