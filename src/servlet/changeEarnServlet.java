package servlet;

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
import DAO.changeEarnDAO;
import entity.product;

@WebServlet("/addearn")
public class changeEarnServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        if(req.getParameter("userid").equals(session.getAttribute("userid"))){
            product product=new product();
            product.setNowprice(Double.valueOf(req.getParameter("newprice")));
            product.setProductid(Integer.valueOf(req.getParameter("productid")));
            try {
                if(changeEarnDAO.getInstance().changeEarn(product)){
                    PrintWriter out = resp.getWriter();
                    out.println("<script language = javascript>alert('更新完成');");
                    out.println("location.href='"+req.getContextPath()+"product-earn.jsp?page=1'</script>");
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
