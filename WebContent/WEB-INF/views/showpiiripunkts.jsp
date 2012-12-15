<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pr:main>
	<h2>Piiripunktid:</h2>
	<c:forEach items="${plist}" var="piiripunkt">
			Piiripunkt: <c:out value="${piiripunkt.nimetus}"></c:out><a href="piiripunktForm?id=<c:out value="${piiripunkt.id}"></c:out>">VAATA</a><br/>
    </c:forEach>
	Sisesta uus: <a href="addpiiripunkt">LISA</a>
</pr:main>
