<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Main Page</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
        <!-- TODO: active link dynamic make. -->
          <a class="nav-link active" aria-current="page" href="konto.jsp">Konto</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="registrierung.jsp">Registrierung</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <button class="btn btn-outline-success" type="submit">Logout</button>
        </li>
      </ul>
    </div>
  </div>
</nav>

