package com.taxi.controller.command.account.driver;

import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import com.taxi.model.domain.Driver;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.OrderStatus;
import com.taxi.model.myUtils.LoginedUserUtils;
import com.taxi.model.service.DriverService;
import com.taxi.model.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DriverEnterNumberOrderCommand implements Command {

    private OrderService orderService;
    private DriverService driverService;
    private final Logger LOGGER = Logger.getLogger(DriverEnterNumberOrderCommand.class);

    public DriverEnterNumberOrderCommand(OrderService orderService, DriverService driverService) {
        this.orderService = orderService;
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String executeOrder = request.getParameter("executeOrder");
        int numberOfOrder;

        if (executeOrder.matches("\\d+")) {

            numberOfOrder = Integer.valueOf(executeOrder);
            Driver driver = (Driver) LoginedUserUtils.getLoginedUser(request.getSession());
            if (orderService.isCorrespondOrderAndDriver(numberOfOrder, driver.getPersonId())) {

                driver.setDriverStatus(DriverStatus.FREE);
                driverService.updateDriverStatus(driver);

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
