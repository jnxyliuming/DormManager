package com.lm.dorm.servlet;

import com.lm.dorm.bean.Record;
import com.lm.dorm.bean.User;
import com.lm.dorm.service.RecordService;
import com.lm.dorm.service.RecordServiceImpl;
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

@WebServlet(name = "RecordServlet")
public class RecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String action =request.getParameter("action");
        RecordService recordService = new RecordServiceImpl();
        if (action!=null&&action.equals("list")){
            List<Record> records = new ArrayList<Record>();
            try {
                records = recordService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("records.size is"+records.size());
            request.setAttribute("records",records);
            request .setAttribute("mainRight","/WEB-INF/jsp/recordList.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }
        else if (action!=null&&action.equals("preAdd")){
            request .setAttribute("mainRight","/WEB-INF/jsp/dormRecordAdd.jsp");
            request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
        }else if (action!=null&&action.equals("save")){
            String date = request.getParameter("date");
            String stuCode = request.getParameter("stuCode");
            String remark =request.getParameter("remark");
            remark = "(Student number)"+stuCode+":"+remark;
            User user = null;
            UserService userService = new UserServiceImpl();
            try {
                user = userService.findByStuCode(stuCode);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (user==null){
                System.out.println("error");
                request.setAttribute("error","Student number does not exist");
                request .setAttribute("mainRight","/WEB-INF/jsp/dormRecordAdd.jsp");
                request.getRequestDispatcher("./WEB-INF/jsp/main.jsp").forward(request, response);
            }else{
                Integer id = user.getId();
                System.out.println("in servlet id is "+id);
                Record record= new Record(id,date,remark,0);
                try {
                    recordService.saveRecord(record);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect(getServletContext().getContextPath()+"/RecordServlet?action=list");
            }
            System.out.println(date+stuCode+"servlet");
        }else if (action!=null&&action.equals("delete")){
            String id = request.getParameter("id");
            String disabled = request.getParameter("disabled");
            System.out.println(id+disabled);
            Record record=null;
            try {
                record = recordService.find(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                recordService.delete(Integer.parseInt(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                recordService.update(record);
                response.sendRedirect(getServletContext().getContextPath()+"/RecordServlet?action=list");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
