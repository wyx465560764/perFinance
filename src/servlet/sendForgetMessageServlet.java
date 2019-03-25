package servlet;


import entity.user;
import mailTool.mailUtil;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forgetSend")
public class sendForgetMessageServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public sendForgetMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            doPost(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            //设置编码
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type","text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            try {
                mailUtil mail = new mailUtil();
                int num = (int)((Math.random()*9+1)*100000);   //生成六位验证码随机数
                System.out.println("你的邮箱验证码为："+num);
                request.getSession().setAttribute("num", num);   //设置东西保存验证码
                user user=new user();
                if(request.getParameter("email")==null){
                    out.println("<script language = javascript>alert('请输入邮箱');");
                    out.println("location.href='"+request.getContextPath()+"login/sign-up.jsp'</script>");
                }
                user.setEmail(request.getParameter("email"));
                request.getSession().setAttribute("email",user.getEmail());
//                user user = (user) request.getSession().getAttribute("finduser");  //得到修改密码对象的Object
                String receiver = user.getEmail();
                String title = "个人理财系统用户找回密码";
                String content = "这是个人理财管理系统发来的邮件，你正在执行该系统找回密码的操作，你的邮箱验证码为："+ String.valueOf(num) + ",请不要告诉别人哦！";
                mail.SendMessage(receiver, title, content);
//                request.getSession().setAttribute("success","验证码已经发送到你的邮箱，请查看！");   //设置东西保存验证码
            } catch (AddressException e) {
                // TODO Auto-generated catch block
                System.out.println("验证码发送失败");
                e.printStackTrace();
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            request.getRequestDispatcher("forgot-change.jsp").forward(request, response);
        }

}
