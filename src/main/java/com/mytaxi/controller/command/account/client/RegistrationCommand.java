package com.mytaxi.controller.command.account.client;


import com.mytaxi.controller.command.Command;
import com.mytaxi.controller.command.RoutesJSP;
import com.mytaxi.model.customExceptions.EmailIsAlreadyTaken;
import com.mytaxi.model.customExceptions.PhoneNumberIsAlreadyTaken;
import com.mytaxi.model.entity.Client;
import com.mytaxi.myUtils.InputDataRegistrationUtils;
import com.mytaxi.service.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mytaxi.controller.command.PathCommand.LOGIN_PAGE;
import static com.mytaxi.controller.command.PathCommand.REDIRECT;


public class RegistrationCommand implements Command {

    private final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private ClientService clientService;

    public RegistrationCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String phoneNumber = request.getParameter("phone_number");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String repeatPassword = request.getParameter("password_repeat");

        if (name == null) {
            LOGGER.info("Name == null, return register client");
            return RoutesJSP.REGISTER_CLIENT;
        }
        if (InputDataRegistrationUtils.isNotCorrectData(name, surname, phoneNumber,
                email, password, repeatPassword)) {
            LOGGER.info("wrong data input");
            return RoutesJSP.REGISTER_CLIENT + "?badInput=true";
        }

        Client client=Client.builder()
                .withName(name)
                .withPhoneNumber(phoneNumber)
                .withSurname(surname)
                .withEmail(email)
                .withPassword(password)
                .build();

        try {
            LOGGER.info("try write to database client");
            clientService.createClientInDatabase(client);
        } catch (EmailIsAlreadyTaken emailIsAlreadyTaken) {
            LOGGER.error("EmailIsAlreadyTaken ", emailIsAlreadyTaken);
            emailIsAlreadyTaken.printStackTrace();
            return RoutesJSP.REGISTER_CLIENT + "?badEmail=true";
        } catch (PhoneNumberIsAlreadyTaken phoneNumberIsAlreadyTaken) {
            LOGGER.error("PhoneNumberIsAlreadyTaken ", phoneNumberIsAlreadyTaken);
            phoneNumberIsAlreadyTaken.printStackTrace();
            return RoutesJSP.REGISTER_CLIENT + "?badPhoneNumber=true";
        }

        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOGGER.info("return login page");
        return REDIRECT + contextAndServletPath + LOGIN_PAGE;
    }
}
