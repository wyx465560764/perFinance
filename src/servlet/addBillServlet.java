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
import DAO.addBillWishDAO;

@WebServlet("/addbill")
public class addBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
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
        bill.setUserId(Integer.valueOf(session.getAttribute("userid").toString()));
//        bill.setPushTime(new Date());// new Date()为获取当前系统时间
//        if(bill.getBillName().equals("收入")){
//            bill.setType(1);
//        }else {
//            bill.setType(0);
//        }
        try {
            if(addBillWishDAO.getInstance().addBill(bill)){
                PrintWriter out = resp.getWriter();
                out.println("<script language = javascript>alert('新增完成');");
                out.println("location.href='"+req.getContextPath()+"bill-detail.jsp?page=1'</script>");
            }
        }catch (SQLException s){
            s.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
