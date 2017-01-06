<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Welcome ::</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>
	<div class="container" style="margin:10px 5% 10px 5%">
		<div class="row col-sm-6">
			<form method="POST" action="/Task1/search">
				<table class="table">
					<tr>
						<td>
							<input type="text" class="" name="empId" placeholder="Search By Employee id"/>
						</td>
						<td>
							<input type="submit" class="btn btn-success" value="Search" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="row col-sm-6"></div>
		<div class="row col-sm-6">
			<div align="center" style="border: 1px solid #ccc;padding:5px;margin:20px 20% 20px 20%;">
			  	<a href="${pageContext.request.contextPath}/registerForm">Add Employee</a>
			  | &nbsp;
				<a id="btnLogin" href="${pageContext.request.contextPath}/login">Edit</a>
			  | &nbsp;
				<a href="${pageContext.request.contextPath}/forgotPassword">Forgot Password</a>
			  <c:if test="${pageContext.request.userPrincipal.name != null}">
			  | &nbsp;
				<a href="${pageContext.request.contextPath}/logout">Logout</a>
			  </c:if>
			</div>
		</div>
	</div>
</body>
</html>