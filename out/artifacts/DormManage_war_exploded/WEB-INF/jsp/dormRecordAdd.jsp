<%--
  Created by IntelliJ IDEA.
  User: liumi
  Date: 2021/6/19
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function checkForm() {
            const inputName = $('#inputName').val();
            const date = $("#date").val();
            if (inputName==null||inputName==""||date==null||date==""){
                $("#error").html(" 信息填写不完整！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="col-lg-6 col-lg-offset-2">
    <form role="form" action="http://localhost:8080/DormManage/RecordServlet?action=save" method="post" onsubmit="return checkForm()">
        <c:if test="${changeId.id==null}">
            <p class="text-success" style="text-align: center;font-size: medium">添加记录</p>
        </c:if>
        <c:if test="${changeId.id!=null}">
        </c:if>
        <div class="form-group has-info" style="margin-top: 5%">
            <label class="col-lg-3 control-label" for="inputName">
                *输入学号
            </label>
            <div class="col-lg-9">
                <input type="hidden" name="id" value="${changeId.id}">
                <input type="text" class="form-control" id="inputName" name="stuCode" value="${changeId.name}">
            </div>
        </div>
        <div class="form-group has-info" style="margin-top: 5%">
            <label class="col-lg-3 control-label" for="date" style="margin-top: 5%">
                *选择时间
            </label>
            <div class="col-lg-9">
                <input id="date" type="date" name="date" value="" class="form-control"  style="margin-top: 5%"/>
            </div>
        </div>
        <div class="form-group has-warning">
            <label class="col-lg-3 control-label" for="inputText" style="margin-top: 5%">
                输入备注
            </label>
            <div class="col-lg-9" style="margin-top: 5%">
                <textarea class="form-control" rows="12" name="remark" id="inputText"></textarea>
            </div>
            <div class="col-lg-6 col-lg-offset-5" style="margin-top: 7%">
                <font id="error" style="text-align: center;font-family: 'Berlin Sans FB Demi';font-size: 16px" color="red">${error}</font><br>
                <input type="submit" class="btn btn-info" value="保存"  style="margin-top: 7%">
                <a href="http://localhost:8080/DormManage/RecordServlet?action=list" class="btn btn-info"  style="margin-top: 7%">返回</a>
            </div>
        </div>

    </form>
</div>
</body>
</html>
