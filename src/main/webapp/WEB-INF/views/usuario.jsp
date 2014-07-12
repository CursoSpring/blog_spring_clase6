<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>USUARIO...!</h1>

<p>Id: <c:out value="${usuario_.id}"></c:out></p>
<p><spring:message code="usuario.usuario.usuario"/>: <c:out value="${usuario_.nombre}"></c:out></p>
<p><spring:message code="usuario.usuario.password"/>: <c:out value="${usuario_.password}"></c:out></p>
<p><spring:message code="usuario.usuario.email"/>: <c:out value="${usuario_.email}"></c:out></p>

</body>
</html>