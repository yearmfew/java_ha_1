<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page = "header.jsp" />

<form action="FormRegistrierung" method="POST">
  <div class="form-inputs"> 
    <div>
      <label for="vorname" class="form-label">Vorname</label>
      <input type="text" required class="form-control" name="vorname" id="vorname">
    </div>
    <div>
      <label for="nachname" class="form-label">Nachname</label>
      <input type="text" required class="form-control" name="nachname" id="nachname">
    </div>
      <div>
      <label for="alter" class="form-label">Alter</label>
      <input type="number" required class="form-control" name="alter" id="alter">
    </div>
    <div>
      <label for="email" class="form-label">Email</label>
      <input type="email" required class="form-control" name="email" id="email">
            <span class="warning">
      ${ emailAlreadyUsed }
      </span>
    </div>
    <div>
      <label for="password"  class="form-label">Passwort</label>
      <input type="password" required class="form-control" name="password" id="password">
      <span class="warning">
      ${ passwordsAreNotEqual }
      </span>
      </div>
    <div>
      <label for="password2" class="form-label">Wiederholen Sie ihr Passwort</label>
      <input type="password" required class="form-control" name="password2" id="password2">
        <span class="warning">
      ${ passwordsAreNotEqual } 
      </span>
  
    </div>
    <div>
      <label for="bankinstitut" class="form-label">Bankinstitut</label>
      <input type="text" required class="form-control" name="bankinstitut" id="bankinstitut">
    </div>
    
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="geschaeftsbedingungen" name="geschaeftsbedingungen"  id="geschaeftsbedingungen">
      <label class="form-check-label" for="geschaeftsbedingungen">
        Ich akzeptiere die Geschäftsbedingungen
      </label>
            <span class="warning">
      ${ bedingungenNotAccepted } 
      </span>
      
      
    </div>
    <div class="form-check">
      <input class="form-check-input" type="checkbox" name="newsletter" value="newsletter"  id="newsletter">
      <label class="form-check-label" for="newsletter">
        Zum Newsletter anmelden
      </label>
    </div>
  </div>
  <div class="form-buttons"> 
    <button type="submit" class="btn btn-primary">Registrieren</button>
  </div>
</form>

<jsp:include page = "footer.jsp" />

