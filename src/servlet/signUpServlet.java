package servlet;

import DAO.signUpDAO;
import entity.user;
import mailTool.mailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/signup")
public class signUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        user user=new user();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        if(user.getUsername().equals("")||user.getPassword().equals("")||user.getEmail().equals("")||req.getParameter("re-password").equals("")){
            out.println("<script language = javascript>alert('请完善所有资料再注册');");
            out.println("location.href='sign-up.jsp'</script>");
        }else {
            if(req.getParameter("password").equals(req.getParameter("re-password"))){
                signUpDAO signUpDAO=new signUpDAO();
                try {
                    if(signUpDAO.userIsExist(user)){
                        if(signUpDAO.emailIsExist(user)){
                            HttpSession session=req.getSession();
                            session.setAttribute("username",user.getUsername());
                            session.setAttribute("password",user.getPassword());
                            session.setAttribute("email",user.getEmail());
                            mailUtil mail = new mailUtil();
                            int num = (int)((Math.random()*9+1)*100000);   //生成六位验证码随机数
                            req.getSession().setAttribute("num", num);   //设置东西保存验证码
                            String receiver = user.getEmail();
                            String title = "个人理财系统邮箱验证";
                            String content = "这是个人理财管理系统发来的邮件，你正在执行该系统注册用户的操作，你的邮箱验证码为："+ String.valueOf(num) + ",请不要告诉别人哦！";
                            mail.SendMessage(receiver, title, content);
                            req.getSession().setAttribute("success","验证码已经发送到你的邮箱，请查看！");   //设置东西保存验证码
                            req.getRequestDispatcher("sign-up-last.jsp").forward(req, resp);
//                        if (signUpDAO.signUp(user)){
//                            System.out.println("注册成功");
//                        }
                        }else {
                            out.println("<script language = javascript>alert('邮箱已被注册请更换一个');");
                            out.println("location.href='login/sign-up.jsp'</script>");
                        }
                    }else {
                        out.println("<script language = javascript>alert('用户名重复请更换一个');");
                        out.println("location.href='login/sign-up.jsp'</script>");
                    }
                }catch (SQLException s){
                    s.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
//                System.out.println(user.getUsername()+user.getPassword());
            }else {
                out.println("<script language = javascript>alert('前后密码不一致，请检查');");
                out.println("location.href='login/sign-up.jsp'</script>");
            }
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
