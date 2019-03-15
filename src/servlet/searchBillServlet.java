package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchbill")
public class searchBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        List list = new ArrayList();
        String remark = req.getParameter("remark");
        String billName = req.getParameter("billname");
        list.add(remark);
        list.add(billName);
        String billMonth = req.getParameter("billmonth");
        if (!billMonth.equals("")) {
            String[] billMonthSplit = billMonth.split(" - ");
            String billMonthMin = billMonthSplit[0];
            String billMonthMax = billMonthSplit[1];
            list.add(billMonthMin);
            list.add(billMonthMax);
        } else {
            list.add("");
            list.add("");
        }
        String priceMin = req.getParameter("price_min");
        String priceMax = req.getParameter("price_max");
        list.add(priceMin);
        list.add(priceMax);
        String pushmonth = req.getParameter("pushmonth");

        if (!pushmonth.equals("")) {
            String[] pushMonthSplit = pushmonth.split(" - ");
            String pushMonthMin = pushMonthSplit[0];
            String pushMonthMax = pushMonthSplit[1];
            list.add(pushMonthMin);
            list.add(pushMonthMax);
        } else {
            list.add("");
            list.add("");
        }
        HttpSession session = req.getSession();
        list.add(session.getAttribute("userid"));
        session.setAttribute("searchbill", list);
        req.getRequestDispatcher("bill-search-result.jsp?page=1").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
