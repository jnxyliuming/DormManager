package com.lm.dorm.servlet;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.dao.DormBuilDaoImpl;
import com.lm.dorm.service.DormBuildService;
import com.lm.dorm.service.DormBuildServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DormBuildServlet")
public class DormBuildServlet extends HttpServlet {
    protected  void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("+========");
        String action = request.getParameter("action");
        System.out.println(action);
        String id = request.getParameter("id");//更改和查询的时候多次用到id直接提取出来
        System.out.println("userid"+id);
        //接入层调取服务层
        DormBuildService dormBuildService = new DormBuildServiceImpl();
        if(action!=null&&action.equals("list")){
            //查询宿舍楼信息
            //当list后面存在参数时会根据id查询dormbuild


            System.out.println(id);
            List<DormBuild> builds = new ArrayList<DormBuild>();
            if (id==null){
                //list后面没有参数时是全部查询
                try {
                    builds = dormBuildService.findAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("builds"+builds);

            }else if (id!=null){
                DormBuild build = null;
                try {
                    build = dormBuildService.find(Integer.parseInt(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(build);
                builds.add(build);
            }
            List<DormBuild> buildSelects= null;
            try {
                buildSelects = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("selectedId",id);//确定当前选择的宿舍楼的id保证选择完搜索后该option有selected的属性
            request.setAttribute("buildSelects",buildSelects);
            request.setAttribute("builds",builds);
            request .setAttribute("mainRight","/WEB-INF/jsp/dormBuildList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);


        }else if(action!=null&&action.equals("preAdd")){
            request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if(action!=null&&action.equals("save")){
            System.out.println(id);
            String name = new String(request.getParameter("inputName").getBytes("iso8859-1"),"utf-8");
            String remark = new String(request.getParameter("inputText").getBytes("iso8859-1"),"utf-8");
            System.out.println(name+remark);
            DormBuild dormBuild = null;//查询是否有重复的名字
            try {
                dormBuild = dormBuildService.findByName(name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("dormBuild"+dormBuild);
            System.out.println();
            if (id!=null && !id.equals("")){//选择了一个id,要么是查询了一个id要么是点击了一个id在save下就说明是更新操作
                //更新
                System.out.println("update operation");
                if(dormBuild != null) {//不null表示该楼存在，说明改了一个存在的name
                    request.setAttribute("error","This building already exists");
                    DormBuild build = null;
                    try {
                        build = dormBuildService.find(Integer.parseInt(id));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("changeId",build);//传入
                    request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
                    request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
                }else{//null表示输入的值合理
                    System.out.println("here has some changing nums"+name+remark);
                    try {
                        dormBuild = dormBuildService.find(Integer.parseInt(id));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    dormBuild.setName(name);
                    dormBuild.setRemark(remark);
                    try {
                        dormBuildService.update(dormBuild);//更新完成跑到list
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //更新select框
                    List<DormBuild> buildSelects= null;
                    try {
                        buildSelects = dormBuildService.findAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("buildSelects",buildSelects);
                    //更新表格框即全部查询
                    List<DormBuild> builds = new ArrayList<DormBuild>();
                    try {
                        builds = dormBuildService.findAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("builds",builds);
                    request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildList.jsp");
                    request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
                }
            }else{
                //保存
                System.out.println("save operation");
                if(dormBuild != null){
                    //已经存在
                    request.setAttribute("error","This building already exists");
                    request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
                    request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);

                }else{
                    //不存在，保存信息到数据库
                    DormBuild build = new DormBuild();
                    build.setName(name);
                    build.setRemark(remark);
                    build.setDisabled(0);
                    try {
                        dormBuildService.save(build);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //更新select框
                    List<DormBuild> buildSelects= null;
                    try {
                        buildSelects = dormBuildService.findAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("buildSelects",buildSelects);
                    //更新表格框即全部查询
                    List<DormBuild> builds = new ArrayList<DormBuild>();
                    try {
                        builds = dormBuildService.findAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("builds",builds);
                    request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildList.jsp");
                    request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
                }
            }



        }else if(action!=null&&action.equals("preUpdate")){
                //跳转到修改页面
            //根据宿舍楼传入的id，保存宿舍楼信息，更改前先展示一波
            DormBuild dormBuild = null;
            try {
                dormBuild = dormBuildService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("changeId",dormBuild);//传入
            request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if(action!=null&&action.equals("deleteOrActive")){
            String disabled = request.getParameter("disabled");
            DormBuild build = null;
            try {
                build = dormBuildService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            build.setDisabled(Integer.parseInt(disabled));
            //执行更新
            try {
                dormBuildService.update(build);//更新完成跑到list
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //更新select框
            List<DormBuild> buildSelects= null;
            try {
                buildSelects = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("buildSelects",buildSelects);
            //更新表格框即全部查询
            List<DormBuild> builds = new ArrayList<DormBuild>();
            try {
                builds = dormBuildService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("builds",builds);
            request.setAttribute("mainRight","/WEB-INF/jsp/dormBuildList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);

        }
    }
}
