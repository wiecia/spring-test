<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags">
	
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
	
	<jsp:directive.page contentType="text/html;charset=UTF-8" />
	<jsp:directive.page pageEncoding="UTF-8" />
	
<html>
<head></head>
<body>
	<h1>It Works!</h1>
	
	<ul>
	<c:forEach items="${people}" var="p">
		<li><c:out value="${p.name}, ${p.age}, ${p.email}"/></li>
	</c:forEach>
	</ul>
</body>
</html>

</jsp:root>