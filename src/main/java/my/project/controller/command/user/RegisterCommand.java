package my.project.controller.command.user;

import my.project.controller.command.Command;
import my.project.model.domain.User;
import my.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String name = (String) request.getAttribute("name");
        final String surname = (String) request.getAttribute("surname");
        final String email = (String) request.getAttribute("email");

        final String password = (String) request.getAttribute("password");
        final String confirmedPassword = (String) request.getAttribute("confirmedPassword");

        if (Objects.equals(password, confirmedPassword)) {
            return "registration.jsp";
        }
        final User user = User.builder()
                .withEmail(email)
                .withName(name)
                .withPassword(password)
                .build();
        userService.register(user);
        return "login.jsp";
    }
}
