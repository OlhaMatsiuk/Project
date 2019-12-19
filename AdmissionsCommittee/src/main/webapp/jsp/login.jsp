<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<title>LogIn</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="login.css">

<script type="text/javascript">
	$(document).ready(function() {
		var selItem = localStorage.getItem("locales");
		$('#locales').val(selItem ? selItem : 'en');
		$("#locales").change(function() {
			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
			}
		});
	});
</script>

</head>

<body>

	<div class="container">

		<form method="POST" action="${contextPath}/login" class="form-signin">
			<h2 class="form-heading"><spring:message code='login.login'/></h2>

			<div
				class="form-group ${error != null ? 'has-error' : ''} center inp-center">
				<span>${message}</span> <input name="email" type="text" id="login"
					class="fadeIn second form-control" placeholder="<spring:message code='login.email'/>" /> 
					<input
					name="password" type="password" id="password"
					class="fadeIn third form-control" placeholder="<spring:message code='login.password'/>" /> 
					<span>${error}</span>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<button class="btn btn-lg btn-primary btn-block fadeIn fourth"
					type="submit"><spring:message code='login.login'/></button>
			</div>

			<div id="formFooter" class = "footerWidth">
				<a href="${contextPath}/registration" class="underlineHover"><spring:message code='login.create_account'/></a>
				
 <br> 
				 
				 <a href="/rating" class="underlineHover"><spring:message code='login.rating'/></a>
			</div>
			
			
			<div id="formFooter" class = "footerWidth lang">
				<fieldset>
					<label class= "underlineHover"><spring:message code="login.choose_language"/></label> <select
						id="locales">
						<option value="en"><spring:message code='login.english'/></option>
						<option value="ua"><spring:message code='login.ukrainian'/></option>

					</select>
				</fieldset>
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