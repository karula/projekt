<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<pr:main>
			<form:form method="POST">
				<label>Kood</label>
				<input type="text" name="kood" value = "" /><br />
				<label>Nimetus</label>
				<input type="text" name="nimetus" value = ""/> <br />
				<label>GPS Koordinaadid</label>
				<input type="text" name="gpskoordinaadid" value = ""/> <br />
				<label>Kõrgus merepinnast</label>
				<input type="text" name="korgusmerepinnast" value = ""/> <br />
				<label>Kommentaar</label>
				  <input type="text" name="Kommentaar" value = ""/>
				<form:errors path="kommentaar"/><br />
				<input type="submit" value="Salvesta" />
				<button type="submit" formaction="cancelpiiriloik">Katkesta</button>
				<button type="submit" formaction="deletepiiriloik">Kustuta</button>
			</form:form>
</pr:main>
