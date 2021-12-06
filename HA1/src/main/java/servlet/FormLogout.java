package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kunde.Kunde;

@WebServlet("/FormLogout")
public class FormLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		String nachname = kunde.getNachname();
		String vorname = kunde.getVorname();
		request.setAttribute("nachname", nachname);
		request.setAttribute("vorname", vorname);
		session.invalidate();
		request.getRequestDispatcher("logout.jsp").forward(request, response);

	}
}
