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
			<a href="/home" class="w3-bar-item w3-button">Home</a> <a
				href="/information" class="w3-bar-item w3-button">Add
				information</a> <a href="/rating" class="w3-bar-item w3-button">Rating</a> <a
				href="/faculty" class="w3-bar-item w3-button">Create faculty</a> <a
				href="/profession" class="w3-bar-item w3-button">Create
				profession</a> <a href="/apply" class="w3-bar-item w3-button">Apply</a>
			<a href="/newApp" class="w3-bar-item w3-button">New App</a>
			<a href="/status"class="w3-bar-item w3-button">My status</a>
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
						<a href="/end">END</a>
					</h2>
				</c:if>
				<!-- ------------------------------------------------------------------------------------------ -->


				<c:forEach items="${rating}" var="rating">
					<div style="with: 10%; height: 10%">


						<img alt="image" src="data:image/jpg;base64,${rating.user.encodedImage}" style="width: 10%">
						<h5>First name: ${rating.user.firstName}</h5>
						<h5>Last name: ${rating.user.lastName}</h5>
			 			<h5>Profession: ${rating.profession.name}</h5>
						
						
						<form id="logoutForm" method="POST"
								action="${contextPath}/allowApply">
								
								<input type="hidden" name="userID" value="${rating.user.id}">
								<input type="hidden" name="profID" value="${rating.profession.id}">
								<input type="submit" value="Allow!">
								
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form>
						
						<form id="logoutForm" method="POST"
								action="${contextPath}/notAllowApply">
								
								<input type="hidden" name="userID" value="${rating.user.id}">
								<input type="hidden" name="profID" value="${rating.profession.id}">
								<input type="submit" value="Not Allow!">
								
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form>
						
						
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