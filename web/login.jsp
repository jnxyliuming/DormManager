<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>宿舍管理系统</title>
		<script type="text/javascript">
		function checkForm() {
			console.log("2312");
			var stuCode = $('#stuCode').val();
			var password = $('#password').val();
			if(stuCode==null||stuCode==""){
				$("#error").html("用户名为空");

				return false;
			}else if (password==null||password=="") {
				$("#error").html("密码为空");
				return false;
			}
			return true;
		}
		</script>
		<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
		<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
		<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		<!--jquery.validate-->
		
		<style type="text/css">
			body{
				background-image: url("./image/wallhaven-nmkmzm.jpg");
			font-size: 16px;}
			.form{background: rgba(255,255,255,0.2);width:400px;margin:100px auto;}
			#login_form{display: block;}
			#register_form{display: none;}
			.fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
			input[type="text"],input[type="password"]{padding-left:26px;}
			.checkbox{padding-left:21px;}

		</style>
		
	</head>
	<body>
	<%
		//m没有cookie就不会进行直接登录
		//对cookie的判断在index.jsp和loginSv中
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length!=0){
			for (Cookie cookie :
					cookies) {
				if (cookie.getName().equals("cookie_name_pass")){
					request.getRequestDispatcher("LoginServlet").forward(request, response);//当前如果存有cookie就发送到LoginServlet直接申请进入main.jsp
				}
			}
		}
	%>
	<div class="container">
		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="http://localhost:8080/DormManage/LoginServlet" id="login_form" method="post" onsubmit="return checkForm()">
				<h3 class="form-title">登录</h3>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i>
						<input class="form-control required" type="text" placeholder="stuCode" name="stuCode" autofocus="autofocus" maxlength="20" id="stuCode"/>
					</div>
					<div class="form-group">
							<i class="fa fa-lock fa-lg"></i>
							<input class="form-control required" type="password" placeholder="Password" name="password" maxlength="8" id="password"/>
					</div>
					<div class="form-group" >
						<hr/>
						<font id="error" height="12px" width="100px" color="red">${error}</font><br>
						<label class="checkbox">
							<input id="remember" name="remember" type="checkbox" value="remember-me" style="float: left">记住我<br>
						</label>
					</div>
					<div class="form-group"	style="margin-top: -40px">
						<input type="submit" class="btn btn-success pull-right" value="登录" style=""/>
					</div>
				</div>
			</form>
		</div>	
		</div>
	</body>
</html>