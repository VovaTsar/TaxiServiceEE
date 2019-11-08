package my.project.controller.command.user;

import my.project.controller.command.Command;
import my.project.model.domain.User;
import my.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        User user = userService.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "index.jsp";
    }
}
