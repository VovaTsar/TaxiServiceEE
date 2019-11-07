package my.project.controller.command.show;

import my.project.controller.command.Command;
import my.project.model.domain.Address;
import my.project.model.service.AddressService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddressShowCommand implements Command {
    private AddressService addressService;

    public AddressShowCommand(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Address> addresses = addressService.findAll(currentPage, recordsPerPage);

        request.setAttribute("addresses", addresses);

        int rows = addressService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("commandShow");
        request.setAttribute("showAddresses", command);

        return "listAddresses.jsp";
    }
}
