package my.project.controller.command.user;

import my.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    public LogoutCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();

        return "login.jsp";
    }
}
