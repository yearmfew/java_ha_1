<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="shared/header.jsp" />
<form action="FormKategorien" method="POST">
	<div class="mb-3">
		<label for="name" class="form-label">Name</label> 
		<input type="text" required class="form-control" name="name" id="name">
	</div>
	<div class="mb-3">
		<label for="schlagwort" class="form-label">Schlagwort</label> 
		<input type="text" required class="form-control" name="schlagwort" id="schlagwort">
	</div>
	<button type="submit" class="btn btn-primary">Kategorie Speichern</button>
	
</form>

<jsp:include page="shared/footer.jsp" />
