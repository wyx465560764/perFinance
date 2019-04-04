package servlet;

import DAO.productChangeDAO;
import entity.product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/changeproduct")
public class productChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session=req.getSession();
        PrintWriter out = resp.getWriter();
        int userid=Integer.valueOf(session.getAttribute("userid").toString());
        if(userid==Integer.valueOf(req.getParameter("userid"))){
            product product=new product();
            product.setProductid(Integer.valueOf(req.getParameter("productid")));
            product.setRemark(req.getParameter("remark"));
            product.setSum(Double.valueOf(req.getParameter("sum")));
            product.setProductname(req.getParameter("productname"));
            productChangeDAO productChangeDAO=new productChangeDAO();
            try {
                if(productChangeDAO.Permission(userid)){
                    if(productChangeDAO.productChange(product)){
                        out.println("<script language = javascript>alert('修改完成');");
                        out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
                    }else {
                        out.println("<script language = javascript>alert('修改失败，请联系专业人员');");
                        out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
                    }
                }else {
                    out.println("<script language = javascript>alert('您没有权限操作');");
                    out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            out.println("<script language = javascript>alert('您没有权限操作');");
            out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
        }
    }
}
