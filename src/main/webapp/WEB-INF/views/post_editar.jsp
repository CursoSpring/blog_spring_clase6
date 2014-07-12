<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- para seguridad -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div>
			<button>Destruir aplicacion</button>
		</div>
	</sec:authorize>

	<!-- Si viene la bandera actualizado pintamos un mensaje -->
	<c:if test="${actualizado}">
		<h3>DATOS GUARDADOS!!</h3>
	</c:if>
	
	<c:url var="urlLogaut" value="/logaut"/>
	<a href="${urlLogaut}">cerrar session</a>
	
	
	<!-- Damos una ur y en tiempo de ejecucion averigua cual es el contextRoot  y lo guarda en la var urlGuardar-->
	<c:url var="urlGuardar" value="/blog/guardar"/>

	<form:form action="${urlGuardar}" method="post" commandName="post">
		<form:input type="hidden" path="id" />
		<div>
			<label><spring:message code="post.editar.titulo"/>
			</label> <form:input type="text" path="titulo" />
		</div>
		
		<div>
			<form:errors path="titulo"></form:errors>
		</div>
		<div>
			<label><spring:message code="post.editar.contenido"/></label> 
			<form:textarea path="contenido"/>
		</div>
		<div>
			<form:errors path="contenido"></form:errors>
		</div>
		
		<input type="submit" value="Enviar">
		
	</form:form>


</body>
</html>