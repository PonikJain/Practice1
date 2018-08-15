<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
<link href="resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
<script type="text/javascript" src="resources/assets/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="resources/assets/js/bootstrap.min.js"></script>

<script>
$(document).ready(function() {

	  if(window.location.href.indexOf('#myModal') != -1) {
	    $('#myModal').modal('show');
	  }
	  
	  $('#signUpBtn').click(function(){
		  $('#myModal').modal('show');
	  });

	});
	
	
</script>

</head>


<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">College Portal</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Services</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
					<li class="nav-item"><sec:authorize access="isAuthenticated()">
							<a class="nav-link" href="<c:url value="/logout" />">Logout</a>
						</sec:authorize> <sec:authorize access="!isAuthenticated()">
						<a class="btn btn-danger btn-rounded"  id="signUpBtn" href="#myModal">Sign up!</a>
					
						</sec:authorize></li>
				</ul>
			</div>
		</div>
	</nav>





	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="max-width: 900px;">
			<div class="modal-content">
				<div class="modal-header text-center">
					<c:if test="${not empty myForm.signInProvider}">
       					 <h2 style="color:blue;">Signup with ${myForm.signInProvider}</h2>
    				</c:if>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

		 		<c:url var="regUrl" value="/register" />
        		<%-- <form role="form" id="Rform"  action="${regUrl}" method="post"> --%>
        		<form:form modelAttribute="myForm" method="POST">
        			<form:hidden path="id" />
        			<form:hidden path="signInProvider" />  
				<div class="container">
					<div class="modal-body mx-1">
						<div class="row">

							<div class="col-md-6">
								<div class="md-form mb-1">
									<!--  <input type="text" name="firstName" id="defaultForm-firstame" class="form-control"> -->
									 <form:input path="firstName" />
									<label data-error="wrong" data-success="right" for="defaultForm-firstame">FirstName</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="lastName" />
									 <!-- <input type="text" name="lastName" id="defaultForm-lastName" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-lastName">LastName</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="phoneNumber" />
									 <!-- <input type="text" name="phoneNumber" id="defaultForm-phoneNumber" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-phoneNumber">PhoneNumber</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="dob" />
									<!-- <input type="date" name="dob" id="defaultForm-dob" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-dob">DOB</label>
								</div>
							</div>
						
							<div class="col-md-6">
								<div class="md-form mb-1">
								<form:input path="email" />
									<!-- <input type="email" name="email" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-email">Email</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="state" />
									<!--  <input type="text" name ="state" id="defaultForm-state" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-state">State</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="password" />
									<!-- <input type="password" id="defaultForm-pass" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-pass">Password</label>
								</div>
								
								<div class="md-form mb-1">
								<form:input path="cpassword" />
									 <!-- <input type="text" id="defaultForm-cpass" name="password" class="form-control"> -->
									<label data-error="wrong" data-success="right" for="defaultForm-cpass">Confirm Password</label>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
							</div>
						</div>
					</div>
				<div class="modal-footer d-flex justify-content-center">
					<button class="btn btn-success" type="submit">Register</button>
				</div>
			</div>
			</form:form>
   			 <div class="error-message">${errorMessage}</div>
<%-- 			</form>  --%>
			</div>
		</div>
	</div>


</body>


</html>