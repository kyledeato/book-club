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
<title>Insert title here</title>
</head>
<body>
	<h1>Edit</h1>
	<a href="/books">Home</a>
	<form:form method="post" action="/books/edit/${book.id}" modelAttribute="book">
	<input type="hidden" name="_method" value="put"> 
		<div class="form-group">
	    	<form:label path="title">Title: </form:label>
	    	<form:input path="title"/>
			<form:errors path="title" />
  		</div>
		<div class="form-group">
	    	<form:label path="author">Author: </form:label>
	    	<form:input path="author"/>
			<form:errors path="author" />
  		</div>
		<div class="form-group">
	    	<form:label path="thoughts">My Thoughts: </form:label>
	    	<form:textarea path="thoughts"/>
			<form:errors path="thoughts" />
  		</div>
  		<br />
  		<button type="submit" class="btn btn-primary">Submit</button>
  		
	</form:form>
</body>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>