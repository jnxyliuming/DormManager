<%--
  Created by IntelliJ IDEA.
  User: liumi
  Date: 2021/6/18
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>function disab() {
        alert("不可修改,请到学生界面修改");
    }

    function dormManagerDeleteOrActive(userId,disabled) {
            if (confirm("您确定要删除/激活这个管理员吗?")){
                window.location="http://localhost:8080/DormManage/DormManagerServlet?action=deleteOrActive&id="+userId+"&disabled="+disabled;
            }
        }
    </script>
</head>

<body style="width: 100%;margin: 0px">
<form class="form-serarch" method="post" action="http://localhost:8080/DormManage/DormBuildServlet?action=list" style="margin-left: 0px;margin-top: 3%">
    <div class="col-lg-6" style="margin-top: 2%">
<%--                        <span class="data_serarch">--%>
<%--                        <select class="form-control" style="margin-left: 0px;width: 70%;float: left" id="id" name="id">--%>
<%--&lt;%&ndash;                            不使用id搜索完成的加入到selecter当中去可以使搜索完成的selecter不变&ndash;%&gt;--%>
<%--			                <c:forEach items="${buildSelects}" var="build" varStatus="stat">--%>
<%--                                <option value="${build.id}" ${selectedId eq build.id?'selected':""}>${build.name}</option>--%>
<%--                            </c:forEach>--%>
<%--		                </select>--%>
<%--                             <button class="btn btn-info btn-md" style="float: left" onkeydown="" type="submit">搜索</button>--%>
<%--                        </span>--%>

    </div>
    <div class="col-lg-5 col-lg-offset-1" style="margin-top: 2%;margin-right: 0px"><a href="http://localhost:8080/DormManage/DormManagerServlet?action=preAdd" class="btn btn-info pull-right">添加</a></div>
</form>
<div class="col-lg-12">
    <table class="table table-bordered pull-right " style="margin-top: 2%">
        <thead>
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>电话</th>
            <th>登录账户名</th>
            <th>权限</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="stat">
            <%--            varStatus表示循环变量的变量名--%>
            <c:if test="${user.roleId==1||user.roleId==0}">
                <tr>
                        <%--                获取序号--%>
                    <td>${stat.index+1}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.tel}</td>
                    <td>${user.stuCode}</td>
                            <c:if test="${user.roleId==1}">
                                <td>管理员</td>
                            </c:if>

                            <c:if test="${user.roleId==0}">
                                <td>超级管理员</td>
                            </c:if>
                    <td>
                        <div class="">
                            <a href="http://localhost:8080/DormManage/DormManagerServlet?action=preUpdate&id=${user.id}" class="btn btn-primary btn-xs">修改</a>
                            <c:if test="${user.disabled == 0}">
                                <button class="btn btn-danger btn-xs" style="margin-left: 5%;" onclick="dormManagerDeleteOrActive(${user.id},1)">删除</button>
                            </c:if>
                            <c:if test="${user.disabled == 1}">
                                <button class="btn btn-success btn-xs" style="margin-left: 5%;" onclick="dormManagerDeleteOrActive(${user.id},0)">激活</button>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:if>
            <c:if test="${user.roleId==2}">
                <tr>
                        <%--                获取序号--%>
                    <td>${stat.index+1}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.tel}</td>
                    <td>${user.stuCode}</td>
                            <td>学生</td>
                            <td>
                        <div class="">
                            <a href="" class="btn btn-primary btn-xs" onclick="disab()">修改</a>
                            <c:if test="${user.disabled == 0}">
                                <button class="btn btn-danger btn-xs" style="margin-left: 5%;" onclick="dormManagerDeleteOrActive(${user.id},1)">删除</button>
                            </c:if>
                            <c:if test="${user.disabled == 1}">
                                <button class="btn btn-success btn-xs" style="margin-left: 5%;" onclick="dormManagerDeleteOrActive(${user.id},0)">激活</button>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:if>

        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
