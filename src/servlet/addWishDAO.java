package servlet;

import DAO.addBillWishDAO ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import entity.wish;

@WebServlet("/addwish")
public class addWishDAO extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        wish wish=new wish();
        wish.setWishName(req.getParameter("wishname"));
        wish.setMoney(Double.valueOf(req.getParameter("money")));
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date planTime= sdf.parse(req.getParameter("plantime"));//字符串转成date对象类型
            wish.setPlanTime(planTime);
        }catch (ParseException p){
            p.printStackTrace();
        }
        wish.setUserId(Integer.valueOf(session.getAttribute("userid").toString()));
//        wish.setPushTime(new Date());// new Date()为获取当前系统时间
        wish.setWishstatus(Integer.valueOf(req.getParameter("wishstatus")));
        try {
            if(addBillWishDAO.getInstance().addWish(wish)){
                PrintWriter out = resp.getWriter();
                out.println("<script language = javascript>alert('新增完成');");
                out.println("location.href='wish-detail.jsp?page=1'</script>");
            }
        }catch (SQLException s){
            s.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
