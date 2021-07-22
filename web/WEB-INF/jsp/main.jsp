<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #313439;color: white" >
<%--<%--%>
<%--    //m没有session就不能访问该页面--%>

<%--    if (request.getSession().getAttribute("session_user")==null){--%>
<%--        request.getRequestDispatcher("login.jsp").forward(request, response);--%>
<%--    }--%>
<%--%>--%>
<div class="container" style="background-color: rgb(247,247,255);height: 950px;background-image: url('./image/wallhaven-0q515q.jpg  ')">
    <div class="col-lg-11 col-lg-offset-1">
        <div class="col-lg-7" style="">
            <ul class="nav  nav-tabs nav-pills nav-justified">
                <!--   pills横向 nav-stacked纵向排列-->
                <%--                超级管理员--%>
                <c:if test="${session_user.roleId==0}">
                    <li ><a href="DormManagerServlet?action=list">管理员管理</a></li>
                    <li  ><a href="StudentsServlet?action=list">学生管理</a></li>
                    <li  ><a href="DormBuildServlet?action=list">宿舍楼管理</a></li>
                    <li  ><a href="RecordServlet?action=list">缺勤记录</a></li>
                </c:if>
<%--                宿舍管理员--%>
                <c:if test="${session_user.roleId==1}">
                    <li  ><a href="StudentsServlet?action=list">学生管理</a></li>
                    <li  ><a href="RecordServlet?action=list">缺勤记录</a></li>
                </c:if>
<%--                学生--%>
                <c:if test="${session_user.roleId==2}">
                    <li  ><a href="RecordServlet?action=list">缺勤记录</a></li>
                </c:if>


            </ul>
        </div>
        <div class="col-lg-4 col-lg-offset-1">

            <div class="dropdown pull-right" >
                <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" style="width: 100%">
<%--                    <div class="pull-right" style="margin-top: 9%;" >用户名:${session_user.name }</div>--%>
                当前登录用户名:${session_user.name }
                </button>
                <div class="dropdown-menu" style="width: 100%;text-align:center" >
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="LoginOutServlet">退出</a>
                </div>
            </div>
        </div>
        <div class="tab-content">
                <jsp:include page="${mainRight==null?'blank.jsp':mainRight}"></jsp:include>
        </div>
    </div>

</div>


</body>
</html>

