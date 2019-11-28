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
    private AddressService addressService;

    public ClientOrderCommand(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Address> allAddress = addressService.getAllAddress();
        request.setAttribute("allAddress", allAddress);
        return RoutesJSP.TAXI_ORDER;
    }
}
