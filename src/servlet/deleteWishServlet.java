package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import DAO.deleteWishDAO;
import entity.wish;

@WebServlet("/deletewish")
public class deleteWishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        int wishid=Integer.parseInt(req.getParameter("wishid"));
        String status=req.getParameter("status");
        int page=Integer.parseInt(req.getParameter("page"));
        int num=Integer.parseInt(req.getParameter("num"));
        wish wish=new wish();
        wish.setWishId(wishid);
        deleteWishDAO deleteWishDAO=new deleteWishDAO();
        try{
            if(deleteWishDAO.deleteWish(wish)){
                if(status.equals("1")){
                    if(num%10==1) {
                        req.getRequestDispatcher("wish-detail.jsp?page=" + (page - 1)).forward(req, resp);
                    }else {
                        req.getRequestDispatcher("wish-detail.jsp?page=" + page).forward(req, resp);
                    }
                }else if(status.equals("2")){
                    if(num%10==1) {
                        req.getRequestDispatcher("wish-search-result.jsp?page=" + (page - 1)).forward(req, resp);
                    }else {
                        req.getRequestDispatcher("wish-search-result.jsp?page=" + page).forward(req, resp);
                    }
                }
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