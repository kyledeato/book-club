<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<title>Home</title>
</head>
<body>
	<div class="container">
	<h1>Welcome <c:out value="${thisUser.userName}"></c:out></h1>
	<h4>Books from everyone's shelves</h4>
	</div>
	<div class="container">
	<a href="/logout">logout</a>
	<a href="/books/new">+ Add a book</a>
	</div>
	
	<table class="table">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Title</th>
				<th scope="col">Author Name</th>
				<th scope="col">Posted By</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${books }">
			<tr>
				<td><c:out value="${book.id }"></c:out></td>
				<td><a href="books/${book.id}"><c:out value="${book.title }"></c:out></a></td>
				<td><c:out value="${book.author }"></c:out></td>
				<td><c:out value="${book.user.getUserName()}"></c:out>  </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>