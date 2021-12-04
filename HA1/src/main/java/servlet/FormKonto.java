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

@WebServlet("/FormKonto")
public class FormKonto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String kontoName = request.getParameter("kontoName");
		Kunde myKunde = new Kunde();
		HttpSession session = request.getSession();
		// We need a kunde to create a konto. We are going to take the eingeloggte kunde
		// directly.
		// For now I am finding it by using email.
		// this for loop going to be deleted
		// email will be deleted from form and here
		String email = request.getParameter("email");
		ArrayList<Kunde> sessionKunden = (ArrayList<Kunde>) session.getAttribute("kunden");
		for (Kunde k : sessionKunden) {
			String mail = k.getEmail();
			if (mail.equals(email)) {
				myKunde = k;
			}
		}

		Konto konto = new Konto(kontoName, myKunde);
		System.out.println(konto.getId());
		System.out.println(myKunde.getVorname());
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}

}
