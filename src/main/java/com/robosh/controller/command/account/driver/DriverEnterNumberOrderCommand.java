package com.robosh.controller.command.account.driver;


import com.robosh.myUtils.LoginedUserUtils;
import com.robosh.controller.command.Command;
import com.robosh.controller.command.RoutesJSP;
import com.robosh.model.entity.Driver;
import com.robosh.model.entity.enums.DriverStatus;
import com.robosh.model.entity.enums.OrderStatus;
import com.robosh.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DriverEnterNumberOrderCommand implements Command {

    private OrderService orderService;
    private final Logger LOGGER = Logger.getLogger(DriverEnterNumberOrderCommand.class);
    public DriverEnterNumberOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String executeOrder = request.getParameter("executeOrder");
        int numberOfOrder;

        if (executeOrder.matches("\\d+")) {
            LOGGER.info("Input is correct");
            numberOfOrder = Integer.valueOf(executeOrder);
            Driver driver = (Driver) LoginedUserUtils.getLoginedUser(request.getSession());
            if (orderService.isCorrespondOrderAndDriver(numberOfOrder, driver.getPersonId())) {
                LOGGER.info("set driver free");
                driver.setDriverStatus(DriverStatus.FREE);
                LoginedUserUtils.updateLoginedUser(request.getSession(), driver);
                orderService.updateOrderStatus(numberOfOrder, OrderStatus.COMPLETE);
                return RoutesJSP.DRIVER_ACCOUNT;
            }
            return RoutesJSP.DRIVER_ACCOUNT + "?noSuchOrder=true";
        } else {
            LOGGER.info("input is not digit");
            return RoutesJSP.DRIVER_ACCOUNT + "?wrongInput=true";
        }
    }
}
