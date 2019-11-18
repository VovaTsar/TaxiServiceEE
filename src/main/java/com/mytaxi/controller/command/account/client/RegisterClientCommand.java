package com.mytaxi.controller.command.account.client;

import com.mytaxi.controller.command.Command;
import com.mytaxi.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterClientCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(RegisterClientCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return register client page");
        return RoutesJSP.REGISTER_CLIENT;
    }
}
