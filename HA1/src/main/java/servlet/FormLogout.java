package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kunde.Kunde;
// Servlet welches den Kunden auslogt und die registrierten Daten entwirft.
@WebServlet("/FormLogout")
public class FormLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Kundendaten werden aus der Session geholt um weiterverwendet zu werden
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		String nachname = kunde.getNachname();
		String vorname = kunde.getVorname();
		// Kundendaten werden zur logout.jsp gesendet 
		request.setAttribute("nachname", nachname);
		request.setAttribute("vorname", vorname);
		// Invalidierung der registrierten Daten eines Nutzers bei Logout, damit die Daten aus der Session gelöscht werden
		session.invalidate();
		
		request.getRequestDispatcher("logout.jsp").forward(request, response);

	}
}
