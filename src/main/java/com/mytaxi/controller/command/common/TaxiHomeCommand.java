package com.mytaxi.controller.command.common;

import com.mytaxi.controller.command.Command;
import com.mytaxi.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TaxiHomeCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(TaxiHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return taxi home page");
        return RoutesJSP.TAXI_HOME;
    }
}
