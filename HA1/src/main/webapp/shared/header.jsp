<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Header.jsp wird auf allen Seiten für das Layout verlinkt, um mehrfache Zugriffe aus die CSS Dateien zu vermeiden. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/mainPage.css" type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

<div class="menu">
	<div>
		<a href="konto.jsp">Konto</a>
	</div>
	<span>Hello <strong> ${ sessionScope.kunde.getVorname() }
			${ sessionScope.kunde.getNachname() } </strong> Wilkommen zur Bank BA
	</span>

	<form method="Post" action="FormLogout" class="menu-loggedin">
		<button type="submit" id="logout">Logout</button>
	</form>
</div>


<div class="page-container">