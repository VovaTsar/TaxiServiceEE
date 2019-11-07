package my.project.controller.command.user;

import my.project.controller.command.Command;
import my.project.model.domain.User;
import my.project.model.entity.Role;
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
        final String name =  request.getParameter("name");
        final String surname =  request.getParameter("surname");
        final String email =  request.getParameter("email");
        final String password =  request.getParameter("password");
        final String confirmedPassword =  request.getParameter("confirmPassword");

        if (!Objects.equals(password, confirmedPassword)) {
            return "registration.jsp";
        }
        final User user = User.builder()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPassword(password)
                .withRole(Role.USER)
                .build();
        userService.register(user);
        return "login.jsp";
    }
}
