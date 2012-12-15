<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pr:main>
	<h2>Vahtkonnad:</h2>
	<c:forEach items="${vklist}" var="vahtkond">
			Vahtkond: <c:out value="${vahtkond.nimetus}"></c:out><a href="vahtkond?id=<c:out value="${vahtkond.id}"></c:out>">VAATA</a><br/>
    </c:forEach>
	Sisesta uus: <a href="addvahtkond">LISA</a>
</pr:main>
