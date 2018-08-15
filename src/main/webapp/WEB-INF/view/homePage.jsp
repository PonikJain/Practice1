<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="javax.servlet.jsp.PageContext" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>College Portal - Home</title>

<style type="text/css">
#main_container{
	margin-top:50px;
}

</style>

<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
<link href="resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
<script type="text/javascript" src="resources/assets/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="resources/assets/js/bootstrap.min.js"></script>

</head>
<body>

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
						</sec:authorize> <
				</ul>
			</div>
		</div>
	</nav>
	
<div id="main_container">
<h2>Welcome : ${pageContext.request.userPrincipal} 
                 |</h2>  
</div>            
<%@ include file="footer.jsp" %>   
</body>
</html>