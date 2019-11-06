package my.project.controller.servlet;

import my.project.controller.command.Command;
import my.project.controller.command.user.LoginCommand;
import my.project.controller.command.user.RegisterCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final Map<String, Command> nameToCommand = new HashMap<>();

    static {
//        nameToCommand.put("login", new LoginCommand());
//        nameToCommand.put("register", new RegisterCommand());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        String action = req.getParameter("command");

        if (action == null || action.isEmpty() ) {
            throw new RuntimeException();
        }

        Command currentCommand = nameToCommand.get(action);

        if ( currentCommand == null ) {
            throw new RuntimeException();
        }

        page = currentCommand.execute(req);

        if ( page != null ) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);

        }
    }
}
