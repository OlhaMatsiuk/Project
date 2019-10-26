<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>LogIn</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<div class="container">
	
	  <h6>${pass}</h6>
	
	<form method="POST" action="${contextPath}/home" class="form-signin">
		<h2 class="form-heading">Login</h2>
		<div class="form-group ">

			<input name="email" type="text" class="form-control"
				placeholder="Email" /> <input name="password" type="text"
				class="form-control" placeholder="Id" />



			<button class="btn btn-lg btn-primary btn-block" type="submit">LogIn</button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</div>
	</form>

	<h4 class="text-center">
		<a href="${contextPath}/registration">Registration</a>
	</h4>

</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>