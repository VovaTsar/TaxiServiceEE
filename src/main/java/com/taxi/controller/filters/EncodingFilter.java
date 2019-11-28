package com.taxi.controller.filters;


import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {

    private String encodingTypeValue;

    @Override
    public void init(FilterConfig filterConfig) {
        encodingTypeValue = filterConfig.
                getInitParameter("encoding_type");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encodingTypeValue);
        servletResponse.setCharacterEncoding(encodingTypeValue);
        servletResponse.setContentType("text/html; charset=" + encodingTypeValue);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}