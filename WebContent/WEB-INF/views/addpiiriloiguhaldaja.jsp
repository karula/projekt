<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				Piirilõik:
				<select name ="drp_piiriloik">
				<c:forEach var="ploik" items="${ploiklist}">
					List
					<option value="${ploik.id}">${ploik.nimetus}</option>
				</c:forEach>
				</select> <br />
				Piiripunkt:
				<select name ="drp_piiripunkt">
				<c:forEach var="ppunkt" items="${ppunktlist}">
					<option value="${ppunkt.id}">${ppunkt.nimetus}</option>
				</c:forEach>
				</select><br/>		
				<label>Alates</label>
				  <input type="text" name="alates" value = ""/><br/>
				 <label>Kuni</label>
				  <input type="text" name="kuni" value = ""/><br/>
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelpiiriloiguhaldaja">Katkesta</button>
				<button type="submit" formaction="deletepiiriloiguhaldaja">Kustuta</button>
			</form:form>
</pr:main>
