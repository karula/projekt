<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				<input type="hidden" name="id" value = "${vkploigul.id}">
				Vahtkond:
				<select name ="drp_vahtkond">
				<c:forEach var="vkond" items="${vkondlist}">
					List
					<option value="${vkond.id}"<c:if test="${vkond.id == vkploigul.vahtkond_id.id}">selected</c:if>>${vkond.nimetus}</option>
				</c:forEach>
				</select></br>			
				Piirilõik:
				<select name ="drp_piiriloik">
				<c:forEach var="ploik" items="${ploiklist}">
					List
					<option value="${ploik.id}"<c:if test="${ploik.id == vkploigul.piiriloik_id.id}">selected</c:if>>${ploik.nimetus}</option>
				</c:forEach>
				</select> <br />
				<label>Alates</label>
				<input type="text" name="alates" value = "${vkploigul.alates}" /><br />
				<label>Kuni</label>
				<input type="text" name="kuni" value = "${vkploigul.kuni}"/> <br />
				<form:errors path="kommentaar"/><br />
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelvahtkondpiiriloigul">Katkesta</button>
				<button type="submit" formaction="deletevahtkondpiiriloigul">Kustuta</button>
			</form:form>
			
</pr:main>
