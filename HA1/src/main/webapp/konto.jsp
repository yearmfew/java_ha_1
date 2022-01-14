<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<jsp:include page = "shared/header.jsp" />


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

<form method="POST" action="FormFileUpload" class="formFile" enctype="multipart/form-data">
        <input type="file" class="form-control mb-2"  id="file" name="file" accept=".xlsx">
        <button class="btn btn-outline-secondary" type="submit"  id="file">Datei Hochladen</button>        
</form>

<form method="Post" action="FormLogout">
    <button class="btn btn-outline-secondary" type="submit"  id="logout">Logout</button> 
</form>




<jsp:include page = "shared/footer.jsp" />
