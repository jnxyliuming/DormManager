package com.lm.dorm.servlet;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.bean.User;
import com.lm.dorm.service.DormBuildService;
import com.lm.dorm.service.DormBuildServiceImpl;
import com.lm.dorm.service.UserService;
import com.lm.dorm.service.UserServiceImpl;
import com.lm.dorm.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "DormManagerServlet")
public class DormManagerServlet extends HttpServlet {
    protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");//更改和查询的时候多次用到id直接提取出来
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService =new DormBuildServiceImpl();
        if (action!=null&&action.equals("list")){
            List<User> users = new ArrayList<User>();
            try {
                users = userService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(users.size());
            request.setAttribute("users",users);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormManagerList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if(action!=null&&action.equals("preAdd")){
            List<DormBuild> builds = null;
            try {
                builds = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("builds",builds);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormManagerAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if(action!=null&&action.equals("save")){
            System.out.println("this is save and id="+id);
            if (id==null || id.equals("")){//存
                String name  = request.getParameter("name");
                String password = request.getParameter("password");
                String sex =request.getParameter("sex");
                String tel = request.getParameter("tel");
                String[] dormBuildIds =request.getParameterValues("dormBuildId");
                System.out.println(name+password+sex+tel+ Arrays.toString(dormBuildIds));
                User user = new User(name,password,sex,tel,null,1);
                user.setDisabled(0);
                User user2 = (User)request.getSession().getAttribute("session_user");
                try {
                    userService.saveManager(user,dormBuildIds);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{//update
                String name = request.getParameter("name");
                String sex = request.getParameter("sex");
                String tel = request.getParameter("tel");
                String[] dormBuildIds =request.getParameterValues("dormBuildId");//更新到关系表不是主表
                //获取修改的参数
                System.out.println("update id ="+id);
                User user = new User();
                try {
                    user = userService.find(Integer.parseInt(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                user.setName(name);
                user.setSex(sex);
                user.setTel(tel);
                try {
                    userService.update(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    userService.updateManagerAndBuilds(user,dormBuildIds);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            response.sendRedirect(getServletContext().getContextPath()+"/DormManagerServlet?action=list");
        }else if(action!=null&&action.equals("preUpdate")){
            //跳转到修改页面
            //根据宿舍楼传入的id，保存宿舍楼信息，更改前先展示一波
            System.out.println("userid"+id);
            User user = new User();
            //                dormBuild = dormBuildService.find(Integer.parseInt(id));
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
            request.setAttribute("mainRight","/WEB-INF/jsp/dormManagerAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if (action!=null&&action.equals("deleteOrActive")){
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
            List<User> users = new ArrayList<User>();
            try {
                users = userService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(users.size());
            request.setAttribute("users",users);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormManagerList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);

        }

    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
