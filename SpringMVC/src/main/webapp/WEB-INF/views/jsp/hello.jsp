<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>
			<c:if test="${not empty name}">
			Hello ${name}
		</c:if>
		</h2>
		<h3>
			<c:if test="${empty name}">
			Welcome Welcome!
			
			<a href="hello/Mayur">Click Here</a>
			</c:if>
		</h3>




	</center>
</body>
</html>