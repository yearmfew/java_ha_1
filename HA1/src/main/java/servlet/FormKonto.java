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
	
	
	
	/** Realisiert die Anforderung, ein neues Konto zu erstellen.
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		kontoname = request.getParameter("konto"); //konto verändert zu kontoName
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kunde");
		ArrayList<Konto> kontoliste = null;
		
		
		
		
		
		
		// das muss vlt in catch block  !DATABASE
		Konto konto = new Konto(kontoname, kunde.getEmail(), kontostand);
		database.DatabaseKonto.addKonto(konto);
		// dropdown menu
		// bis jetzt nur für ein konto, wird immer überschrieben, birol hilfe
		request.setAttribute("konto1",
				 kontoname);
		String auswahl = request.getParameter("KontoUpload");
		// hier steht jetzt "kontoname" 
		
		
		
		
		
		
		
		 
		// int id = 0;

		try {
			kontoliste = (ArrayList<Konto>) session.getAttribute("kontoliste");
			// id = Validierung.getHoechsteID(kontoliste); kundenid aus datenbank
			// konto = new Konto(kontoname, kunde.getEmail(), kontostand);
			kontoliste.add(konto);
			
			
			
			
			
			
			
		} catch (NullPointerException npe) {
			// habe konto erstmal auskommentiert und aus catch block rausgenommen; database konto msus aber rein
			// Konto konto = new Konto(kontoname, kunde.getEmail(), 0);
			kontoliste = new ArrayList<Konto>();
			kontoliste.add(konto);
			
		}
		session.setAttribute("kontoliste", kontoliste);
		request.getRequestDispatcher("konto.jsp").forward(request, response);
		
	}

	/** Realisiert die Import-CSV Datei Anforderung.
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
						System.out.println("Token: " + tokens[i].toString());
					}
					// Token 14: Betrag. Reinige Eintrag, sodass er als double geparsed werden kann
					tokens[14] = tokens[14].replace(",", ".");
					// Erstelle aus allen relevanten Eintr�gen ein Bankobjekt
					eintrag = new Eintrag(tokens[0], // Auftragskonto
							tokens[1], // Buchungstag
							tokens[2], // Valutdatum
							tokens[3], // Buchungstext
							tokens[4], // Verwendungszweck
							tokens[5], // GlaeubigerID
							tokens[11], // Beguenstigter
							tokens[12], // Kontonummer
							tokens[13], // BIC
							Double.parseDouble(tokens[14]) // Betrag
					);
					kontostand += Double.parseDouble(tokens[14]); 
					eintraege.add(eintrag);
					
					
					
					
					// Vor Import Kunde soll verbinden Konto mit Upload
					
					// DATABASE
					database.DatabaseKonto.addEintrag(eintrag);
					
					
					// zeilen in csv sollen zu posten werden 
					// anstelle kontoliste soll es jetzt zu datenbank geschickt werden
					// vor import kunde kann entscheiden auf welches konto hochgeladen wird
					// kontoname, kundenemail input soll zur datenbank geschickt werden
					// kontostand soll zu datenbank geschickt werden
					// für aufgabe 5.2: betrag, kontostand und verwendungszweck soll zur datenbank geschickt werden
					// kontostand: aufsummierte posten eines kontos (code immer allgemein schreiben)
					
				}
			}
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

		// HttpSession session = request.getSession();
		session.setAttribute("eintraege", eintraege);
		session.setAttribute("kontostand", kontostand);
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
}




/*
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
*/