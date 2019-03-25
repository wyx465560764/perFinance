package servlet;

import DAO.signUpDAO;
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

@WebServlet("/signuplast")
public class signUpValidation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public signUpValidation() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String fycodes = req.getParameter("verification");   //邮箱验证码
        int code = Integer.parseInt(fycodes);    //字符串转换为整形
        int num =  (int) req.getSession().getAttribute("num");  //得到刚发送的邮箱验证码
//        System.out.println("你的邮箱验证码为："+num);
        if(code == num){                                            //判断输入的验证码是否和邮箱收到的一致
            System.out.println("验证成功！");
            HttpSession session=req.getSession();
            user user=new user();
            user.setUsername(session.getAttribute("username").toString());
            user.setPassword(session.getAttribute("password").toString());
            user.setEmail(session.getAttribute("email").toString());
            signUpDAO signUpDAO=new signUpDAO();
            try {
                if (signUpDAO.signUp(user)){
                    System.out.println("注册成功");
                    session.invalidate();
                    out.println("<script language = javascript>alert('注册成功，请再次登录');");
                    out.println("location.href='"+req.getContextPath()+"index.jsp'</script>");
                }
            }catch (SQLException s){
                s.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else{
            System.out.println("修改失败");
            req.getSession().setAttribute("ValidaError", "输入的邮箱验证码错误！");   //设置东西保存验证码

        }
    }
}
