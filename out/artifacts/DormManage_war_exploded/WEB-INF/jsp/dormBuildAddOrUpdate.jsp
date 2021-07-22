<%--
  Created by IntelliJ IDEA.
  User: liumi
  Date: 2021/6/10
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script>
        function checkForm() {
            const inputName = $('#inputName').val();
            if(inputName==null||inputName==""){
                $("#error").html(" 名 称 不 能 为 空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body style="width: 100%;">

<%--<div class="col-lg-6 col-lg-offset-2">--%>
<%--        <div style="text-align: center;font-size: 20px;font-family: Muiicons;">修改宿舍楼/添加宿舍楼</div>--%>
<%--        <div class="form-group has-info" style="margin-top: 5%">--%>
<%--            <label class="col-lg-3 control-label" for="inputName">--%>
<%--                <mark>*</mark>输入名字--%>
<%--            </label>--%>
<%--            <div class="col-lg-9">--%>
<%--                <input type="text" class="form-control" id="inputName" name="inputName">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--<div class="col-lg-6 col-lg-offset-2">--%>
<%--    <form role="form" action="http://localhost:8080/DormManage/DormBuildServlet?action=save" method="post" onsubmit="return checkForm()">--%>
<%--        <div class="form-group has-warning">--%>
<%--            <label class="col-lg-3 control-label" for="inputText">--%>
<%--                输入简介--%>
<%--            </label>--%>
<%--            <div class="col-lg-9">--%>
<%--                <textarea class="form-control" rows="12" name="inputText" id="inputText"></textarea>--%>
<%--            </div>--%>
<%--            <div class="col-lg-6 col-lg-offset-5" style="margin-top: 7%">--%>
<%--                <font id="error" style="text-align: center;font-family: 'Berlin Sans FB Demi';font-size: 16px" color="red"></font><br>--%>
<%--                <input type="submit" class="btn btn-info" value="保存"  style="margin-top: 7%">--%>
<%--                <a href="#" class="btn btn-info"  style="margin-top: 7%">返回</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</div>--%>
<div class="col-lg-6 col-lg-offset-2">
    <form role="form" action="http://localhost:8080/DormManage/DormBuildServlet?action=save" method="post" onsubmit="return checkForm()">
        <c:if test="${changeId.id==null}">
            <p class="text-success" style="text-align: center;font-size: medium">添加宿舍</p>
        </c:if>
        <c:if test="${changeId.id!=null}">
            <p class="text-success" style="text-align: center;font-size: medium">修改宿舍</p>
        </c:if>
        <div class="form-group has-info" style="margin-top: 5%">
            <label class="col-lg-3 control-label" for="inputName">
                *输入名字
            </label>
            <div class="col-lg-9">
                <input type="hidden" name="id" value="${changeId.id}">
                <input type="text" class="form-control" id="inputName" name="inputName" value="${changeId.name}">
            </div>
        </div>
        <div class="form-group has-warning">
            <label class="col-lg-3 control-label" for="inputText" style="margin-top: 5%">
                输入简介
            </label>
            <div class="col-lg-9" style="margin-top: 5%">
                <textarea class="form-control" rows="12" name="inputText" id="inputText">${changeId.remark}</textarea>
            </div>
            <div class="col-lg-6 col-lg-offset-5" style="margin-top: 7%">
                <font id="error" style="text-align: center;font-family: 'Berlin Sans FB Demi';font-size: 16px" color="red">${error}</font><br>
                <input type="submit" class="btn btn-info" value="保存"  style="margin-top: 7%">
                <a href="http://localhost:8080/DormManage/DormBuildServlet?action=list" class="btn btn-info"  style="margin-top: 7%">返回</a>
            </div>
        </div>

    </form>
</div>
</body>
</html>