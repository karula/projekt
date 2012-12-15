<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pr:main>
	<h2>Piiriloigud:</h2>
	<c:forEach items="${plist}" var="piiriloik">
			Piirilõik: <c:out value="${piiriloik.nimetus}"></c:out><a href="piiriloik?id=<c:out value="${piiriloik.id}"></c:out>">VAATA</a><br/>
    </c:forEach>
	Sisesta uus: <a href="addpiiriloik">LISA</a>
</pr:main>
