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

<form method="GET" action="FormKonto">
	KontoUpload <input type="text" name="KontoUpload"> <br /> <input
		type="submit" value="Upload">
</form>


<h1 style="font-size: 13px">Für welches Konto sollen ihnen
	Transaktionen angezeigt werden?</h1>
<!--  hier dropdown  -->

<!-- Example single danger button -->
<div class="btn-group">
	<button type="button" class="btn btn-secondary dropdown-toggle"
		data-bs-toggle="dropdown" aria-expanded="false">Action</button>
	<ul class="dropdown-menu">

		<c:forEach var="konto" items="${ sessionScope.konten }">
			<li><a class="dropdown-item" href="">${konto.getName()}</a></li>

		</c:forEach>

	</ul>
</div>

<form method="POST" action="FormKonto" class="formFile"
	enctype="multipart/form-data">
	<input type="file" class="form-control mb-2" id="file" name="datei"
		accept=".csv">
	<button class="btn btn-outline-secondary" type="submit" id="file">Datei
		Hochladen</button>
</form>

<form method="Post" action="FormLogout">
	<button class="btn btn-outline-secondary" type="submit" id="logout">Logout</button>
</form>

<!--  aus Beispiellösung -->

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


<!--  Ende Beispiellösung -->

<form method="GET" action="FormKategorie">
	<button type="button" class="btn btn-success">Kategorien anlegen</button>
</form>


<jsp:include page="shared/footer.jsp" />
