<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!-- Si viene la bandera actualizado pintamos un mensaje -->
	<c:if test="${actualizado}">
		<h3>DATOS GUARDADOS!!</h3>
	</c:if>
	
	<c:url var="urlLogaut" value="/logaut"/>
	<a href="${urlLogaut}">cerrar session</a>

	<!-- Damos una ur y en tiempo de ejecucion averigua cual es el contextRoot  y lo guarda en la var urlGuardar-->
	<c:url var="urlGuardar" value="/usuario/guardar" />

	<form:form action="${urlGuardar}" method="post" commandName="usuario">
		<form:input type="hidden" path="id" />
		<div>
			<label><spring:message code="usuario.editar.nombre"/>:</label> <form:input type="text" path="nombre"/>
		</div>
		<div>
			<form:errors path="nombre"></form:errors>
		</div>
		<div>
			<label><spring:message code="usuario.usuario.password"/>:</label> <form:input type="text" path="password"/>
		</div>
		<div>
			<form:errors path="password"></form:errors>
		</div>
		<div>
			<label><spring:message code="usuario.usuario.email"/>:</label>
			<form:textarea path="email"/>
		</div>
		<div>
			<form:errors path="email"></form:errors>
		</div>
		<input type="submit" value="Enviar">

	</form:form>

</body>
</html>