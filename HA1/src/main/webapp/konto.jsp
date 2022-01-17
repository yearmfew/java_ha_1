<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="shared/header.jsp" />



<h1 style="font-size: 13px">Geben Sie dem Konto einen Namen, um
	eine neues zu erstellen:</h1>

<form method="GET" action="FormKonto">
	Kontoname: <input type="text" name="konto"> <br /> <input
		type="submit" value="Erstellen">
</form>


<h1 style="font-size: 13px">Für welches Konto soll die Datei
	hochgeladen werden?</h1>


<form method="POST" action="FormKonto" class="formFile"
	enctype="multipart/form-data">

	
	<div class="form-group">
		<label for="selectKonto">Example select</label> 
		<select
			class="form-control" id="selectKonto" name="kontoId" >
			<c:forEach var="konto" items="${ sessionScope.konten }">
				<option value="${konto.getKontoId()}" >${konto.getName()}</option>
			</c:forEach>
		</select>
	</div>

	
	<input type="file" class="form-control mb-2" id="file" name="datei"
		accept=".csv">
	<button class="btn btn-outline-secondary" type="submit" id="file">Datei
		Hochladen</button>
</form>


<form method="Post" action="FormLogout">
	<button class="btn btn-outline-secondary" type="submit" id="logout">Logout</button>
</form>

<c:if test="${ eintraege != null }">
	<br />
	<div class="table-responsive-sm">
		<div class="card">
			<div class="card-body">Kontoname: ${ kontoname}</div>
		</div>

		<table class="table table-bordered table-hover table-sm table-striped">
			<tr>

				<th scope="col">Verwendungszweck</th>
				<th scope="col">Betrag</th>
			</tr>
			<c:forEach var="eintraege" items="${ sessionScope.eintraege }">
				<tr>
					<td>${ eintraege.getVerwendungszweck() }</td>
					<td>${ eintraege.getBetrag() }€</td>
				</tr>
			</c:forEach>
		</table>
		Ihr aktueller Kontostand: ${ kontostand } EUR
	</div>
</c:if>

<a href="kategorien.jsp" class"btn btn-success">Kategorien</a>

<jsp:include page="shared/footer.jsp" />
