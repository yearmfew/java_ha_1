<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    


<jsp:include page = "shared/header.jsp" />

<!--   DAS WAR UNSER KONTONAME. HABE GENOMMEN VON BEISPIELLÖSUNG DA DRUNTER DIE GET METHODE
<form action="FormKonto" method="POST">
  <div class="form-inputs"> 
    <div>
    	<span>
    	${ nutzername }
    	</span>
    	<br/>
      <label for="kontoName" class="form-label">Konto Name</label>
      <input type="text" required class="form-control" name="kontoName" id="kontoName">
    </div>      
  </div>
 
  <div class="form-buttons"> 
    <button type="submit" class="btn btn-primary">Konto Erstellen</button>
  </div> 
  
</form>

-->







<h1 style="font-size:13px">Geben Sie dem Konto einen Namen, um eine neues zu erstellen:</h1>

<form method="GET" action="FormKonto">
		Kontoname: <input type="text" name="konto"> <br /> <input type="submit" value="Erstellen">
	</form>
	
<!-- ENDE beispiellösung -->



  
<h1 style="font-size:13px">Für welches Konto soll die Datei hochgeladen werden?</h1>

<form method="GET" action="FormKonto">
		KontoUpload <input type="text" name="KontoUpload"> <br /> <input type="submit" value="Upload">
	</form>
	




<h1 style="font-size:13px">Für welches Konto sollen ihnen Transaktionen angezeigt werden?</h1>
<!--  hier dropdown  -->
<div class="form-inputs"> 
    <div>
      <label for="kontoUpload" class="form-label">KontoAnzeigen</label>
      <input type="text" required class="form-control" name="kontoanzeigen" id="kontoUpload">
    </div>      
  </div>







	
<form method="POST" action="FormKonto" class="formFile" enctype="multipart/form-data">
        <input type="file" class="form-control mb-2"  id="file" name="datei" accept=".csv"> 
        <button class="btn btn-outline-secondary" type="submit"  id="file">Datei Hochladen</button>        
</form>

<form method="Post" action="FormLogout">
    <button class="btn btn-outline-secondary" type="submit"  id="logout">Logout</button> 
</form>

<!--  aus Beispiellösung -->

	<c:if test="${ eintraege != null }">
		<br />
		<div class="table-responsive-sm">
			<div class="card">
				<div class="card-body">Auftragskonto: ${ eintraege[0].getAuftragskonto() }</div>
			</div>

			<table class="table table-bordered table-hover table-sm table-striped">
				<tr>
					<th scope="col">Buchungstext</th>
					<th scope="col">Verwendungszweck</th>
					<th scope="col">Beguenstigter</th>
					<th scope="col">Betrag</th>
				</tr>
				<c:forEach var="eintraege" items="${ sessionScope.eintraege }">
					<tr>

						<td scope="row">${ eintraege.getBuchungstext() }</td>
						<td>${ eintraege.getVerwendungszweck() }</td>
						<td>${ eintraege.getBeguenstigter() }</td>
						<td>${ eintraege.getBetrag() }€</td>
					</tr>
				</c:forEach>
			</table>
			Ihr aktueller Kontostand: ${ kontostand } EUR
		</div>
	</c:if>


<!--  Ende Beispiellösung -->

<jsp:include page = "shared/footer.jsp" />
