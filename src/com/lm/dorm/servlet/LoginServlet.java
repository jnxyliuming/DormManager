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
		//���user
		User user = null;
		//��cookie��ֱ�Ӳ���
		if (cookies!=null && cookies.length!=0){
			for (Cookie cookie :
					cookies) {
				if (cookie.getName().equals("cookie_name_pass")){
					//��servlet���д���cookie��ֱ��ת��main.jsp
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
		//û�ҵ��Ļ�user���ǿյ�,˵��û�б����cookie
		//�ӱ���ȡ����
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
		//��user�ļ��飬Ҫ��Ϊ�����û������LoginServlet
		if (user==null) {
			System.out.println("login");
			request.setAttribute("error", "�˺��������������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("session_user", user);//���session
			if (remember!=null && remember.equals("remember-me")){//��ס����һ��
					CookieUtil.addCookie("cookie_name_pass",7*24*60*60,request,response,stuCode,password);
			}
			request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);//����web-inf�µ�jsp�ļ����յ������Ĳ���ֱ��ͨ���ļ�·�����ʣ��ض���ķ�ʽҲ�ǲ��е�
			System.out.println("main");
		}
	}

}
