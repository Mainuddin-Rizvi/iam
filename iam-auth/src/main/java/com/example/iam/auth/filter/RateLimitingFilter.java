package com.example.iam.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** Simple in-memory rate limiting filter */
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        AtomicInteger counter = requestCounts.computeIfAbsent(ip, k -> new AtomicInteger(0));
        if (counter.incrementAndGet() > 100) {
            response.setStatus(429);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
