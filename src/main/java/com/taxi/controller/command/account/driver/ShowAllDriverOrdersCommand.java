package com.taxi.controller.command.account.driver;


import com.taxi.myUtils.LoginedUserUtils;
import com.taxi.controller.command.Command;
import com.taxi.controller.command.RoutesJSP;
import com.taxi.model.entity.Order;
import com.taxi.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ShowAllDriverOrdersCommand implements Command {

    private OrderService orderService;

    private final Logger LOGGER = Logger.getLogger(ShowAllDriverOrdersCommand.class);


    public ShowAllDriverOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int idDriver = LoginedUserUtils.getLoginedUser(session).getPersonId();
        int pageNumber;
        int totalNumberRecords = (int) orderService.getAllOrdersCount(idDriver);
        int recordPerPage = 5;
        int startIndex;
        int numberOfPages;
        String sPageNo = request.getParameter("pagination");
        LOGGER.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        List<Order> orderList = orderService.getAllOrderByIdDriver(idDriver, startIndex, recordPerPage);
        LOGGER.info("get lis Orders");
        request.setAttribute("orderList", orderList);
        request.setAttribute("recordPerPage", recordPerPage);
        numberOfPages = totalNumberRecords / recordPerPage;
        if (totalNumberRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        LOGGER.info("set request numberOfPages " + numberOfPages);
        request.setAttribute("pageNumbers", numberOfPages);
        return RoutesJSP.SHOW_DRIVER_ORDERS;
    }

    private int getPageNumber(String strNumber) {
        try {
            return Integer.valueOf(strNumber);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
