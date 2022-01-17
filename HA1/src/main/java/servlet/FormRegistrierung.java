package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseKunde;
import database.DatabasePassword;
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

		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");

		checkFormData cF = new checkFormData();

		Map result = cF.checkForm(vorname, nachname, alter, email, password, password2, geschaeftsbedingungen);

		System.out.println("size" + result.size());

		if (result.size() == 0) {
			Kunde newKunde = new Kunde(vorname, nachname, alter, email, password, bankinstitut, geschaeftsbedingungen,
					newsletter);
			kunden.add(newKunde);
			session.setAttribute("kunden", kunden);

			// DATABASE CONNECTION

			DatabaseKunde.addUser(newKunde);
			DatabasePassword.addPassword(newKunde);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			// fehler erklarungen erstellung mit for loop
			for (Object i : result.keySet()) {
				request.setAttribute((String) i, (String) result.get(i));
			}

			request.getRequestDispatcher("registrierung.jsp").forward(request, response);

		}

	}

}
