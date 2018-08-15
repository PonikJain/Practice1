<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>College Portal</title>
        <link rel="icon" href="resources/assets/images/tabIcon.png">

        <style>

            .omb_loginOr {
            	position: relative;
            	font-size: 1.5em;
            	color: #aaa;
                margin-top: 1em;
                margin-bottom: 1em;
                padding-top: 0.5em;
                padding-bottom: 0.5em;
                }

            .omb_loginOr .omb_hrOr {
                background-color: #cdcdcd;
                height: 1px;
                margin-top: 0px !important;
                margin-bottom: 0px !important;
            }

            hr {
                width: 218%;
            }

            .omb_loginOr .omb_spanOr {
                display: block;
                position: absolute;
                left: 50%;
                top: -0.6em;
                margin-left: 2.4m;
                background-color: #f8f8f8;
                width: 3em;
                text-align: center;
            }			

        </style>
        
        
        
<script>
$(document).ready(function() {

	  if(window.location.href.indexOf('#myModal') != -1) {
	    $('#myModal').modal('show');
	  }

	});
</script>
    </head>
<body>

<%@ include file="header.jsp" %> 

<div class="container">
     <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <div class="login-panel panel panel-default">

                <div class="panel-heading">
                    <h2>${regMessage}</h2>
                    <h3 class="panel-title">Please Sign In</h3>
                </div>

            <div class="panel-body">
                 <c:url var="loginUrl" value="/login" />
                    <form role="form" action="${loginUrl}" method="post">
                    <c:if test="${not empty error}"><div>${error}</div></c:if>
					<c:if test="${not empty message}"><div>${message}</div></c:if>
                    <fieldset>
                                
						<div class="input-group mb-3">
  						    <div class="input-group-prepend">
   						       <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
  						    </div>
 							<input type="text" class="form-control" placeholder="Username" aria-label="Username" name="username"aria-describedby="basic-addon1">
						</div>
					
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
   								<span class="input-group-text" id="basic-addon1"><i class="fa fa-lock"></i></span>
  							</div>
 							<input class="form-control" placeholder="Password" name="password" type="password" aria-label="Password" aria-describedby="basic-addon2">
						</div>
								
                        <div class="checkbox">
                            <label>
                                <input name="remember-me" type="checkbox" value="Remember Me">Remember Me please
                            </label>
                        </div>
                        
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                
                        <div class="form-actions">
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
                        </div>
                                
                    </fieldset>
                        </form>
                        
                    <div class="row omb_row-sm-offset-3 omb_loginOr">
						<div class="col-xs-12 col-sm-6">
						<hr class="omb_hrOr">
						<span class="omb_spanOr">or</span>
						</div>
					</div>
                    <form action="<c:url value="/auth/google" />" method="get">
                    <input type="hidden" name="scope" value="profile email" />
                    <div class="btn-group">
						<a class='btn btn-danger disabled'><i class="fa fa-google-plus" style="width:16px; height:20px"></i></a>
						<button type="submit" class='btn btn-danger'>Sign in with Google</button>
						<!-- <a href="${pageContext.request.contextPath}/auth/google" class='btn btn-danger'> </a> -->
					</div>
					</form>
					
					<div class="btn-group">
						<a class='btn btn-primary disabled'><i class="fa fa-facebook" style="width:16px; height:20px"></i></a>
						<button type="submit" class='btn btn-primary'>
						<a href="${pageContext.request.contextPath}/auth/facebook"></a>Sign in with Facebook</button>
					</div>
					
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %> 
</body>
</html>