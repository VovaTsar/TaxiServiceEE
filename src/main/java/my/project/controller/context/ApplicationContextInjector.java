package my.project.controller.context;

import my.project.controller.command.Command;
import my.project.controller.command.show.*;
import my.project.controller.command.user.LoginCommand;
import my.project.controller.command.user.LogoutCommand;
import my.project.controller.command.user.RegisterCommand;
import my.project.model.service.AddressService;
import my.project.model.service.CouponService;
import my.project.model.service.TaxiService;
import my.project.model.service.UserService;
import my.project.model.service.encoder.PasswordEncoder;
import my.project.model.service.mapper.*;
import my.project.model.service.validator.UserValidator;
import my.project.model.service.validator.Validator;
import my.project.model.domain.User;
import my.project.model.dao.CouponDao;
import my.project.model.dao.impl.CouponDaoImpl;
import my.project.model.dao.connector.ConnectionPool;
import my.project.model.dao.TaxiDao;
import my.project.model.dao.impl.TaxiDaoImpl;
import my.project.model.dao.AddressDao;
import my.project.model.dao.impl.AddressDaoImpl;
import my.project.model.dao.OrderDao;
import my.project.model.dao.impl.OrderDaoImpl;
import my.project.model.dao.UserDao;
import my.project.model.dao.impl.UserDaoImpl;
import my.project.model.service.impl.CouponServiceImpl;
import my.project.model.service.impl.TaxiServiceImpl;
import my.project.model.service.impl.AddressServiceImpl;
import my.project.model.service.OrderService;
import my.project.model.service.impl.OrderServiceImpl;
import my.project.model.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final ConnectionPool CONNECTOR = new ConnectionPool("database");
    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();
    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final CouponMapper COUPON_MAPPER = new CouponMapper();
    private static final TaxiMapper TAXI_MAPPER = new TaxiMapper();
    private static final OrderMapper ORDER_MAPPER = new OrderMapper();
    private static final AddressMapper ADDRESS_MAPPER = new AddressMapper();

    private static final UserDao USER_DAO = new UserDaoImpl(CONNECTOR);
    private static final CouponDao COUPON_DAO = new CouponDaoImpl(CONNECTOR);
    private static final TaxiDao TAXI_DAO = new TaxiDaoImpl(CONNECTOR);
    private static final OrderDao ORDER_DAO = new OrderDaoImpl(CONNECTOR);
    private static final AddressDao ADDRESS_DAO = new AddressDaoImpl(CONNECTOR);


    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, PASSWORD_ENCODER, USER_MAPPER, USER_VALIDATOR);
    private static final CouponService COUPON_SERVICE = new CouponServiceImpl(COUPON_DAO, COUPON_MAPPER);
    private static final TaxiService TAXI_SERVICE = new TaxiServiceImpl(TAXI_DAO, TAXI_MAPPER);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO, ORDER_MAPPER);
    private static final AddressService ADDRESS_SERVICE = new AddressServiceImpl(ADDRESS_DAO, ADDRESS_MAPPER);
    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command REGISTRATION_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command USER_SHOW_COMMAND = new UserShowCommand(USER_SERVICE);
    private static final Command ORDER_SHOW_COMMAND = new OrderShowCommand(ORDER_SERVICE);
    private static final Command TAXI_SHOW_COMMAND = new TaxiShowCommand(TAXI_SERVICE);
    private static final Command COUPON_SHOW_COMMAND = new CouponShowCommand(COUPON_SERVICE);
    private static final Command ADDRESS_SHOW_COMMAND = new AddressShowCommand(ADDRESS_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = getInitUserCommand();

    private static Map<String, Command> getInitUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("registration", REGISTRATION_COMMAND);
        userCommandNameToCommand.put("showUsers", USER_SHOW_COMMAND);
        userCommandNameToCommand.put("showOrders", ORDER_SHOW_COMMAND);
        userCommandNameToCommand.put("showTaxis", TAXI_SHOW_COMMAND);
        userCommandNameToCommand.put("showCoupons", COUPON_SHOW_COMMAND);
        userCommandNameToCommand.put("showAddresses", ADDRESS_SHOW_COMMAND);

        return Collections.unmodifiableMap(userCommandNameToCommand);
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

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public CouponService getСouponService() {
        return COUPON_SERVICE;
    }

    public TaxiService getTaxiService() {
        return TAXI_SERVICE;
    }

    public OrderService getStoryService() {
        return ORDER_SERVICE;
    }

    public AddressService getAddresseService() {
        return ADDRESS_SERVICE;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }
}
