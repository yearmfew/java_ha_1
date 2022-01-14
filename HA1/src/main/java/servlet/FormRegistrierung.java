package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseKunde;
import database.DatabasePassword;
import kunde.Kunde;
import validierung.Validierung;

@WebServlet("/FormRegistrierung")
public class FormRegistrierung extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Kunde> kunden = new ArrayList<Kunde>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		int alter = Integer.parseInt(request.getParameter("alter"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String bankinstitut = request.getParameter("bankinstitut");
		boolean geschaeftsbedingungen = (request.getParameter("geschaeftsbedingungen") != null);
		boolean newsletter = (request.getParameter("newsletter") != null);

		request.setAttribute("vorname", vorname);
		request.setAttribute("nachname", nachname);
		request.setAttribute("alter", alter);
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("password2", password2);
		request.setAttribute("bankinstitut", bankinstitut);
		request.setAttribute("geschaeftsbedingungen", "checked");
		request.setAttribute("newsletter", newsletter);
		HttpSession session = request.getSession();
		Validierung validierung = new Validierung();

		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");

		boolean isEmailAlreadyUsed = validierung.emailCheck(sessionKunden, email);
		boolean passwordCheck = validierung.passwordCheck(password, password2);
		boolean geschaeftsbedingungenCheck = validierung.geschaeftsbedingungenCheck(geschaeftsbedingungen);

		System.out.println("regex:: " + validierung.mailCheck(email));
		System.out.println("regex Vorname:: " + validierung.nameCheck(vorname));
		System.out.println("regex Nachname:: " + validierung.nameCheck(nachname));

		
		if (!passwordCheck) {
			request.setAttribute("passwordsAreNotEqual", "Die Passwörter sind nicht gleich!");
			request.setAttribute("password", "");
			request.setAttribute("password2", "");

		}

		if (!geschaeftsbedingungenCheck) {
			request.setAttribute("bedingungenNotAccepted", "Bitte akezeptieren Sie die Geschäftsbedingungen");
			request.setAttribute("geschaeftsbedingungen", "");
		}

		if (validierung.emailCheck(sessionKunden, email)) {
			request.setAttribute("emailAlreadyUsed", "Es gibt bereits einen Account mit dieser Email!");
			request.setAttribute("email", email);
		}

		if ((password.equals(password2)) && (geschaeftsbedingungenCheck) && (!isEmailAlreadyUsed)) {
			request.setAttribute("passwordsAreNotEqual", "");
			request.setAttribute("bedingungenNotAccepted", "");
			request.setAttribute("emailAlreadyUsed", "");

			Kunde newKunde = new Kunde(vorname, nachname, alter, email, password, bankinstitut, geschaeftsbedingungen,
					newsletter);
			kunden.add(newKunde);
			session.setAttribute("kunden", kunden);
			
			// DATABASE CONNECTION: 
			
			DatabaseKunde.addUser(newKunde);
			DatabasePassword.addPassword(newKunde);
			
			
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("registrierung.jsp").forward(request, response);
		}

	}

}
