package com.taxi.controller.filters;

import com.taxi.model.entity.Person;
import com.taxi.model.entity.enums.Role;
import com.taxi.model.myUtils.LoginedUserUtils;
import com.taxi.model.myUtils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.taxi.controller.command.PathCommand.*;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String pathInfo = request.getPathInfo();
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        Person loginedPerson = LoginedUserUtils.getLoginedUser(request.getSession());

        if ((LOGIN_PAGE.equals(pathInfo) || REGISTER_CLIENT.equals(pathInfo)) && loginedPerson != null) {
            if (loginedPerson.getRole().equals(Role.CLIENT)) {
                response.sendRedirect(contextAndServletPath + CLIENT_ACCOUNT);
            } else {
                response.sendRedirect(contextAndServletPath + DRIVER_ACCOUNT);
            }
            return;
        }

        if (SecurityUtils.isSecurityPage(request)) {
            if (loginedPerson != null && SecurityUtils.hasPermission(request, loginedPerson.getRole())) {

                chain.doFilter(req, resp);
            } else {
                if (MAKE_ORDER.equals(pathInfo)) {
                    response.sendRedirect(contextAndServletPath + LOGIN_PAGE);
                } else {
                    response.sendRedirect(contextAndServletPath + FORBIDDEN);
                }
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}