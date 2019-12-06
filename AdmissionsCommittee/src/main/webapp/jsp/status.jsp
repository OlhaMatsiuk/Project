<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Periodicals</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<body>
	<div class="container">

	<!-- Sidebar -->
		<div class="container">


		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
			<h3 class="w3-bar-item">|||</h3>
			
			<a href="/home" class="w3-bar-item w3-button">Home</a>
			
			<security:authorize access="hasAuthority('USER')">
			<a href="/information" class="w3-bar-item w3-button">Add information</a>
			</security:authorize>
			
			
			 <a href="/rating" class="w3-bar-item w3-button">Rating</a>
			 
			 <security:authorize access="hasAuthority('ADMIN')">
			 <a href="/faculty"	class="w3-bar-item w3-button">Create faculty</a> 
			 </security:authorize>
			 
			 <security:authorize access="hasAuthority('ADMIN')">
			 <a href="/profession" class="w3-bar-item w3-button">Create profession</a> 
			 </security:authorize>
			 
			 <security:authorize access="hasAuthority('USER')">
			 <a href="/apply"class="w3-bar-item w3-button">Apply </a>
			 </security:authorize>
			 
			 <security:authorize access="hasAuthority('ADMIN')">
			  <a href="/newApp"class="w3-bar-item w3-button">New App</a>
			  </security:authorize>
			  
			  <security:authorize access="hasAuthority('USER')">
			  <a href="/status"class="w3-bar-item w3-button">My status</a>
			  </security:authorize>
			  
		</div>


		<!-- Page Content -->
		
		<div style="margin-left: 10%">
			<div class="w3-container w3-teal">
				<h1>Rating</h1>
			</div>
			<div class="w3-container">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<h2>
						Welcome ${pageContext.request.userPrincipal.name} | <a
							onclick="document.forms['logoutForm'].submit()">LogOut</a>
					</h2>
				</c:if>
				
<!-- 
-------------------------------------------- -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name of Profession</th>
							<th>Status</th>
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
</body>
</html>
				
				
