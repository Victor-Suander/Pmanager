package com.java360.pmanager.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@RequiredArgsConstructor
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationService authenticationService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain
    ) {

        try {
            Authentication authentication = authenticationService
                    .getAuthentication((HttpServletRequest) request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(SC_UNAUTHORIZED);
        }
    }
}