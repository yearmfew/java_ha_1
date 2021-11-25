<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page = "header.jsp" />

<h3>Login Form</h3>
 <form action="FormServlet" method="POST" class="login-form">
     <input type="text" placeholder="Geben Sie Nutzername ein.." name="username">
     <input type="password" placeholder="Geben Sie ihre Password ein.." name="password">
     <button type="submit">Login</button>
</form>

<jsp:include page = "footer.jsp" />
