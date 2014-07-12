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

	<h1>POST ACTUAL</h1>
	
	<!-- Para evitar un ataque XSS -->
	<c:out value="${nombre}" />
	
	<p>Id: ${var.id}</p>
	<h2><spring:message code="post.editar.titulo"/>: ${var.titulo}</h2>
	<p><spring:message code="post.editar.contenido"/>: ${var.contenido}</p>
	<p><spring:message code="post.editar.titulo"/>: ${var.fechaCreacion}</p>
	
	<h2><spring:message code="post.post.fecha"/>:</h2>
	<c:forEach var="comentario" items="${pos.comentarios}">
		<h4><c:out value="${comentario.comentarista}"></c:out></h4>
		<p><c:out value="${comentario.comentario}"></c:out></p>
	</c:forEach>

</body>
</html>