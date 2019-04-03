package servlet;

import DAO.identityDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/identity")
public class isIdentityServlet extends HttpServlet {
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
        identityDAO identityDAO=new identityDAO();
        String identity=req.getParameter("identity");
        try {
            if(identityDAO.isRealName(userid)){
                out.println("<script language = javascript>alert('已实名认证，请勿再次验证');");
                out.println("location.href='"+req.getContextPath()+"market-first.jsp'</script>");
            }else {
                if(identityDAO.identity(userid,identity)){
                    out.println("<script language = javascript>alert('验证完成');");
                    out.println("location.href='"+req.getContextPath()+"market-first.jsp'</script>");
                }else {
                    out.println("<script language = javascript>alert('发生未知错误，请联系专业人员');");
                    out.println("location.href='"+req.getContextPath()+"market-first.jsp'</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
