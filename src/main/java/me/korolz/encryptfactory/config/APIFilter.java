package me.korolz.encryptfactory.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.korolz.encryptfactory.helpers.TokenManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class APIFilter extends HttpFilter implements Filter {
    private final String AUTH_HEADER_PREFIX = "Bearer ";
    private final String API_PREFIX = "/api/v1/";


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith(API_PREFIX)) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith(AUTH_HEADER_PREFIX)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
                return;
            }

            String token = authHeader.substring(AUTH_HEADER_PREFIX.length());
            if (!TokenManager.getAuthToken().equals(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
