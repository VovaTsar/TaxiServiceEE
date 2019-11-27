package com.taxi.controller.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class LocalizationFilter implements Filter {
    private final Logger LOGGER = Logger.getLogger(LocalizationFilter.class);
    private String defaultBundle;
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {
         defaultBundle = filterConfig.getInitParameter("bundle");
         locale = filterConfig.getInitParameter("locale");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeParameter = request.getParameter("locale");
        LOGGER.info("set locale Filter" + localeParameter);
        locale = localeParameter != null
                ? localeParameter
                : httpRequest.getSession().getAttribute("locale") != null
                ? (String) httpRequest.getSession().getAttribute("locale")
                : this.locale;

        httpRequest.getSession().setAttribute("locale", locale);
        httpRequest.getSession().setAttribute("bundle", defaultBundle);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
