package com.robosh.controller.command.account.client;

import com.robosh.model.entity.Address;
import com.robosh.controller.command.Command;
import com.robosh.controller.command.RoutesJSP;
import com.robosh.service.AddressService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ClientOrderCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(ClientOrderCommand.class);
    private  AddressService addressService;

    public ClientOrderCommand(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Address> allAddress = addressService.getAllAddress();
        request.setAttribute("allAddress", allAddress);
        LOGGER.info("set attribute all address and return taxi order page");
        return RoutesJSP.TAXI_ORDER;
    }
}
