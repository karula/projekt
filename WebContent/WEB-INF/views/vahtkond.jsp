<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				<input type="hidden" name="id" value = "${vahtkond.id}">
				<label>Kood</label>
				<input type="text" name="kood" value = "${vahtkond.kood}" /><br />
				<label>Nimetus</label>
				<input type="text" name="nimetus" value = "${vahtkond.nimetus}"/> <br />
				Piiripunkt:
				<select name ="drp_piiripunkt">
				<c:forEach var="punkt" items="${ppunktlist}">
					List
					<option value="${punkt.id}"<c:if test="${punkt.id == vahtkond.piiripunkt.id}">selected</c:if>>${punkt.nimetus}</option>
				</c:forEach>
				</select></br>
				Väeosa:
				<select name ="drp_vaeosa">
				<c:forEach var="vosa" items="${vosalist}">
					List
					<option value="${vosa.id}"<c:if test="${vosa.id == vahtkond.vaeosa.id}">selected</c:if>>${vosa.nimetus}</option>
				</c:forEach>
				</select> <br />
				<label>Kommentaar</label>
				 <input type="text" name="kommentaar" value = "${vahtkond.kommentaar}"/>
				<form:errors path="kommentaar"/><br />
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelvahtkond">Katkesta</button>
				<button type="submit" formaction="deletevahtkond">Kustuta</button>
			</form:form>
			
</pr:main>
