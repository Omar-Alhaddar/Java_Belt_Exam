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

<div id="lftpnl">
			<h2>Create a new Course:</h2>
			<form:form method="POST" action="/courses/new" modelAttribute="course">
				<h4 class="avoid">
					<form:label path="name">Name:</form:label>
					<form:input cssClass="fields" type="text" path="name"/>

				</h4>
				<h4 class="avoid">
					<form:label path="instructor">Instructor:</form:label>
					<form:input cssClass="fields" type="text" path="instructor"/>
				</h4>
				<h4 class="avoid">
					<form:label path="capacity">Capacity:</form:label>
					<form:input cssClass="fields" type="number" path="capacity"/>
				</h4>

				<input class="btn" type="submit" value="Create"/>
			</form:form>
			    <p><c:out value="${error}" /></p>
    			<p><form:errors path="course.*"/></p>
		</div>
		
		
</body>
</html>