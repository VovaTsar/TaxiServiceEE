package my.project.controller.command.show;

import my.project.controller.command.Command;
import my.project.model.domain.Coupon;
import my.project.model.service.CouponService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CouponShowCommand implements Command {
    private CouponService couponService;

    public CouponShowCommand(CouponService couponService) {
        this.couponService = couponService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Coupon> coupons = couponService.findAll(currentPage, recordsPerPage);

        request.setAttribute("coupons", coupons);

        int rows = couponService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("command");
        request.setAttribute("showCoupons", command);

        return "listCoupons.jsp";
    }
}
