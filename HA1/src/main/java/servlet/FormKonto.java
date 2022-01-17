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

import kunde.Eintrag;
import kunde.Konto;
import kunde.Kunde;
import validierung.Validierung;

// Servlet welches beliebig viele Kontoobjekte eines angemeldeten Kunden erstellt
@WebServlet("/FormKonto")
@MultipartConfig
public class FormKonto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String kontoname;
	double kontostand = 0;
	Konto konto = null;

	/**
	 * Realisiert die Anforderung, ein neues Konto zu erstellen.
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		kontoname = request.getParameter("konto"); // konto verändert zu kontoName
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		konto = new Konto(kontoname, kunde.getEmail(), kontostand);

		try {
			database.DatabaseKonto.addKonto(konto);
			int id = database.DatabaseKonto.getKontoId(kunde.getEmail());

			session.setAttribute("kontoid", id);

		} catch (NullPointerException npe) {
			System.out.println("doGet Fehler" + npe);

		}
		request.getRequestDispatcher("konto.jsp").forward(request, response);

	}

	/**
	 * Realisiert die Import-CSV Datei Anforderung.
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initialisiere notwendige Bausteine zum Datei auslesen
		Part datei = request.getPart("datei"); // request.getParameter()
		InputStream fileContent = datei.getInputStream();
		fileContent.read();
		BufferedReader fileReader = null;
		String line = "";
		String cvsSplitBy = ";";
		ArrayList<Eintrag> eintraege = new ArrayList<Eintrag>();

		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		int id = Integer.parseInt(request.getParameter("kontoId"));

		try {
			// Bereite Liste eines spezifischen Datentyps zur Speicherung der Eintr�ge vor
			eintraege = new ArrayList<Eintrag>();
			Eintrag eintrag = null;

			// Lese die Datei aus und ueberspringe die Kopfzeile
			fileReader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));
			fileReader.readLine();

			// Solange es Zeilen in der Excel Datei gibt...
			while ((line = fileReader.readLine()) != null) {
				// ... dann spalte diese Zeile anhand der einzelnen Felder auf
				String[] tokens = line.split(cvsSplitBy);
				if (tokens.length > 0) {
					for (int i = 0; i < tokens.length; i++) {
						tokens[i] = tokens[i].substring(0, tokens[i].length());
					}
					// Token 14: Betrag. Reinige Eintrag, sodass er als double geparsed werden kann
					tokens[14] = tokens[14].replace(",", ".");
					// Erstelle aus allen relevanten Eintr�gen ein Bankobjekt
					eintrag = new Eintrag(tokens[4], // Verwendungszweck
							Double.parseDouble(tokens[14]) // Betrag
					);
					// die einträge müssen aus datenbank zum user geschickt werden
					eintraege.add(eintrag);
					// DATABASE POSTEN
					database.DatabaseKonto.addEintrag(eintrag, id);
				}
			} 
			// DATABASE KONTO
			kontostand = database.DatabaseKonto.getKontostand(id);
			String kontoName = database.DatabaseKonto.getName(id);

			session.setAttribute("kontoname", kontoName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		session.setAttribute("eintraege", eintraege);
		session.setAttribute("kontostand", kontostand);
		request.getRequestDispatcher("konto.jsp").forward(request, response);

	}
}
