<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<pr:main>
	<c:choose>
		<c:when test="${not empty guard.name and not empty guard.age}">
			${guard.name}
			${guard.age}
		</c:when>
		<c:otherwise>
			<form method="POST">
				<label>Nimi</label>
				<input type="text" name="name" /> <br />
				<label>Vanus</label>
				<input type="text" name="age" /> <br />
				<input type="submit" value="Lisa" />
			</form>
		</c:otherwise>
	</c:choose>
</pr:main>
