package servlet;

import DAO.changeBillDAO;
import entity.bill;

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

@WebServlet("/changebill")
public class changeBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        if(req.getParameter("userid").equals(session.getAttribute("userid"))){
            bill bill=new bill();
            bill.setBillName(Integer.valueOf(req.getParameter("billname")));
            bill.setMoney(Double.valueOf(req.getParameter("money")));
            bill.setRemark(req.getParameter("remark"));
            try {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date spenttime= sdf.parse(req.getParameter("spenttime"));//字符串转成date对象类型
                bill.setSpentTime(spenttime);
            }catch (ParseException p){
                p.printStackTrace();
            }
            bill.setBillId(Integer.valueOf(req.getParameter("billid")));
            try {
                if(changeBillDAO.getInstance().changeBill(bill)){
                    PrintWriter out = resp.getWriter();
                    out.println("<script language = javascript>alert('修改完成');");
                    out.println("location.href='"+req.getContextPath()+"bill-detail.jsp?page=1'</script>");
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
