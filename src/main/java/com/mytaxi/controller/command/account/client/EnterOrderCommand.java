package com.mytaxi.controller.command.account.client;


import com.mytaxi.controller.command.Command;
import com.mytaxi.model.entity.*;
import com.mytaxi.model.entity.enums.DriverStatus;
import com.mytaxi.model.entity.enums.OrderStatus;
import com.mytaxi.myUtils.CookiesUtils;
import com.mytaxi.myUtils.LoginedUserUtils;
import com.mytaxi.myUtils.PriceVoyageUtils;
import com.mytaxi.myUtils.TimeWaitTaxiUtil;
import com.mytaxi.service.AddressService;
import com.mytaxi.service.CouponService;
import com.mytaxi.service.DriverService;
import com.mytaxi.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.mytaxi.controller.command.PathCommand.REDIRECT;
import static com.mytaxi.controller.command.PathCommand.SHOW_CLIENT_ORDER;


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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        final String addressDepartureStr = request.getParameter("addressDeparture");
        final String addressArriveStr = request.getParameter("addressArrive");
        final String carType = request.getParameter("carType");
        final String couponStr = request.getParameter("coupon");

        if (isNotSameAddress(addressDepartureStr, addressArriveStr)) {
            Driver driver = driverService.getDriverByCarTypeAndDriverStatus(DriverStatus.FREE, carType);
            if (driver != null) {
                return createOrder(request, response, addressDepartureStr, addressArriveStr, couponStr, driver);
            } else {
                return clientOrder.execute(request, response) + "?noSuitableCarType=true";
            }
        } else {
            return clientOrder.execute(request, response) + "?sameAddress=true";
        }
    }

    private String createOrder(HttpServletRequest request, HttpServletResponse response, String addressDepartureStr, String addressArriveStr, String couponStr, Driver driver) throws UnsupportedEncodingException, UnsupportedEncodingException {
        bookedDriver(driver);

        Client loginedClient = (Client) LoginedUserUtils.getLoginedUser(request.getSession());
        Address addressDeparture = addressService.getAdressByAdressString(addressDepartureStr);
        Address addressArrive = addressService.getAdressByAdressString(addressArriveStr);
        Coupon coupon = couponService.getCouponByName(couponStr);

        int costs = PriceVoyageUtils.getPriceDependDistance(addressArrive, addressDeparture);
        int costWithDiscount = PriceVoyageUtils.getPriceWithCoupon(costs, coupon);
        Order order = Order.builder()
                .withOrderStatus(OrderStatus.EXECUTING)
                .withClient(loginedClient)
                .withDriver(driver)
                .withAddressDeparture(addressDeparture)
                .withAddressArrive(addressArrive)
                .withCoupon(coupon)
                .withCost(costs)
                .withCostWithDiscount(costWithDiscount)
                .build();
        orderService.createOrderInDB(order);
        int timeWait = TimeWaitTaxiUtil.getTimeWait();
        CookiesUtils.addCookies(response, driver, costWithDiscount, timeWait);

        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        return REDIRECT + contextAndServletPath + SHOW_CLIENT_ORDER;
    }

    private void bookedDriver(Driver driver) {
//        Driver driver1=Driver.builder()
//                .withPersonId(driver.getPersonId())
//                .withName(driver.getName())
//                .withSurname(driver.getSurname())
//                .withPassword(driver.getPassword())
//                .withPhoneNumber(driver.getPhoneNumber())
//                .withRole(driver.getRole())
//                .withDriverStatus(DriverStatus.BOOKED)
//                .withCar(driver.getCar())
//                .withMiddleName(driver.getMiddleName())
//                .build();
        driver.setDriverStatus(DriverStatus.BOOKED);
        driverService.updateDriverStatus(driver);
    }


    private boolean isNotSameAddress(String addressDeparture, String addressArrive) {
        return !addressArrive.equals(addressDeparture);
    }

}
