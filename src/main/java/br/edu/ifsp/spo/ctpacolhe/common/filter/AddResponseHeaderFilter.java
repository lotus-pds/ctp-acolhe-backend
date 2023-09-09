package br.edu.ifsp.spo.ctpacolhe.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AddResponseHeaderFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    		throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Accept", "application/json");
        httpServletResponse.setHeader("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        httpServletResponse.setHeader("Content-Security-Policy", "default-src 'self' https://ctp-acolhe-backend-production.up.railway.app");
        httpServletResponse.setHeader("X-Frame-Options", "DENY");
        httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpServletResponse.setHeader("Referrer-Policy", "same-origin");
        httpServletResponse.setHeader("Permissions-Policy", "microphone=none; geolocation=none;camera=none");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Quantidade-Total");
        chain.doFilter(request, response);
    }
}
