package com.lm.dorm.util;

import com.lm.dorm.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",servletNames = {"DormBuildServlet"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("this servlet is filtering");
        //判断是否登录，登录就放行，不登陆就拦截
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;//
        //转化为httpsv形式
        HttpSession session = httpServletRequest.getSession();
        //获取用户信息
        User user = (User) session.getAttribute("session_user");
        //不为空时用户已经登录
        if (user!=null){
            //放行方法
            chain.doFilter(req,resp);
        }else{
            //提示信息
            req.setAttribute("error","please login!");
            //未登录,登陆界面
            req.getRequestDispatcher("login.jsp").forward(httpServletRequest,resp);

        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
