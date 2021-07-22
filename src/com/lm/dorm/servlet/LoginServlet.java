package com.lm.dorm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lm.dorm.bean.User;
import com.lm.dorm.service.UserService;
import com.lm.dorm.service.UserServiceImpl;
import com.lm.dorm.util.CookieUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stuCode = "";
		String password ="";
		String remember = "";
		UserService userservice = new UserServiceImpl();
		Cookie[] cookies = request.getCookies();
		//填充user
		User user = null;
		//在cookie中直接查找
		if (cookies!=null && cookies.length!=0){
			for (Cookie cookie :
					cookies) {
				if (cookie.getName().equals("cookie_name_pass")){
					//该servlet当中存在cookie就直接转入main.jsp
					String[] sourceArray = cookie.getValue().split("#");
					try {
						user = userservice.findBystuCodeAndPass(sourceArray[0],sourceArray[1]);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println(user+"try to login from cookie");
				}
			}
		}
		//没找到的话user还是空的,说明没有保存的cookie
		//从表单获取参数
		if(user==null){
			stuCode = request.getParameter("stuCode");
			password = request.getParameter("password");
			remember = request.getParameter("remember");
			System.out.println(stuCode+password+remember);
			try {
				user = userservice.findBystuCodeAndPass(stuCode,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(user+"try to login from form");

		}
		//对user的检验，要是为空则就没法访问LoginServlet
		if (user==null) {
			System.out.println("login");
			request.setAttribute("error", "账号名或者密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("session_user", user);//填充session
			if (remember!=null && remember.equals("remember-me")){//记住密码一周
					CookieUtil.addCookie("cookie_name_pass",7*24*60*60,request,response,stuCode,password);
			}
			request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);//放在web-inf下的jsp文件是收到保护的不能直接通过文件路径访问，重定向的方式也是不行的
			System.out.println("main");
		}
	}

}
