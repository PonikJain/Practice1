<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
<link href="resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
<script type="text/javascript" src="resources/assets/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="resources/assets/js/bootstrap.min.js"></script>


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
						<a class="btn btn-danger btn-rounded" data-toggle="modal" href="#myModal">Sign up!</a>
						</sec:authorize></li>
				</ul>
			</div>
		</div>
	</nav>





	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="max-width: 900px;">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form>
  <div class="input-prepend">
    <span class="add-on"><i class="icon-envelope"></i></span>
    <input class="span2" type="text" placeholder="Email address">
  </div>
  <div class="input-prepend">
    <span class="add-on"><i class="icon-key"></i></span>
    <input class="span2" type="password" placeholder="Password">
  </div>
</form>
				 <!--  <form action="/homepage" method="post">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<div class="modal-body mx-1">
								<div class="md-form mb-1">
									<i class="fa fa-envelope prefix grey-text"></i>
									 <input type="email" id="defaultForm-email" class="form-control validate">
									<label data-error="wrong" data-success="right" for="defaultForm-email">Your email</label>
								</div>
								
								<div class="md-form mb-1">
									<i class="fa fa-lock prefix grey-text"></i>
									<input type="password" id="defaultForm-pass" class="form-control validate">
									<label data-error="wrong" data-success="right" for="defaultForm-pass">Your password</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="modal-body mx-1">
								<div class="md-form mb-1">
									<i class="fa fa-envelope prefix grey-text"></i>
									 <input type="email" id="birthday" class="form-control validate">
									<label data-error="wrong" data-success="right" for="birthday">Your email</label>
								</div>

								<div class="md-form mb-1">
									<i class="fa fa-lock prefix grey-text"></i>
									<input type="password" id="fatherName" class="form-control validate">
									<label data-error="wrong" data-success="right" for="fatherName">Your password</label>
								</div>
							</div>
						</div>
				
				</div>
				<div class="modal-footer d-flex justify-content-center">
					<button class="btn btn-default">Login</button>
				</div>
			</div>
			</form> -->
		</div>
	</div>
	</div>


</body>


</html>