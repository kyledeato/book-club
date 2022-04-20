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
<title>Login Register</title>
</head>
<body>
	<h1>Welcome!</h1>
	<h2>Book Club</h2>
	<div class="container">
		<form:form action="/register" method="post" modelAttribute="newUser">
			<h3>Register</h3>
			<div class="form-group">
	    		<form:label path="userName">Username: </form:label>
	    		<form:input path="userName"/>
				<form:errors path="userName" />
				
	    		<form:label path="email">Email: </form:label>
	    		<form:input path="email" />
				<form:errors path="email" />
				
	    		<form:label path="password">Password: </form:label>
	    		<form:input path="password" />
				<form:errors path="password" />
				
	    		<form:label path="confirm"> Confirm password: </form:label>
	    		<form:input path="confirm" />
				<form:errors path="confirm" />
  			</div>
  			<br />
  			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
		
	</div>
	
	<br />
	<br />
	
	<div class="container">
	<form:form action="/login" method="post" modelAttribute="newLogin">
			<h3>Login</h3>
			<div class="form-group">
				
	    		<form:label path="email">Email: </form:label>
	    		<form:input path="email" />
				<form:errors path="email" />
				
	    		<form:label path="password">Password: </form:label>
	    		<form:input path="password" />
				<form:errors path="password" />
				
  			</div>
  			<br />
  			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>