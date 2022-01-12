package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kunde.Konto;
import kunde.Kunde;
// Servlet welches beliebig viele Kontoobjekte eines angemeldeten Kunden erstellt
@WebServlet("/FormKonto")
public class FormKonto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Parameter werden aus der Session geladen 
		String kontoName = request.getParameter("kontoName");
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		Konto konto = new Konto(kontoName, kunde);
		ArrayList<Konto> Konten = new ArrayList<Konto>();
		// Neues Konto kann durch Benutzer erstellt werden
		Konten.add(konto);
		kunde.setKonten(Konten);
		// nutzername wird zur konto.jsp gesendet um den Kunden zu begrüßen 
		request.setAttribute("nutzername", "Hey " + kunde.getVorname() + " " + kunde.getNachname());
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}

}
