<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.springframework.security.core.userdetails.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
<h1>status  : ${status} <h1>
<h2>Reason  : ${reason} </h2>
</body>
</html>