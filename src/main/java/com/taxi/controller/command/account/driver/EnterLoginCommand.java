package com.taxi.controller.command.account.driver;

import com.taxi.model.entity.enums.Role;
import com.taxi.myUtils.EncoderPassword;
import com.taxi.myUtils.LoginedUserUtils;
import com.taxi.controller.command.Command;

import static com.taxi.controller.command.PathCommand.*;

import com.taxi.controller.command.RoutesJSP;
import com.taxi.model.entity.Person;
import com.taxi.service.ClientService;
import com.taxi.service.DriverService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EnterLoginCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(EnterLoginCommand.class);

    private ClientService clientService;
    private DriverService driverService;
    private EncoderPassword encoderPassword;

    public EnterLoginCommand(ClientService clientService, DriverService driverService, EncoderPassword encoderPassword) {
        this.clientService = clientService;
        this.driverService = driverService;
        this.encoderPassword = encoderPassword;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String phoneNumber = request.getParameter("phoneNumber");
        final String password = request.getParameter("password");
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOGGER.info("check data phone number and password" + phoneNumber + " " + password);
        String hashPassword=encoderPassword.encode(password);
        if (inputWrongData(phoneNumber, hashPassword)) {
            request.setAttribute("errorMessage", "Invalid Phone Number or Password");
            LOGGER.info("wrong data");
            return RoutesJSP.LOGIN + "?wrongData=true";
        } else {
            Person person = LoginedUserUtils.getLoginedUser(request.getSession());
            if (person != null) {
                LOGGER.info("person id not null");
                if (Role.CLIENT.toString().equals(person.getRole().toString())) {
                    LOGGER.info("return client account");
                    return REDIRECT + contextAndServletPath + CLIENT_ACCOUNT;
                } else {
                    LOGGER.info("return driver account");
                    return REDIRECT + contextAndServletPath + DRIVER_ACCOUNT;
                }
            }
            if (checkIfDriver(phoneNumber, hashPassword)) {
                LOGGER.info("return driver account");

                person = driverService.getDriverByPasswordAndPhone(phoneNumber, hashPassword);
                LoginedUserUtils.storeLoginedUser(request.getSession(), person);
                return REDIRECT + contextAndServletPath + DRIVER_ACCOUNT;
            } else {
                LOGGER.info("return client account");
                person = clientService.getClientByPasswordAndPhone(phoneNumber, hashPassword);
                LoginedUserUtils.storeLoginedUser(request.getSession(), person);
                return REDIRECT + contextAndServletPath + CLIENT_ACCOUNT;
            }
        }
    }

    private boolean inputWrongData(String phoneNumber, String password) {
        return !checkIfClient(phoneNumber, password) && !checkIfDriver(phoneNumber, password);
    }

    private boolean checkIfClient(String phoneNumber, String password) {

        return clientService.isClientAlreadyExist(phoneNumber, password);
    }

    private boolean checkIfDriver(String phoneNumber, String password) {
        return driverService.isDriverExists(phoneNumber, password);
    }
}
