package com.taxi.controller.command.account.client;


import com.taxi.controller.command.Command;
import com.taxi.model.entity.*;
import com.taxi.model.entity.enums.DriverStatus;
import com.taxi.model.entity.enums.OrderStatus;
import com.taxi.model.myUtils.CookiesUtils;
import com.taxi.model.myUtils.LoginedUserUtils;
import com.taxi.model.myUtils.PriceVoyageUtils;
import com.taxi.model.myUtils.TimeWaitTaxiUtil;
import com.taxi.model.service.AddressService;
import com.taxi.model.service.CouponService;
import com.taxi.model.service.DriverService;
import com.taxi.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.taxi.controller.command.PathCommand.REDIRECT;
import static com.taxi.controller.command.PathCommand.SHOW_CLIENT_ORDER;


public class EnterOrderCommand implements Command {

    private ClientOrderCommand clientOrder;
    private OrderService orderService;
    private DriverService driverService;
    private AddressService addressService;
    private CouponService couponService;

    public EnterOrderCommand(OrderService orderService, DriverService driverService,
                             AddressService addressService, CouponService couponService, ClientOrderCommand clientOrder) {
        this.orderService = orderService;
        this.driverService = driverService;
        this.addressService = addressService;
        this.couponService = couponService;
        this.clientOrder = clientOrder;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String addressDepartureStr = request.getParameter("addressDeparture");
        final String addressArriveStr = request.getParameter("addressArrive");
        final String carType = request.getParameter("carType");
        final String couponStr = request.getParameter("coupon");

        if (isNotSameAddress(addressDepartureStr, addressArriveStr)) {
            Driver driver = driverService.getDriverByCarTypeAndDriverStatus(DriverStatus.FREE, carType);
            if (driver != null) {
                bookedDriver(driver);

                Client loginedClient = (Client) LoginedUserUtils.getLoginedUser(request.getSession());
                Address addressDeparture = addressService.getAddressByAddressString(addressDepartureStr);
                Address addressArrive = addressService.getAddressByAddressString(addressArriveStr);
                Coupon coupon = couponService.getCouponByName(couponStr);


                int costs = PriceVoyageUtils.getPriceDependDistance(addressArrive, addressDeparture);
                int costWithDiscount = PriceVoyageUtils.getPriceWithCoupon(costs, coupon);

                Order order = new Order(OrderStatus.EXECUTING, loginedClient, driver, addressDeparture,
                        addressArrive, coupon, costs, costWithDiscount);

                orderService.createOrderInDB(order);

                int timeWait = TimeWaitTaxiUtil.getTimeWait();
                CookiesUtils.addCookies(response, driver, costWithDiscount, timeWait);

                String contextAndServletPath = request.getContextPath() + request.getServletPath();
                return REDIRECT + contextAndServletPath + SHOW_CLIENT_ORDER;
            } else {
                return clientOrder.execute(request, response) + "?noSuitableCarType=true";
            }
        } else {
            return clientOrder.execute(request, response) + "?sameAddress=true";
        }
    }

    private void bookedDriver(Driver driver) {
        driver.setDriverStatus(DriverStatus.BOOKED);
        driverService.updateDriverStatus(driver);
    }


    private boolean isNotSameAddress(String addressDeparture, String addressArrive) {
        return !addressArrive.equals(addressDeparture);
    }

}
