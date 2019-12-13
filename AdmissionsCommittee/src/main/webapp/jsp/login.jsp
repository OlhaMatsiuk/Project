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

<title>Log in with your account</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<link rel="stylesheet" href="login.css">

</head>

<body>

	<div class="container">

		<form method="POST" action="${contextPath}/login" class="form-signin">
			<h2 class="form-heading">Log in</h2>

			<div
				class="form-group ${error != null ? 'has-error' : ''} center inp-center">
				<span>${message}</span> <input name="email" type="text" id="login"
					class="fadeIn second form-control" placeholder="Email" /> <input
					name="password" type="password" id="password"
					class="fadeIn third form-control" placeholder="Password" /> <span>${error}</span>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<button class="btn btn-lg btn-primary btn-block fadeIn fourth"
					type="submit">Log In</button>
			</div>

			<div id="formFooter" class = "footerWidth">
				<a href="${contextPath}/registration" class="underlineHover">Create
					an account</a> <br> <a href="/rating" class="underlineHover">View
					Rating</a>
			</div>


		</form>

	</div>
	<!-- /container -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>