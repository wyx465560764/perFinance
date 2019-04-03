package servlet;

import DAO.submitOrderDAO;
import DAO.unfixedIncomedetailDAO;
import entity.order;
import entity.productDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/submitorder")
public class submitOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session=req.getSession();
        PrintWriter out = resp.getWriter();
        //明天写实名
        int userid=Integer.valueOf(session.getAttribute("userid").toString());
        int productid=Integer.valueOf(req.getParameter("productid"));
        String identity=req.getParameter("identity");
        double number=Double.valueOf(req.getParameter("number"));
        submitOrderDAO submitOrderDAO=new submitOrderDAO();
        order submitOrder=new order();
        submitOrder.setUserid(userid);
        submitOrder.setProductid(productid);
        submitOrder.setBuysum(number);
        try {
            if(submitOrderDAO.isVerified(userid).equals(identity)){
                //验证身份证
                if(submitOrderDAO.submitOrder(submitOrder)){
                    out.println("<script language = javascript>alert('认购成功，因该系统处于测试阶段暂不提供收费退费功能');");
                    out.println("location.href='"+req.getContextPath()+"user-position.jsp?page=1'</script>");
                }else {
                    out.println("<script language = javascript>alert('认购失败，发生未知错误！');");
                    out.println("location.href='"+req.getContextPath()+"user-position.jsp?page=1'</script>");
                }
            }else if (submitOrderDAO.isVerified(userid).equals("")){
                out.println("<script language = javascript>alert('认购失败，请先实名认证！');");
                out.println("location.href='"+req.getContextPath()+"identityBinding.jsp'</script>");
            }else {
                out.println("<script language = javascript>alert('身份证验证失败，请确认身份证号是否输入正确！');");
                out.println("location.href='"+req.getContextPath()+"unfixed-income-detail.jsp?productid="+productid+"'</script>");
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
