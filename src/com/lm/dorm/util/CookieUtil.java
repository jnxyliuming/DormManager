package com.lm.dorm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void addCookie(String cookie_name_pass, int time, HttpServletRequest request, HttpServletResponse response, String stuCode, String password) {

        Cookie cookie =getCookieByName(request,cookie_name_pass);
        if (cookie!=null){
            cookie.setValue(stuCode+"#"+password);
        }else{

            cookie = new Cookie(cookie_name_pass,stuCode+ "#" +password);
            cookie.setMaxAge(time);//设置时间
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
    }

   public static Cookie getCookieByName(HttpServletRequest request, String cookie_name_pass) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length!=0){
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals(cookie_name_pass)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
