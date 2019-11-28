package com.taxi.controller.command.account.client;


import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import com.taxi.model.entity.Client;
import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;
import com.taxi.myUtils.EncoderPassword;
import com.taxi.myUtils.InputDataRegistrationUtils;
import com.taxi.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.taxi.controller.command.PathCommand.LOGIN_PAGE;
import static com.taxi.controller.command.PathCommand.REDIRECT;


public class RegistrationCommand implements Command {

    private ClientService clientService;
    private EncoderPassword encoderPassword;

    public RegistrationCommand(ClientService clientService, EncoderPassword encoderPassword) {
        this.clientService = clientService;
        this.encoderPassword = encoderPassword;
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
            return RoutesJSP.REGISTER_CLIENT;
        }
        if (InputDataRegistrationUtils.isNotCorrectData(name, surname, phoneNumber,
                email, password, repeatPassword)) {
            return RoutesJSP.REGISTER_CLIENT + "?badInput=true";
        }

        Client client = new Client();
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPassword(encoderPassword.encode(password));

        try {
            clientService.createClientInDatabase(client);
        } catch (EmailIsAlreadyTaken emailIsAlreadyTaken) {
            emailIsAlreadyTaken.printStackTrace();
            return RoutesJSP.REGISTER_CLIENT + "?badEmail=true";
        } catch (PhoneNumberIsAlreadyTaken phoneNumberIsAlreadyTaken) {
            phoneNumberIsAlreadyTaken.printStackTrace();
            return RoutesJSP.REGISTER_CLIENT + "?badPhoneNumber=true";
        }

        String contextAndServletPath = request.getContextPath() + request.getServletPath();

        return REDIRECT + contextAndServletPath + LOGIN_PAGE;
    }
}
