package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// prüfe ob es kunden in der liste gibt und sende die kundendetails
		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");
		for (Kunde customer : sessionKunden) {
			if (customer.getEmail().equals(email)) {
				request.setAttribute("vorname", customer.getVorname());
				request.setAttribute("kunde", customer);
				// prüfe ob Passwörter gleich sind 
				if (customer.getPassword().equals(password)) {
					session.setAttribute("kunde", customer);
					request.setAttribute("nutzername", "Hey " + customer.getVorname() + " " + customer.getNachname());
					// weiterleitung zur Kontoseite
					request.getRequestDispatcher("konto.jsp").forward(request, response);

				} else {
					request.setAttribute("fehlermeldung",
							"Nutzername oder Passwort falsch. Bitte überprüfen sie ihre Daten.");
					// login muss erneut geschehen auf der login seite
					request.getRequestDispatcher("login.jsp").forward(request, response);

				}
			}
		}

	}

}
