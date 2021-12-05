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

/**
 * Servlet implementation class FormLogin
 */
@WebServlet("/FormLogin")
public class FormLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// we take the parameter we send with this syntax:
		// In inputs we have names. We take the parameters with this names.
		// look login.jsp form to understand

		// where should it be username and password
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");
		for (Kunde k : sessionKunden) {
			if (k.getEmail().equals(email)) {
				request.setAttribute("vorname", k.getVorname());
				// password check
				if (k.getPassword().equals(password)) {
					request.getRequestDispatcher("konto.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}

	}

}
