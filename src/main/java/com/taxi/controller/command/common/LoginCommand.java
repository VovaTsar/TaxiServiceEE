package com.taxi.controller.command.common;

import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return RoutesJSP.LOGIN;
    }
}
