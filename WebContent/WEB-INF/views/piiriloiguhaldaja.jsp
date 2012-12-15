<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				<input type="hidden" name="id" value = "${plhaldaja.id}">
				Piirilõik:
				<select name ="drp_piiriloik">
				<c:forEach var="ploik" items="${ploiklist}">
					List
					<option value="${ploik.id}"<c:if test="${ploik.id == plhaldaja.piiriloik_id.id}">selected</c:if>>${ploik.nimetus}</option>
				</c:forEach>
				</select> <br />
				Piiripunkt:
				<select name ="drp_piiripunkt">
				<c:forEach var="ppunkt" items="${ppunktlist}">
					List
					<option value="${ppunkt.id}"<c:if test="${ppunkt.id == plhaldaja.piiripunkt_id.id}">selected</c:if>>${ppunkt.nimetus}</option>
				</c:forEach>
				</select><br/>						
				<label>Alates</label>
				<input type="text" name="alates" value = "${plhaldaja.alates}" /><br />
				<label>Kuni</label>
				<input type="text" name="kuni" value = "${plhaldaja.kuni}"/> <br />
				<form:errors path="kommentaar"/><br />
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelvahtkondpiiriloigul">Katkesta</button>
				<button type="submit" formaction="deletevahtkondpiiriloigul">Kustuta</button>
			</form:form>
			
</pr:main>
