<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form:form method="POST" action="/courses/update" modelAttribute="course">
	<form:input path="id" type="hidden"/>
	<form:input path="joinedUsers" type="hidden"/>
	<table>
		<tr>
			<td><form:label path="name">Name:</form:label></td>
			<td><form:input path="name" class="input" /></td>
			<td><form:errors path="name" /></td>
		</tr>
			
		<tr>
			<td><form:label path="instructor">Instructor:</form:label></td>
			<td><form:input path="instructor" class="input" /></td>
			<td><form:errors path="instructor" /></td>
		</tr>
		<tr>
			<td><form:label path="capacity">Capacity:</form:label></td>
			<td><form:input path="capacity" class="input" type="number"/></td>
			<td><form:errors path="capacity" /></td>
		</tr>
	</table>
	<div class="buttons has-addons">

		<input type="submit" value="Update" class="button is-dark" />
	</div>
</form:form>
					
</body>
</html>