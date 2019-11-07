package my.project.controller.command.show;

import my.project.controller.command.Command;
import my.project.model.domain.Taxi;
import my.project.model.service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TaxiShowCommand implements Command {
    private TaxiService taxiService;

    public TaxiShowCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Taxi> taxis = taxiService.findAll(currentPage, recordsPerPage);

        request.setAttribute("taxis", taxis);

        int rows = taxiService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("commandShow");
        request.setAttribute("showTaxis", command);

        return "listTaxis.jsp";
    }
}
