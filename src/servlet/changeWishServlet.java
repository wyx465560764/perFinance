package servlet;

import DAO.changeBillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.changeWishDAO;
import entity.wish;

@WebServlet("/changewish")
public class changeWishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        if(req.getParameter("userid").equals(session.getAttribute("userid"))){
            wish wish=new wish();
            wish.setWishName(req.getParameter("wishname"));
            wish.setMoney(Double.valueOf(req.getParameter("money")));
            wish.setWishstatus(Integer.valueOf(req.getParameter("wishstatus")));
            try {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date plantime= sdf.parse(req.getParameter("plantime"));//字符串转成date对象类型
                wish.setPlanTime(plantime);
            }catch (ParseException p){
                p.printStackTrace();
            }
            wish.setWishId(Integer.valueOf(req.getParameter("wishid")));
            try {
                if(changeWishDAO.getInstance().changeWish(wish)){
                    PrintWriter out = resp.getWriter();
                    out.println("<script language = javascript>alert('修改完成');");
                    out.println("location.href='wish-detail.jsp?page=1'</script>");
                }
            }catch (SQLException s){
                s.printStackTrace();
            }
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
