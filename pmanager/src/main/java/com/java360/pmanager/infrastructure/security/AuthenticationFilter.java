package com.java360.pmanager.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationService authenticationService;

    @Override
    public void doFilter(
           ServletRequest servletRequest,
           ServletResponse servletResponse,
           FilterChain filterChain
    ) throws IOException, ServletException {

        Authentication authentication = authenticationService
                .getAuthentication((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
