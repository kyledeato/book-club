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
<title>Show</title>
</head>
<body>
	<a href="/books">Home</a>
	<h1><c:out value="${book.title}"></c:out></h1>
	<p><c:out value="${book.thoughts}"></c:out></p>
	
	<c:if test="${book.user.id == user_id }">
	<a href="/books/${book.id}/edit">Edit</a>
	
	<form action="/books/delete/${book.id}" method="post" class="form-inline">
		<input type="hidden" name="_method" value="delete"> 
		<input class="" type="submit" value="Delete">
	</form>
	</c:if>
	
	
</body>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>