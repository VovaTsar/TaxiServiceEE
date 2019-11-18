package com.robosh.controller.context;

import com.robosh.controller.command.Command;
import com.robosh.controller.command.account.client.*;
import com.robosh.controller.command.account.driver.DriverAccountCommand;
import com.robosh.controller.command.account.driver.DriverEnterNumberOrderCommand;
import com.robosh.controller.command.account.driver.EnterLoginCommand;
import com.robosh.controller.command.account.driver.ShowAllDriverOrdersCommand;
import com.robosh.controller.command.common.ErrorForbiddenCommand;
import com.robosh.controller.command.common.LogOutCommand;
import com.robosh.controller.command.common.LoginCommand;
import com.robosh.controller.command.common.TaxiHomeCommand;
import com.robosh.model.dao.connection.PoolConnection;
import com.robosh.model.dao.impl.*;
import com.robosh.service.*;

import java.util.HashMap;
import java.util.Map;

import static com.robosh.controller.command.PathCommand.*;
import static com.robosh.controller.command.PathCommand.ENTER_NUMBER_OF_ORDER;

public class ApplicationContextInjector {
    private static final PoolConnection CONNECTOR = new PoolConnection();

    private static final AddressDaoImpl ADDRESS_DAO = new AddressDaoImpl(CONNECTOR);
    private static final CarDaoImpl CAR_DAO = new CarDaoImpl(CONNECTOR);
    private static final ClientDaoImpl CLIENT_DAO = new ClientDaoImpl(CONNECTOR);
    private static final CouponDaoImpl COUPON_DAO = new CouponDaoImpl(CONNECTOR);
    private static final DriverDaoImpl DRIVER_DAO = new DriverDaoImpl(CONNECTOR);
    private static final OrderDaoImpl ORDER_DAO = new OrderDaoImpl(CONNECTOR);

    private static final AddressService ADDRESS_SERVICE = new AddressService(ADDRESS_DAO);
    private static final CarService CAR_SERVICE = new CarService(CAR_DAO);
    private static final ClientService CLIENT_SERVICE = new ClientService(CLIENT_DAO);
    private static final CouponService COUPON_SERVICE = new CouponService(COUPON_DAO);
    private static final DriverService DRIVER_SERVICE = new DriverService(DRIVER_DAO);
    private static final OrderService ORDER_SERVICE = new OrderService(ORDER_DAO);


    private static final Map<String, Command> NAME_COMMAND_TO_COMMANDS = initCommand();

    public  Map<String, Command> getNameCommandToCommands() {
        return NAME_COMMAND_TO_COMMANDS;
    }

    private static Map<String, Command> initCommand() {
        Map<String, Command> commands = new HashMap<>();
        commands.put(REGISTER_CLIENT, new RegisterClientCommand());
        commands.put(REGISTER_PAGE, new RegistrationCommand(CLIENT_SERVICE));
        commands.put(HOME_PAGE, new TaxiHomeCommand());
        commands.put(MAKE_ORDER, new ClientOrderCommand(ADDRESS_SERVICE));
        commands.put(ENTER_LOGIN, new EnterLoginCommand(CLIENT_SERVICE, DRIVER_SERVICE));
        commands.put(LOGIN_PAGE, new LoginCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(CLIENT_ACCOUNT, new ClientAccountCommand());
        commands.put(DRIVER_ACCOUNT, new DriverAccountCommand());
        commands.put(SHOW_ALL_ORDERS_PAG, new ShowAllDriverOrdersCommand(ORDER_SERVICE));
        commands.put(FORBIDDEN, new ErrorForbiddenCommand());
        commands.put(ENTER_ORDER, new EnterOrderCommand(ORDER_SERVICE, DRIVER_SERVICE, ADDRESS_SERVICE, COUPON_SERVICE,new ClientOrderCommand(ADDRESS_SERVICE)));
        commands.put(SHOW_CLIENT_ORDER, new ShowOrderClientCommand());
        commands.put(ENTER_NUMBER_OF_ORDER, new DriverEnterNumberOrderCommand(ORDER_SERVICE));
        return commands;
    }
    private static ApplicationContextInjector injector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (injector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (injector == null) {
                    injector = new ApplicationContextInjector();
                }
            }
        }
        return injector;
    }

    public  AddressService getAddressService() {
        return ADDRESS_SERVICE;
    }

    public  CarService getCarService() {
        return CAR_SERVICE;
    }

    public  ClientService getClientService() {
        return CLIENT_SERVICE;
    }

    public  CouponService getCouponService() {
        return COUPON_SERVICE;
    }

    public  DriverService getDriverService() {
        return DRIVER_SERVICE;
    }

    public  OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}
