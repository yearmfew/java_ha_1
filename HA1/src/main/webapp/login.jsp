<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />

</head>
<body>
<jsp:include page = "header.jsp" />

<h3>Login Form</h3>
 <form action="FormServlet" method="POST" class="login-form">
     <input type="text" placeholder="Geben Sie Nutzername ein.." name="username">
     <input type="password" placeholder="Geben Sie ihre Password ein.." name="password">
     <button type="submit">Login</button>
</form>

<jsp:include page = "footer.jsp" />
</body>
</html>