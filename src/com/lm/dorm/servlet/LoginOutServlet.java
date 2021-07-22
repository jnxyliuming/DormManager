package com.lm.dorm.servlet;

import com.lm.dorm.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
   public LoginOutServlet(){
       super();
   }
   protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
       //清除session
        request.getSession().removeAttribute("session_user");;
        //清除cookie
       //根据名字查找
        Cookie cookie = CookieUtil.getCookieByName(request,"cookie_name_pass");
        if (cookie!=null){
            cookie.setMaxAge(0);//设置为0使之失效
            cookie.setPath(request.getContextPath());//设置范围
            response.addCookie(cookie);
        }
        response.sendRedirect("login.jsp");
   }
}
