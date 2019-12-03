package com.taxi.controller.context;

import com.taxi.controller.command.Command;
import com.taxi.controller.command.account.client.*;
import com.taxi.controller.command.account.driver.DriverAccountCommand;
import com.taxi.controller.command.account.driver.DriverEnterNumberOrderCommand;
import com.taxi.controller.command.account.driver.EnterLoginCommand;
import com.taxi.controller.command.account.driver.ShowAllDriverOrdersCommand;
import com.taxi.controller.command.common.ErrorForbiddenCommand;
import com.taxi.controller.command.common.LogOutCommand;
import com.taxi.controller.command.common.LoginCommand;
import com.taxi.controller.command.common.TaxiHomeCommand;
import com.taxi.model.dao.*;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.dao.impl.*;
import com.taxi.model.mapper.*;
import com.taxi.model.myUtils.EncoderPassword;
import com.taxi.model.service.*;
import com.taxi.model.service.impl.*;

import java.util.HashMap;
import java.util.Map;

import static com.taxi.controller.command.PathCommand.*;

public class ApplicationContextInjector {
    private static final PoolConnection CONNECTOR = new PoolConnection();
    private static final EncoderPassword ENCODER_PASSWORD = new EncoderPassword();

    private static final AddressDao ADDRESS_DAO = new AddressDaoImpl(CONNECTOR);
    private static final CarDaoImpl CAR_DAO = new CarDaoImpl(CONNECTOR);
    private static final ClientDao CLIENT_DAO = new ClientDaoImpl(CONNECTOR);
    private static final CouponDao COUPON_DAO = new CouponDaoImpl(CONNECTOR);
    private static final DriverDao DRIVER_DAO = new DriverDaoImpl(CONNECTOR, CAR_DAO);
    private static final OrderDao ORDER_DAO = new OrderDaoImpl(CONNECTOR, CLIENT_DAO, DRIVER_DAO, ADDRESS_DAO, COUPON_DAO);

    private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();
    private static final CarMapper CAR_MAPPER = new CarMapper();
    private static final ClientMapper CLIENT_MAPPER = new ClientMapper();
    private static final CouponMapper COUPON_MAPPER = new CouponMapper();
    private static final DriverMapper DRIVER_MAPPER = new DriverMapper(CAR_MAPPER);
    private static final OrderMapper ORDER_MAPPER = new OrderMapper(CLIENT_MAPPER, DRIVER_MAPPER, ADDRESS_MAPPER, COUPON_MAPPER);

    private static final AddressService ADDRESS_SERVICE = new AddressServiceImpl(ADDRESS_DAO, ADDRESS_MAPPER);
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl(CLIENT_DAO, CLIENT_MAPPER);
    private static final CouponService COUPON_SERVICE = new CouponServiceImpl(COUPON_DAO, COUPON_MAPPER);
    private static final DriverService DRIVER_SERVICE = new DriverServiceImpl(DRIVER_DAO, DRIVER_MAPPER);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO, ORDER_MAPPER);


    private static final Map<String, Command> NAME_COMMAND_TO_COMMANDS = initCommand();

    public Map<String, Command> getNameCommandToCommands() {
        return NAME_COMMAND_TO_COMMANDS;
    }

    private static Map<String, Command> initCommand() {
        Map<String, Command> commands = new HashMap<>();
        commands.put(REGISTER_CLIENT, new RegisterClientCommand());
        commands.put(REGISTER_PAGE, new RegistrationCommand(CLIENT_SERVICE, ENCODER_PASSWORD));
        commands.put(HOME_PAGE, new TaxiHomeCommand());
        commands.put(MAKE_ORDER, new ClientOrderCommand(ADDRESS_SERVICE));
        commands.put(ENTER_LOGIN, new EnterLoginCommand(CLIENT_SERVICE, DRIVER_SERVICE, ENCODER_PASSWORD));
        commands.put(LOGIN_PAGE, new LoginCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(CLIENT_ACCOUNT, new ClientAccountCommand());
        commands.put(DRIVER_ACCOUNT, new DriverAccountCommand());
        commands.put(SHOW_ALL_ORDERS_PAG, new ShowAllDriverOrdersCommand(ORDER_SERVICE));
        commands.put(FORBIDDEN, new ErrorForbiddenCommand());
        commands.put(ENTER_ORDER, new EnterOrderCommand(ORDER_SERVICE, DRIVER_SERVICE, ADDRESS_SERVICE, COUPON_SERVICE, new ClientOrderCommand(ADDRESS_SERVICE)));
        commands.put(SHOW_CLIENT_ORDER, new ShowOrderClientCommand());
        commands.put(ENTER_NUMBER_OF_ORDER, new DriverEnterNumberOrderCommand(ORDER_SERVICE, DRIVER_SERVICE));
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

    public AddressService getAddressService() {
        return ADDRESS_SERVICE;
    }

    public ClientService getClientService() {
        return CLIENT_SERVICE;
    }

    public CouponService getCouponService() {
        return COUPON_SERVICE;
    }

    public DriverService getDriverService() {
        return DRIVER_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}
