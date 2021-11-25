<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
      crossorigin="anonymous">

</head>
<body>


<jsp:include page = "header.jsp" />


<div class="page-container">
<div>
<span>
    Bitte loggen Sie ein. Wenn Sie keine Akkount haben, können Sie ein neues Erstellen.
</span>
</div>
<div class="buttons">
<button type="button" class="btn btn-primary btn-lg">Login</button>
<button type="button" class="btn btn-success btn-lg">Registrieren</button>
</div>

</div>


<jsp:include page = "footer.jsp" />

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
        crossorigin="anonymous"></script>
</html>
