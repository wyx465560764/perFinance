package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class sesFilter implements Filter {

    public void init(FilterConfig filterConfig)throws ServletException{

    }
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException{
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String url=req.getRequestURI();
        if(req.getSession().getAttribute("userid")!=null||url.equals("/login")||url.equals("/forgot.jsp")||url.equals("/forgetSend")||url.equals("/forgetVerification")||url.equals("/sign-up.jsp")||url.equals("/signup")||url.equals("/signuplast")){
            chain.doFilter(request,response);
        }else {
            req.getSession().getServletContext().getRequestDispatcher("/index.jsp").forward(req,res);//页面前的'/'一定不能省略
        }
    }
    public void destroy(){

    }
}