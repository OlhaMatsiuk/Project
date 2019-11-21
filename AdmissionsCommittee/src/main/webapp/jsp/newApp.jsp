<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profession</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<body>
	<div class="container">


		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
			<h3 class="w3-bar-item">||</h3>
			<a href="/home" class="w3-bar-item w3-button">Home</a> 
			<a href="/information" class="w3-bar-item w3-button">Add information</a>
			<a href="#"
				class="w3-bar-item w3-button">Rating</a> <a href="/faculty"
				class="w3-bar-item w3-button">Create faculty</a> <a
				href="/profession" class="w3-bar-item w3-button">Create
				profession</a> <a href="/apply" class="w3-bar-item w3-button">Apply</a>
				 <a href="/newApp"class="w3-bar-item w3-button">New App</a>
		</div>


		<!-- Page Content -->
		<div style="margin-left: 10%">


			<div class="w3-container">

				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<h2>
						<a onclick="document.forms['logoutForm'].submit()">LogOut</a>
					</h2>
				</c:if>
<!-- ------------------------------------------------------------------------------------------ -->
				
				
				<c:forEach items="${list}" var="list">
					 <div style="with: 10%; height: 10%";">
				 
				 		<h5>First name: ${list.firstName} </h5>
				 		<h5>Last name:  ${list.lastName} </h5>
				 		
				 		<c:forEach items="${list.professions}" var="prof">
				 			<h5>Profession: ${prof.name} </h5>
				 		</c:forEach>
					
				 		<h4> ${list.status} </h4>
				 		
				 		<h3>----------------------</h3>
				 
				 	</div> 
				</c:forEach>
				
<!-- ------------------------------------------------------------------------------------------ -->
			</div>

		</div>


	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>