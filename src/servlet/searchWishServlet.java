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

@WebServlet("/searchwish")
public class searchWishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        List list=new ArrayList();
        String wishName=req.getParameter("wishname");
        String status=req.getParameter("status");
        list.add(wishName);
        list.add(status);
        String planMonth=req.getParameter("planmonth");
        if(!planMonth.equals("")){
            String[] planMonthSplit=planMonth.split(" - ");
            String planMonthMin=planMonthSplit[0];
            String planMonthMax=planMonthSplit[1];
            list.add(planMonthMin);
            list.add(planMonthMax);
        }else {
            list.add("");
            list.add("");
        }

        String priceMin=req.getParameter("price_min");
        String priceMax=req.getParameter("price_max");
        list.add(priceMin);
        list.add(priceMax);
        String wishMonth=req.getParameter("wishmonth");
        if(!wishMonth.equals("")){
            String[] wishMonthSplit=wishMonth.split(" - ");
            String wishMonthMin=wishMonthSplit[0];
            String wishMonthMax=wishMonthSplit[1];
            list.add(wishMonthMin);
            list.add(wishMonthMax);
        }else {
            list.add("");
            list.add("");
        }
        HttpSession session=req.getSession();
        list.add(session.getAttribute("userid"));
        session.setAttribute("searchwish",list);
        req.getRequestDispatcher("wish-search-result.jsp?page=1").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
