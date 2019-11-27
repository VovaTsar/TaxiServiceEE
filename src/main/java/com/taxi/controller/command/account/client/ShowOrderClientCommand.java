package com.taxi.controller.command.account.client;


import com.taxi.myUtils.CookiesUtils;
import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowOrderClientCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(ShowOrderClientCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String driverName = CookiesUtils.readCookie(request, CookiesUtils.DRIVER_NAME);
        String phoneNumber = CookiesUtils.readCookie(request, CookiesUtils.DRIVER_PHONE);
        String priceVoyage = CookiesUtils.readCookie(request, CookiesUtils.PRICE_VOYAGE);
        String timeWait = CookiesUtils.readCookie(request, CookiesUtils.TIME_WAIT);
        LOGGER.info("get cookies");
        System.out.println(driverName);
        request.setAttribute(CookiesUtils.DRIVER_NAME, driverName);
        request.setAttribute(CookiesUtils.DRIVER_PHONE, phoneNumber);
        request.setAttribute(CookiesUtils.PRICE_VOYAGE, priceVoyage);
        request.setAttribute(CookiesUtils.TIME_WAIT, timeWait);
        LOGGER.info("return order status");
        return RoutesJSP.ORDER_STATUS;
    }
}
