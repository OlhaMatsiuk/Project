<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Status</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
<body>

	<div class="container">
	
		<!-- ----------DEF----------- -->


		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block"
			style="width: 15%; position: absolute; left: 0; top: 0;">
			<h3 class="w3-bar-item">
				<spring:message code='nav.name' />
			</h3>

			<a href="/home" class="w3-bar-item w3-button"><spring:message
					code='nav.home' /></a>

			<security:authorize access="hasAuthority('USER')">
				<a href="/information" class="w3-bar-item w3-button"><spring:message
						code='nav.add_inform' /></a>
			</security:authorize>


			<a href="/rating" class="w3-bar-item w3-button"><spring:message
					code='nav.rating' /></a>

			<security:authorize access="hasAuthority('ADMIN')">
				<a href="/faculty" class="w3-bar-item w3-button"><spring:message
						code='nav.faculty' /></a>
			</security:authorize>

			<security:authorize access="hasAuthority('ADMIN')">
				<a href="/profession" class="w3-bar-item w3-button"><spring:message
						code='nav.profession' /></a>
			</security:authorize>

			<security:authorize access="hasAuthority('USER')">
				<a href="/apply" class="w3-bar-item w3-button"><spring:message
						code='nav.apply' /></a>
			</security:authorize>

			<security:authorize access="hasAuthority('ADMIN')">
				<a href="/newApp" class="w3-bar-item w3-button"><spring:message
						code='nav.new_app' /></a>
			</security:authorize>

			<security:authorize access="hasAuthority('USER')">
				<a href="/status" class="w3-bar-item w3-button"><spring:message
						code='nav.status' /></a>
			</security:authorize>

			<div class="w3-bar-item w3-button">
				<fieldset>
					<label class="underlineHover"><spring:message
							code="login.choose_language" /></label> <select id="locales">
						<option value="en"><spring:message code='login.english' /></option>
						<option value="ua"><spring:message code='login.ukrainian' /></option>

					</select>
				</fieldset>
			</div>

			<a onclick="document.forms['logoutForm'].submit()"
				class="w3-bar-item w3-button"><spring:message code='nav.logout' /></a>

		</div>

		<!--  top -->
		<div style="margin-left: 10%">

			<div class="w3-container w3-teal"
				style="width: 85%; position: absolute; right: 0; top: 0; margin-bottom: 100px;">

				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<h2>
					<spring:message code='nav.welcome' />
					${pageContext.request.userPrincipal.name}
				</h2>

			</div>

		</div>

		<!-- ----------DEF END----------- -->


		<!-- Page Content -->
		
		<div style="position:absolute; top:10%; left:15%;">
			<div class="w3-container">
<!-- 
-------------------------------------------- -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th><spring:message code='rating.profession' /></th>
							<th><spring:message code='rating.status' /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rating" items="${rating}">
							<tr>
								<td>${rating.profession.name}</td>
								<td>${rating.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
<!-- 
-------------------------------------------- -->


			</div>

		</div>

</div>
	</div>
</body>
</html>
				
				

