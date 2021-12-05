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

		if (!(password.equals(password2))) {
			request.setAttribute("passwordsAreNotEqual", "Password sind nicht gleicht");
		}
		if (!geschaeftsbedingungen) {
			request.setAttribute("bedingungenNotAccepted", "Müssen Sie geschaeftsbedingungen akzeptieren");
		}

		HttpSession session = request.getSession();
		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");
		boolean isEmailAlreadyUsed = false;
		if (sessionKunden != null) {
			for (Kunde k : sessionKunden) {
				String mail = k.getEmail();
				if (mail.equals(email)) {
					request.setAttribute("emailAlreadyUsed", "Es gibt einen Account mit diesem Email.");
					isEmailAlreadyUsed = true;
				}
			}
		}

		if ((password.equals(password2)) && (geschaeftsbedingungen) && (!isEmailAlreadyUsed)) {
			request.setAttribute("passwordsAreNotEqual", "");
			request.setAttribute("bedingungenNotAccepted", "");
			request.setAttribute("emailAlreadyUsed", "");

			Kunde newKunde = new Kunde(vorname, nachname, alter, email, password, bankinstitut, geschaeftsbedingungen,
					newsletter);
			kunden.add(newKunde);
			session.setAttribute("kunden", kunden);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("registrierung.jsp").forward(request, response);
		}

	}

}
