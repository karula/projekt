<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				Vahtkond:
				<select name ="drp_vahtkond">
				<c:forEach var="vkond" items="${vkondlist}">
					<option value="${vkond.id}">${vkond.nimetus}</option>
				</c:forEach>
				</select></br>
				Piirilõik:
				<select name ="drp_piiriloik">
				<c:forEach var="ploik" items="${ploiklist}">
					List
					<option value="${ploik.id}">${ploik.nimetus}</option>
				</c:forEach>
				</select> <br />
				<label>Alates</label>
				  <input type="text" name="alates" value = ""/><br/>
				 <label>Kuni</label>
				  <input type="text" name="kuni" value = ""/><br/>
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelvahtkondpiiriloigul">Katkesta</button>
				<button type="submit" formaction="deletevahtkondpiiriloigul">Kustuta</button>
			</form:form>
</pr:main>
