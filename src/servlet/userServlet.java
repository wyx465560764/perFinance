package servlet;

import DAO.userDAO;
import entity.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class userServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
        if(username.equals("")){
            out.println("<script language = javascript>alert('用户名为空');");
            out.println("location.href='index.jsp'</script>");
        }
        else if (password.equals("")){
            out.println("<script language = javascript>alert('密码为空');");
            out.println("location.href='index.jsp'</script>");
        }else {
            try{
                DAO.userDAO userDAO=new userDAO();
                user user=new user();
                user.setUsername(username);
                user.setPassword(password);
                user userResult=userDAO.login(user);
                HttpSession session=req.getSession();
                if(userResult.getUserid()!=null){
                    session.setAttribute("userid",user.getUserid());
                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("type",user.getType());
                    req.getRequestDispatcher("main.jsp").forward(req, resp);
//                    out.println("<script language = javascript> location.href='finance/main.jsp'</script>");
                }else {
                    out.println("<script language = javascript>alert('用户名或密码错误');");
                    out.println("location.href='index.jsp'</script>");
                }
//                req.getRequestDispatcher("finance/main.jsp").forward(req, resp);
//                System.out.println(session.getAttribute("userid"));
//                System.out.println(session.getAttribute("username"));
//                System.out.println(session.getAttribute("type"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
