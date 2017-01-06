<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Edit Details ::</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script>
	<%@include file="../resources/js/adminMain.js" %>
</script>
<style>
	.error{
		color: red; font-weight: bold;
	}
</style>
</head>
<body>
	<center>
	<h2> Edit Employee Details </h2>
	<form:form method="POST" modelAttribute="emp" action="/Task1/editsave">
	<table class="table table-condensed table-responsive" style="margin-top:3%;margin-left:10%;;width:50%">
	<tr style="padding-right: 0px;">
		<td>First Name</td>
		<td>
			<form:input path="firstName"  />
			<%-- <form:hidden path="id" value="${emp.id}" /> --%>
		</td>
		<td><form:errors path="firstName" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><form:input path="lastName"  /></td>
		<td><form:errors path="lastName" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Date Of Birth</td>
		<td><form:input path="dob"  id="datePicker"/></td>
		<td><form:errors path="dob" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><form:input path="address"  /></td>
		<td><form:errors path="address" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><form:input path="email"  /></td>
		<td><form:errors path="email" cssClass="error"/></td>
	</tr>
	
	<tr>
		<td>Department</td>
		<td>
			<%-- <form:select path="department" items="${department}" itemValue="id" itemLabel="name" /> --%>
			
			<select name='department'>
			    <c:forEach items="${department}" var="department">
			        <c:if test="${department.id == emp.department.id}">
			            <option value="${department.id}" selected>${department.name}</option>
			        </c:if>
			        <c:if test="${department.id != emp.department.id}">
			            <option value="${department.id}">${department.name}</option>
			        </c:if>
			    </c:forEach>
			</select>
		</td>
		<td><form:errors path="" cssClass="error"/></td>
	</tr>
	
	
	<tr>
		<td style="float: right;"><input type="submit" class="btn btn-success" value="Update"/></td>
		<td><a href="${pageContext.request.contextPath}/employeeList" class="btn btn-danger" id="updateCancel">Cancel</a>
		</td>
		<td></td>
	</tr>
	</table>
	</form:form>
	</center>
</body>
</html>