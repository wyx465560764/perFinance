package servlet;


import DAO.forgetDAO;
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

@WebServlet("/forgetVerification")
public class forgetValidation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public forgetValidation() {
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
//        String email = request.getParameter("email");
        String fycodes = request.getParameter("verification");   //邮箱验证码
        String password = request.getParameter("password");
        String repassword = request.getParameter("re-password");
        if(password.equals(repassword)){
            int code = Integer.parseInt(fycodes);    //字符串转换为整形
            int num =  (int) request.getSession().getAttribute("num");  //得到刚发送的邮箱验证码
            System.out.println("你的邮箱验证码为："+num);
            if(code == num){                                            //判断输入的验证码是否和邮箱收到的一致
                System.out.println("验证成功！");
                user user=new user();
                HttpSession session=request.getSession();
                user.setEmail(session.getAttribute("email").toString());
                user.setPassword(password);
                forgetDAO forgetDAO=new forgetDAO();
                try {
                    if(forgetDAO.forget(user)){
                        session.invalidate();
                        out.println("<script language = javascript>alert('密码修改成功，请重新登录');");
                        out.println("location.href='"+request.getContextPath()+"index.jsp'</script>");
                    }
                }catch (SQLException s){
                    s.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }


                //数据库操作
                //  	request.getSession().removeAttribute("num");
//            request.getSession().removeAttribute("success");
//            request.getRequestDispatcher("ThirdSteps.jsp").forward(request, response);
            }
            else{
                System.out.println("修改失败");
                out.println("<script language = javascript>alert('验证码错误，请重试');");
                out.println("location.href='"+request.getContextPath()+"forgot.jsp'</script>");
//            request.getSession().setAttribute("ValidaError", "输入的邮箱验证码错误！");   //设置东西保存验证码
//            request.getRequestDispatcher("SecondSteps.jsp").forward(request, response);
            }
        }else {
            out.println("<script language = javascript>alert('两次密码不一致');");
            out.println("location.href='"+request.getContextPath()+"forgot-change.jsp'</script>");
        }
    }

}