<%--
  Created by IntelliJ IDEA.
  User: liumi
  Date: 2021/6/19
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script>
        function checkForm1() {
            const inputName = $('#inputName').val();
            const inputPassword = $('#inputPassword').val();
            const rePassword = $('#rePassword').val();
            const inputSex = $('#inputSex').val();
            const phone= $('#phone').val();
            //获取复选框
            const dormBuildIdCheckBox = document.getElementsByName("dormBuildId");
            const checkBoxValue =[];
            for (let i = 0; i < dormBuildIdCheckBox.length; i++) {
                if (dormBuildIdCheckBox[i].checked){
                    checkBoxValue.push(dormBuildIdCheckBox[i].value());
                }
            }
            if(inputName==null||inputName==""||rePassword==""||inputPassword==""||inputSex==""||phone=="" || checkBoxValue.length<1){
                $("#error").html(" 信息填写不完整！");
                return false;
            }else if(rePassword!=inputPassword){
                $("#error").html(" 两次密码填写不一致！");
                return false;
            }else if (!/^1[345678]\d(9)$/.test(phone)){
                $("#error").html(" 手机号格式错误！");
                return false;
            }
            return true;
        }
    </script>
</head>

<body style="width: 100%;">
<div class="col-lg-6 col-lg-offset-2">
    <form role="form" action="http://localhost:8080/DormManage/StudentsServlet?action=save" method="post" onsubmit="return checkForm1()">
        <c:if test="${user.id==null}">
            <p class="text-success" style="text-align: center;font-size: medium">添加学生</p>
        </c:if>
        <c:if test="${user.id!=null}">
            <p class="text-success" style="text-align: center;font-size: medium">修改学生</p>
        </c:if>
        <div class="form-group">
            <label for="inputName" class="col-sm-3 control-label" style="margin-top: 4%">姓名</label>
            <div class="col-sm-9" style="margin-top: 3%">
                <input type="hidden" name="id" value="${user.id}">
                <input type="text" class="form-control" id="inputName"
                       placeholder="请输入姓名" name="name" value="${user.name}">
            </div>
        </div>
        <c:if test="${user.id==null}">
            <div class="form-group">
                <label for="inputPassword" class="col-sm-3 control-label" style="margin-top: 4%">密码</label>
                <div class="col-sm-9" style="margin-top: 3%">
                    <input type="password" class="form-control" id="inputPassword" name="password" value="${user.password}">
                </div>
            </div>
            <div class="form-group">
                <label for="rePassword" class="col-sm-3 control-label" style="margin-top: 4%">再次输入密码</label>
                <div class="col-sm-9" style="margin-top: 3%">
                    <input type="password" class="form-control" id="rePassword" name="rePassword" value="${user.password}">
                </div>
            </div>
            <div class="form-group">
                <label for="rePassword" class="col-sm-3 control-label" style="margin-top: 4%">学号</label>
                <div class="col-sm-9" style="margin-top: 3%">
                    <input type="text" class="form-control" id="stu_code" name="stu_code" value="${user.stucode}">
                </div>
            </div>
        </c:if>
        <c:if test="${user.id!=null}">

        </c:if>

        <div class="form-group">
            <label for="inputSex" class="col-sm-3 control-label" style="margin-top: 4%">性别</label>
            <div class="col-sm-9" style="margin-top: 3%">
                <select class="form-control" style="margin-left: 0px;width: 70%;float: left" id="inputSex" name="sex" value="${user.sex}">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label" style="margin-top: 4%">电话</label>
            <div class="col-sm-9" style="margin-top: 3%">
                <input type="text" class="form-control" id="phone" name="tel" value="${user.tel}">
            </div>
        </div>
        <div class="form-group">
            <label for="build" class="col-sm-3 control-label" style="margin-top: 3%">住宿楼</label>
            <div class="col-sm-9" style="margin-top: 3%" id="build">
                <c:forEach items="${builds}" var="build" >
                    <label class="checkbox-inline">
                        <input type="radio" name="dormBuildId" value="${build.id}">${build.name}
                    </label>
                </c:forEach>
            </div>
        </div>



        <div class="col-lg-6 col-lg-offset-5" style="margin-top: 7%">
            <font id="error" style="text-align: center;font-family: 'Berlin Sans FB Demi';font-size: 16px" color="red"></font><br>
            <input type="submit" class="btn btn-info" value="保存"  style="margin-top: 7%">
            <a href="http://localhost:8080/DormManage/StudentsServlet?action=list" class="btn btn-info"  style="margin-top: 7%">返回</a>
        </div>
    </form>
</div>
</body>
</html>