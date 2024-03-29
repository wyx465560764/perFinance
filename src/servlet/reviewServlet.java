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

import DAO.reviewDAO;

@WebServlet("/review")
public class reviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type","text/html; charset=utf-8");
        PrintWriter out=resp.getWriter();
        int orderid=Integer.valueOf(req.getParameter("orderid"));
        int status=Integer.valueOf(req.getParameter("status"));
        int operate=Integer.valueOf(req.getParameter("operate"));
        int page=Integer.parseInt(req.getParameter("page"));
        int num=Integer.parseInt(req.getParameter("num"));
        HttpSession session=req.getSession();
        int userid=Integer.valueOf(session.getAttribute("userid").toString());
        reviewDAO reviewDAO=new reviewDAO();
        try {
            if(operate==1){
                if(reviewDAO.isPersonal(userid,orderid)){
                    if(reviewDAO.reviewPass(orderid)){
                        if(num%10==1&&num!=1) {
                            req.getRequestDispatcher("product-review.jsp?page=" + (page - 1)+"&status="+status).forward(req, resp);
                        }else {
                            req.getRequestDispatcher("product-review.jsp?page=" + page+"&status="+status).forward(req, resp);
                        }
                    }else {
                        out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                        out.println("location.href='"+req.getContextPath()+"product-review.jsp?page="+page+"&status="+status+"'</script>");
                    }
                }else {
                    out.println("<script language = javascript>alert('身份验证失败，你没有权限完成该操作');");
                    out.println("location.href='"+req.getContextPath()+"product-review.jsp?page="+page+"&status="+status+"'</script>");
                }
            }else if(operate==2){
                if(reviewDAO.isPersonal(userid,orderid)){
                    if(reviewDAO.reviewDisagree(orderid)){
                        if(num%10==1&&num!=1) {
                            req.getRequestDispatcher("product-review.jsp?page=" + (page - 1)+"&status="+status).forward(req, resp);
                        }else {
                            req.getRequestDispatcher("product-review.jsp?page=" + page+"&status="+status).forward(req, resp);
                        }
                    }else {
                        out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                        out.println("location.href='"+req.getContextPath()+"product-review.jsp?page="+page+"&status="+status+"'</script>");
                    }
                }else {
                    out.println("<script language = javascript>alert('身份验证失败，你没有权限完成该操作');");
                    out.println("location.href='"+req.getContextPath()+"product-review.jsp?page="+page+"&status="+status+"'</script>");
                }
            }else {
                out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                out.println("location.href='"+req.getContextPath()+"product-review.jsp?page="+page+"&status="+status+"'</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
