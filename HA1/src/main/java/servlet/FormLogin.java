package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseKonto;
import database.DatabaseKunde;
import database.DatabasePassword;
import kunde.Konto;
import kunde.Kunde;

// Servlet welches die Logindaten des Besuchers auf Richtigkeit prüft
@WebServlet("/FormLogin")
public class FormLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Paramter werden übermittelt um mit denen zu arbeiten
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("validLogin", false);
		Boolean isLoginSuccessfull = false;

		// get kunde id
		int kundenId = DatabaseKunde.getId(email);
		if (kundenId != 0) {
			isLoginSuccessfull = DatabasePassword.checkPassword(kundenId, password);
		} else {
			// es gibt keinen kunde mit diesem email
			// redirect to login page with error code
			request.setAttribute("keineEmailFounded", "Es gibt keinen Akkount mit diesem Email.");
		}

		if (isLoginSuccessfull) {
			Kunde kunde = DatabaseKunde.getKundenData(email);
			ArrayList<Konto> konten = DatabaseKonto.getKontoData(email);
			session.setAttribute("kunde", kunde);
			session.setAttribute("konten", konten);
			request.getRequestDispatcher("konto.jsp").forward(request, response);
		} else {
			request.setAttribute("fehlermeldung", "Nutzername oder Passwort falsch. Bitte überprüfen sie ihre Daten.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
