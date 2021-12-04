<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page = "header.jsp" />


<form action="FormKonto" method="POST">
  <div class="form-inputs"> 
    <div>
      <label for="kontoName" class="form-label">Konto Name</label>
      <input type="text" required class="form-control" name="kontoName" id="kontoName">
    </div>      
    <div>
      <label for="email" class="form-label">Email</label>
      <input type="email" required class="form-control" name="email" id="email">
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



<jsp:include page = "footer.jsp" />
