<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/style.css">
	<title>Courses</title>
</head>

<body>
	<div id="container">
		<h1 class="fltleft">Welcome, <c:out value="${user.name}"/></h1>
		<h2><a class="fltright" href="/logout">Logout</a></h2>

		<h2>Courses:</h2>
		<table class="table table-striped table-bordered table-hover">
			<thead>
  				<tr>
   				<th scope="col">Courses</th>
					    <th scope="col">Instructor</th>
					    <th scope="col">Signups</th>
					    <th scope="col">Action</th>
  				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="course">
  				<tr>
  					<td><a href="/courses/${course.id}"><c:out value="${course.name}"/></a></td>
				    <td><c:out value="${course.instructor}"/></td>
				    <td>${course.getJoinedUsers().size()} / ${course.capacity}</td>
				    <td>
					<c:choose>
						<c:when test="${course.getJoinedUsers().indexOf(user)!= -1}">
							Already Added
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${course.getJoinedUsers().size() == course.capacity}">
									Full
								</c:when>
								<c:otherwise>
									<a href="/courses/add/${course.id}">Add</a>
								</c:otherwise>
							</c:choose>	
						</c:otherwise>
					</c:choose>
					</td>
   
  				</tr>
  				</c:forEach>
			</tbody>
		</table>
			<form action="/courses/new" method="post">
				<input type="submit" value="Add new course"/>
			</form>
		
		</div>

</body>
</html>