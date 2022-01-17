package servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import database.DatabaseKategorie;
import exception.e;
import kunde.Eintrag;
import kunde.Konto;
import kunde.Kunde;
import validierung.Validierung;

// Servlet welches beliebig viele Kontoobjekte eines angemeldeten Kunden erstellt
@WebServlet("/FormKategorien")
@MultipartConfig
public class FormKategorien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String schlagwort = request.getParameter("schlagwort");

		try {
			DatabaseKategorie.addKategorie(name, schlagwort);
		} catch (Exception e) {
			e.printStackTrace();

			request.getRequestDispatcher("kategorien.jsp").forward(request, response);

		}finally {
			request.getRequestDispatcher("kategorien.jsp").forward(request, response);
		}
	}
}