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
</head>
<body>
<%@ include file="header.jsp" %> 
<div id="main_container">
<h2>Welcome : ${pageContext.request.userPrincipal} 
                 |</h2>  
</div>            
<%@ include file="footer.jsp" %>   
</body>
</html>