package com.taxi.controller.command.account.driver;

import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DriverAccountCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(DriverAccountCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return driver account");
        return RoutesJSP.DRIVER_ACCOUNT;
    }
}
