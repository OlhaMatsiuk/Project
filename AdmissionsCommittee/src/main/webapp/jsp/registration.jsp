<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Create</title>

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

	<div class="container2">


		<form:form method="POST" modelAttribute="userForm"
			class="form-signin2">
			<h2 class="form-signin-heading"><spring:message code='login.create_account'/></h2>

			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''} marDiv">
				<spring:message code="login.firstName" var="firstName"/> 
					<form:input type="text" path="firstName" class="form-control"
						placeholder="${firstName}" autofocus="true"></form:input>
					<form:errors path="firstName"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''} marDiv">
				<spring:message code="login.lastName" var="lastName"/> 
					<form:input type="text" path="lastName"
						class="form-control fadeIn second" placeholder="${lastName}"
						autofocus="true"></form:input>
					<form:errors path="lastName"></form:errors>
				</div>
			</spring:bind>


			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''} marDiv">
				<spring:message code="login.email" var="email"/> 
					<form:input type="text" path="email"
						class="form-control  fadeIn second" placeholder="${email}"
						autofocus="true"></form:input>
					<form:errors path="email"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''} marDiv">
				<spring:message code="login.password" var="password"/> 
					<form:input type="password" path="password"
						class="form-control  fadeIn second"  placeholder="${password}" ></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''} marDiv">
				<spring:message code="login.password_confirm" var="password_confirm"/> 
					<form:input type="password" path="passwordConfirm"
						class="form-control  fadeIn second"
						placeholder="${password_confirm}"></form:input>
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code='login.create_account'/></button>
		</form:form>

		<div id="formFooter">
			<a href="${contextPath}/login" class="underlineHover"><spring:message code='login.login'/></a> <br>
			<a href="/rating" class="underlineHover"><spring:message code='login.rating'/></a>
		</div>
		
			<div id="formFooter" class = "lang">
				<fieldset>
					<label class= "underlineHover"><spring:message code="login.choose_language"/></label> <select
						id="locales">
						<option value="en"><spring:message code='login.english'/></option>
						<option value="ua"><spring:message code='login.ukrainian'/></option>

					</select>
				</fieldset>
			</div>

	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>