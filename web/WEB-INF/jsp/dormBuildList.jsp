<%--
  Created by IntelliJ IDEA.
  User: liumi
  Date: 2021/6/10
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function dormBuildDeleteOrActive(dormBuildId,disabled) {
        if (confirm("您确定要删除这个宿舍楼吗?")){
            window.location="http://localhost:8080/DormManage/DormBuildServlet?action=deleteOrActive&id="+dormBuildId+"&disabled="+disabled;
        }
    }
</script>
<body style="width: 100%;margin: 0px">
    <form class="form-serarch" method="post" action="http://localhost:8080/DormManage/DormBuildServlet?action=list" style="margin-left: 0px;margin-top: 3%">
        <div class="col-lg-6" style="margin-top: 2%">
                        <span class="data_serarch">
                        <select class="form-control" style="margin-left: 0px;width: 70%;float: left" id="id" name="id">
<%--                            不使用id搜索完成的加入到selecter当中去可以使搜索完成的selecter不变--%>
			                <c:forEach items="${buildSelects}" var="build" varStatus="stat">
                                <option value="${build.id}" ${selectedId eq build.id?'selected':""}>${build.name}</option>
                            </c:forEach>
		                </select>
                             <button class="btn btn-info btn-md" style="float: left" onkeydown="" type="submit">搜索</button>
                        </span>

        </div>
        <div class="col-lg-5 col-lg-offset-1" style="margin-top: 2%;margin-right: 0px"><a href="http://localhost:8080/DormManage/DormBuildServlet?action=preAdd" class="btn btn-info pull-right">添加</a></div>


    </form>
<div class="col-lg-12">
    <table class="table table-bordered pull-right " style="margin-top: 2%">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${builds}" var="build" varStatus="stat">
<%--            varStatus表示循环变量的变量名--%>
            <tr>
<%--                获取序号--%>
                <td>${stat.index+1}</td>
                <td>${build.name}</td>
                <td>${build.remark}</td>
                <td>
                    <div class="">
                        <a href="http://localhost:8080/DormManage/DormBuildServlet?action=preUpdate&id=${build.id}" class="btn btn-primary btn-xs">修改</a>
                        <c:if test="${build.disabled == 0}">
                            <button class="btn btn-danger btn-xs" style="margin-left: 5%;" onclick="dormBuildDeleteOrActive(${build.id},1)">删除</button>
                        </c:if>
                        <c:if test="${build.disabled == 1}">
                            <button class="btn btn-success btn-xs" style="margin-left: 5%;" onclick="dormBuildDeleteOrActive(${build.id},0)">激活</button>
                        </c:if>

                    </div>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
