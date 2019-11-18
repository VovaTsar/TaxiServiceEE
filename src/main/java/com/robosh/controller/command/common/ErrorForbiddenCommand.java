package com.robosh.controller.command.common;

import com.robosh.controller.command.Command;
import com.robosh.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ErrorForbiddenCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(ErrorForbiddenCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return 403 page");
        return RoutesJSP.HTTP_FORBIDDEN;
    }
}
