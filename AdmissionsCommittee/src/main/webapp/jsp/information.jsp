<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Information</title>
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
				<!-- --------------------------------------------------------------------------------- -->

				<c:set var="val1" value="${certifEr}" />
				<c:set var="val2" value="${mathEr}" />
				<c:set var="val3" value="${lanEr}" />
				<c:set var="val4" value="${hisEr}" />
				<c:set var="val5" value="${image}" />

				<c:if test="${val1 == null}">

					<div>
						<form:form method="POST"
							action="${contextPath}/addInformationCertifiacate"
							modelAttribute="user">

							<table>

								<tr>
									<td><form:label path="evaluationOfCertificate">Evaluation of Certificate</form:label></td>
									<td><form:input path="evaluationOfCertificate" />
									<td><form:input type="hidden" path="email"
											value="${pageContext.request.userPrincipal.name}" />
								</tr>

								<tr>
									<td><input type="submit" value="Submit" /></td>
								</tr>
							</table>


							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form:form>

					</div>
				</c:if>

				<c:if test="${val1 != null}">
					<h5>Certificate: ${val1}</h5>
				</c:if>
				<!-- --------------------------------------------------------------------------------- -->
				<div>
					<!-- --------------------------------------------------------------------------------- -->

					<c:if test="${val2 == null}">
						<form:form method="POST" action="${contextPath}/addEvaluations"
							modelAttribute="evaluation">

							<table>

								<tr>
									<td><form:input type="hidden" path="nameSubject"
											value="Math" />
								</tr>
								<tr>
									<td><form:label path="evaluation">Evaluation with math</form:label></td>
									<td><form:input path="evaluation" />
								</tr>

								<tr>
									<td><input type="submit" value="Submit" /></td>
								</tr>
							</table>


							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form:form>

					</c:if>

					<c:if test="${val2 != null}">
						<h5>Math: ${val2}</h5>
					</c:if>

					<!-- --------------------------------------------------------------------------------- -->


					<c:if test="${val3 == null}">
						<form:form method="POST" action="${contextPath}/addEvaluations"
							modelAttribute="evaluation">

							<table>

								<tr>
									<td><form:input type="hidden" path="nameSubject"
											value="Language" />
								</tr>
								<tr>
									<td><form:label path="evaluation">Evaluation with language</form:label></td>
									<td><form:input path="evaluation" />
								</tr>

								<tr>

									<td><input type="submit" value="Submit" /></td>
								</tr>
							</table>


							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form:form>

					</c:if>

					<c:if test="${val3 != null}">
						<h5>Language: ${val3}</h5>
					</c:if>

					<!-- --------------------------------------------------------------------------------- -->


					<c:if test="${val4 == null}">
						<form:form method="POST" action="${contextPath}/addEvaluations"
							modelAttribute="evaluation">

							<table>

								<tr>
									<td><form:input type="hidden" path="nameSubject"
											value="History" />
								</tr>
								<tr>
									<td><form:label path="evaluation">Evaluation with history</form:label></td>
									<td><form:input path="evaluation" />
								</tr>

								<tr>

									<td><input type="submit" value="Submit" /></td>
								</tr>
							</table>


							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form:form>
					</c:if>

					<c:if test="${val4 != null}">
						<h5>History: ${val4}</h5>
					</c:if>

					<!-- --------------------------------------------------------------------------------- -->
					<br>
				</div>

				
				
				<c:if test="${val5 == null}">
				<div>

					<form:form method="POST" action="${contextPath}/addImage" enctype="multipart/form-data">
						<table>
							<tr>
								<td><input type="file" name="image" /></td>
							</tr>

							<tr>
								<td><input type="submit" value="Add" /></td>
							</tr>
						</table>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form>
				</div>
				</c:if>
				
				<c:if test="${val5 != null}">
				<div>
					
					<h5> ${val5} </h5>
					
					<form:form method="POST" action="${contextPath}/addImage" enctype="multipart/form-data">
						<table>
							<tr>
								<td><input type="file" name="image" /></td>
							</tr>

							<tr>
								<td><input type="submit" value="Update" /></td>
							</tr>
						</table>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form>
				</div>
				</c:if>
				
				
				
			</div>

		</div>


	</div>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>