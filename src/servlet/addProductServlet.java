package servlet;

import entity.product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import DAO.addProductDAO;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addproduct")
public class addProductServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        product product=new product();
        product.setUserid(Integer.valueOf(session.getAttribute("userid").toString()));
        product.setProductname(req.getParameter("productname"));
        product.setTypeid(Integer.valueOf(req.getParameter("typeid")));
//        product.setEarntype(Integer.valueOf(req.getParameter("earntype")));
        product.setSum(Double.valueOf(req.getParameter("sum")));
        product.setNowprice(Double.valueOf(req.getParameter("nowprice")));
        product.setExpectedincome(Double.valueOf(req.getParameter("expectedincome")));
        product.setRemark(req.getParameter("remark"));
        PrintWriter out = resp.getWriter();
        try {
            if(addProductDAO.getInstance().addProduct(product)){
                out.println("<script language = javascript>alert('新增完成');");
                out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
            }else {
                out.println("<script language = javascript>alert('发生未知错误，请联系专业人员');");
                out.println("location.href='"+req.getContextPath()+"product-change.jsp?page=1'</script>");
            }
        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}