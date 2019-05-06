package servlet;

import DAO.reviewUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/reviewuser")
public class reviewUserServlet extends HttpServlet {
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
        int operate=Integer.valueOf(req.getParameter("operate"));
        int page=Integer.parseInt(req.getParameter("page"));
        int num=Integer.parseInt(req.getParameter("num"));
        HttpSession session=req.getSession();
        int userid=Integer.valueOf(session.getAttribute("userid").toString());
        reviewUserDAO reviewUserDAO=new reviewUserDAO();
        try {
            if(operate==1){
                if(reviewUserDAO.isPersonal(userid,orderid)){
                    if(reviewUserDAO.disReview(orderid)){
//                        if(num%10==1) {
//                            req.getRequestDispatcher("user-position.jsp?page=" + (page - 1)).forward(req, resp);
//                        }else {
                            req.getRequestDispatcher("user-position.jsp?page=" + page).forward(req, resp);
//                        }
                    }else {
                        out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                        out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
                    }
                }else {
                    out.println("<script language = javascript>alert('身份验证失败，你没有权限完成该操作');");
                    out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
                }
            }if(operate==2){
                if(reviewUserDAO.isPersonal(userid,orderid)){
                    if(reviewUserDAO.sell(orderid)){
//                        if(num%10==1) {
//                            req.getRequestDispatcher("user-position.jsp?page=" + (page - 1)).forward(req, resp);
//                        }else {
                            req.getRequestDispatcher("user-position.jsp?page=" + page).forward(req, resp);
//                        }
                    }
                }else {
                    out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                    out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
                }
            }if(operate==3){
                if(reviewUserDAO.isPersonal(userid,orderid)){
                    if(reviewUserDAO.disSell(orderid)){
//                        if(num%10==1) {
//                            req.getRequestDispatcher("user-position.jsp?page=" + (page - 1)).forward(req, resp);
//                        }else {
                            req.getRequestDispatcher("user-position.jsp?page=" + page).forward(req, resp);
//                        }
                    }else {
                        out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                        out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
                    }
                }else {
                    out.println("<script language = javascript>alert('身份验证失败，你没有权限完成该操作');");
                    out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
                }
            }else {
                out.println("<script language = javascript>alert('发生错误，请正确操作系统');");
                out.println("location.href='"+req.getContextPath()+"user-position.jsp?page="+page+"'</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
