package com.lm.dorm.servlet;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.bean.User;
import com.lm.dorm.service.DormBuildService;
import com.lm.dorm.service.DormBuildServiceImpl;
import com.lm.dorm.service.UserService;
import com.lm.dorm.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentsServlet")
public class StudentsServlet extends HttpServlet {
    protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String id= request.getParameter("id");
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService = new DormBuildServiceImpl();
        if (action!=null && action.equals("list")){
            List<User> stus = new ArrayList<User>();
            try {
                stus = userService.findAllStu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(stus.size());
            request.setAttribute("stus",stus);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormStuList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);


        }else if (action!=null && action.equals("preAdd")){
            List<DormBuild> builds = null;
            try {
                builds = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("builds",builds);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormStudentsAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if (action!=null && action.equals("save")){
            if (id==null||id.equals("")){//add
                String name  = request.getParameter("name");
                String password = request.getParameter("password");
                String sex =request.getParameter("sex");
                String tel = request.getParameter("tel");
                String dormBuild = request.getParameter("dormBuildId");
                String stu_code =request.getParameter("stu_code");
                System.out.println(name+password+sex+tel+dormBuild);
                User user_c = (User)request.getSession().getAttribute("session_user");
                System.out.println(user_c.getId()+"is user_c's id");
                System.out.println("in add id is ?"+id);
                System.out.println(dormBuild+"dormBuild");
                User user = new User(name,password,sex,tel,Integer.parseInt(dormBuild),2,user_c.getId(),0,stu_code);
                try {
                    userService.add(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                List<User> stus = new ArrayList<User>();
                try {
                    stus = userService.findAllStu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(stus.size());
                request.setAttribute("stus",stus);
                request .setAttribute("mainRight","/WEB-INF/jsp/dormStuList.jsp");
                request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
            }else{//update
                System.out.println("in update id is "+id);
                String name = request.getParameter("name");
                String sex = request.getParameter("sex");
                String tel = request.getParameter("tel");
                String dormBuild = request.getParameter("dormBuildId");
                System.out.println(name+tel+sex+dormBuild);
                User user = new User();
                try {
                    user = userService.find(Integer.parseInt(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                user.setName(name);
                user.setSex(sex);
                user.setTel(tel);
                user.setDormBuildId(Integer.parseInt(dormBuild));
                try {
                    userService.update(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect(getServletContext().getContextPath()+"/StudentsServlet?action=listS");
            }

        }else if(action!=null && action.equals("preUpdate")){
            id = request.getParameter("id");
            User user = new User();
            try {
                user = userService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                user  = userService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<DormBuild> builds = null;
            try {
                builds = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("builds",builds);
            request.setAttribute("user",user);//传入
            request.setAttribute("mainRight","/WEB-INF/jsp/dormStudentsAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);


        }else if (action!=null&&action.equals("deleteOrActive")){
            System.out.println("1231");
            id = request.getParameter("id");
            String disabled = request.getParameter("disabled");
            System.out.println("delete or activate id is "+id+"and disabled="+disabled);
            User user =null;
            try {
                user = userService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            user.setDisabled(Integer.parseInt(disabled));
            System.out.println(user.getDisabled());
            try {
                userService.updateDis(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<User> stus = new ArrayList<User>();
            try {
                stus = userService.findAllStu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(stus.size());
            request.setAttribute("stus",stus);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormStuList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);

        }

    }
}
