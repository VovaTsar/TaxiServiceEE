package com.mytaxi.controller.command.common;

import com.mytaxi.controller.command.Command;
import com.mytaxi.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return login page");
        return RoutesJSP.LOGIN;
    }
}
