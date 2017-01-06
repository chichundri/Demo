<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Employee List ::</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<script type="text/javascript">
	<%@include file="../resources/js/addEdit.js" %>
	<%-- <%@include file="../resources/css/style.css" %> --%>
</script>
<script type="text/javascript">
$(function(){
	setTimeout(function(){
        $('#msgDiv').fadeOut('slow');
    },2500);
});
</script>
</head>
<body>
	<h3> Employee List </h3>
	<strong>${message}</strong>
	<c:if test="${not empty message}">
		<div class="alert alert-danger fade in col-sm-4" id="msgDiv" style="margin-left:35%;position: absolute;">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>${message}</strong>
		</div>
	</c:if>
	<form action="/Task1/search" name="searchForm" id="searchForm" method="POST">
	<div class="panel panel-default" style="margin-left:2%;">
		<div class="panel-body">
		Search By:
		<select name="searchdropdown" id="searchdropdown" style="margin-left:2%;">
			<option value="0" selected> -- Select -- </option>
			<option value="byEmpId">By Employee id</option>
			<option value="byName">By FirstName/LastName/email</option>
		</select>
		<input type="text" id="searchCriteria" style="margin-left:2%; width:25%;" placeholder="Enter Search text here..." name="searchCriteria">
		<button class="btn btn-success" style="margin-left:2%;" id="searchSubmitBtn">Search</button>
	</div>
	</div>
	</form>
	
	<form action="/Task1/search1" name="searchForm1" id="searchForm1" method="POST">
	<div class="panel panel-default" style="margin-left:2%;">
		<div class="panel-body">
		Search By:
		<input type="text" name="firstName" style="margin-left:2%;" placeholder="First Name"/><span style="margin-left:1%;">And</span>
		<input type="text" name="lastName" style="margin-left:1%;" placeholder="Last Name"/><span style="margin-left:1%;">And</span>
		<input type="text" name="email" style="margin-left:1%;" placeholder="Email Name"/>
		<button class="btn btn-success" style="margin-left:2%;" id="searchSubmitBtn1">Search</button>
	</div>
	</div>
	</form>
	
	<table class="table table-striped table-bordered table-hover table-condensed table-responsive" style="margin-top:3%;margin-left:1%;;width:95%">
	<tr style="padding-right: 0px;">
		<th style="padding-right: 0px;">#</th>
		<th style="padding-right: 0px;">Employee Id</th>
		<th style="padding-right: 0px;">Full Name</th>
		<th style="padding-right: 0px;">Email</th>
		<th style="padding-right: 0px;">Date Of Birth</th>
		<th style="padding-right: 0px;">Address</th>
		<th style="padding-right: 0px;">Creation Date</th>
		<th style="padding-right: 0px;">Dept Id</th>
		<th style="padding-right: 0px;">Name</th>
		<th style="padding-right: 0px;">Location</th>
		<th style="padding-right: 0px;">Actions</th>
	</tr>
	<c:forEach items="${employees}" var="employee">
		<tr>
			<td style="padding-right: 0px;"><input type="checkbox" class="empChk" name="empChk" value="${employee.id}"/></td>
			<td style="padding-right: 0px;">${employee.id}</td>
			<td style="padding-right: 0px;"> <a href="editEmpDetails/${employee.id}"> ${employee.firstName} ${employee.lastName} </a></td>
			<td style="padding-right: 0px;"> <a href="editEmpDetails/${employee.id}"> ${employee.email} </a></td>
			<td style="padding-right: 0px;">${employee.dob}</td>
			<td style="padding-right: 0px;">${employee.address}</td>
			<td style="padding-right: 0px;">${employee.createdDate}</td>
			<td style="padding-right: 0px;">${employee.department.id}</td>
			<td style="padding-right: 0px;">${employee.department.name}</td>
			<td style="padding-right: 0px;">${employee.department.location}</td>
			<td style="padding-right: 0px;">
				<a href="editEmpDetails/${employee.id}">Edit</a> &nbsp;
				| <a href="deleteEmployee/${employee.id}">Delete</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<a href="addEmployeeForm" >Add New Employee</a>
	<form action="editEmpDetails" id="editForm" method="POST">
		<input type="hidden" name="selectedEmpId" id="edit" value="" />
	</form>
	<form action="deleteEmployee" id="deleteForm" method="POST">
		<input type="hidden" name="selectedEmpId" id="delete" value="" />
	</form>
	<button id="editBtn" class="btn btn-success">Edit</button>
	<button id="deleteBtn" class="btn btn-danger">Delete</button>
</body>
</html>