package my.project.controller.servlet;

import my.project.controller.command.Command;
import my.project.controller.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class AdminServlet extends HttpServlet {
    private final Map< String, Command> commandNameToCommand;

    public AdminServlet() {
        final ApplicationContextInjector inject= ApplicationContextInjector.getInstance();
        this.commandNameToCommand = inject.getUserCommands();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command= req.getParameter("command");
        //check null maybe use optional
       //final String page = Optional.ofNullable(commandNameToCommand.get(command));

      // req.getRequestDispatcher(page).forward(req,resp);
    }
}
