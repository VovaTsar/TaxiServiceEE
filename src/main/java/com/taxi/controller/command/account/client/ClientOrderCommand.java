package com.taxi.controller.command.account.client;

import com.taxi.model.entity.Address;
import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import com.taxi.service.AddressService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ClientOrderCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(ClientOrderCommand.class);
    private AddressService addressService;

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
