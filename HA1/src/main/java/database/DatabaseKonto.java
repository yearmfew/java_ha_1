package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import kunde.Eintrag;
import kunde.Konto;

public class DatabaseKonto {
	private static Connection con = null;

	/**
	 * Fügt ein Konto zu Datenbank hinzu.
	 * 
	 * @param konto konto objekt besteht aus email, name, kontostand
	 * @return erfolg
	 */
	public static boolean addKonto(Konto konto) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();

			System.out.println("add konto funktioniert");
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO konto (kundenemail, name, kontostand)  VALUES (" + "?, " + // kundenemail
																												// -
																												// String
							"?, " + // kontoname - String
							"? " + // kontostand - Integer
							")");

			pstmt.setString(1, konto.getKundenEmail());
			pstmt.setString(2, konto.getName());
			pstmt.setDouble(3, konto.getKontostand());

			int zeilen = pstmt.executeUpdate();
			System.out.println("zeilen:: " + zeilen);
			if (zeilen > 0) {
				erfolg = true;

			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei addkonto()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei addkonto() - Verbindung geschlossen");
			}
		}

		return erfolg;
	}

	/**
	 * Erstellt einen Eintrag für einen Posten.
	 * 
	 * @param eintrag besteht aus kontoid, betrag und verwendungszweck
	 * @param id      konto id
	 * @return erfolg
	 */
	public static boolean addEintrag(Eintrag eintrag, int id) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();

			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO posten (kontoid, betrag, Verwendungszweck)  VALUES (" + "?, " + // auf
																													// welches
																													// konto
																													// soll
																													// er
																													// das
																													// packen
																													// über
																													// konto
																													// id
							"?, " + // Betrag double
							"? " + // Verwendungszweck String
							")");

			pstmt.setInt(1, id); // kontoID??
			pstmt.setDouble(2, eintrag.getBetrag());
			pstmt.setString(3, eintrag.getVerwendungszweck());

			int zeilen = pstmt.executeUpdate();
			if (zeilen > 0) {
				erfolg = true;

			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei addEintrag()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei addEintrag() - Verbindung geschlossen");
			}
		}

		return erfolg;
	}

	/**
	 * Methode welche kontoid zurückgibt.
	 * 
	 * @param email einzigartige kunden email des users. return kontoid
	 */
	public static int getKontoId(String email) {
		int kontoid = 0;
		try {
			con = DatabaseConnection.getConnection();
			String passwordDB = null;

			PreparedStatement pstmt = con.prepareStatement("SELECT kontoid FROM konto WHERE kundenemail= ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				kontoid = rs.getInt("kontoid");
			}
			System.out.println("unsere kontoid.. " + kontoid);

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getKontoId" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getKontoId - Verbindung geschlossen");
			}
		}
		return kontoid;
	}

	/**
	 * Konto daten des benutzers werden übermittelt.
	 * 
	 * @param email
	 * @return konten, alle konten des nutzer
	 */
	public static ArrayList getKontoData(String email) {
		ArrayList<Konto> konten = new ArrayList<Konto>();

		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM konto WHERE kundenemail= ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs == null) {
				System.out.println("kein konto.. ");
			} else {
				while (rs.next()) {
					Konto konto = new Konto(rs.getInt("kontoid"), rs.getString("name"), email,
							rs.getDouble("kontostand"));
					konten.add(konto);
					ArrayList<String> kontoNamen = new ArrayList<String>();
					kontoNamen.add(rs.getString("name"));
					double kontoStand = rs.getDouble("kontostand");

					konto.setKontoStand(kontoStand);
					konto.setKundenEmail(email);
					konto.setName(rs.getString("name"));
				}

			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getKontoData" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getKontoData - Verbindung geschlossen");
			}
		}
		return konten;
	}

	/**
	 * Kontostand des konto wird übermittelt.
	 * 
	 * @param id kontoid
	 * @return kontostand
	 */
	public static double getKontostand(int id) {
		double kontostand = 0;
		try {
			con = DatabaseConnection.getConnection();
			// String passwordDB = null;

			PreparedStatement pstmt = con
					.prepareStatement("SELECT SUM(betrag) AS kontostand FROM posten WHERE kontoid = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				kontostand = rs.getDouble("kontostand");
			}
			System.out.println("unsere betrag.. " + kontostand);

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getKontostand" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getKontostand - Verbindung geschlossen");
			}
		}
		return kontostand;
	}

	/**
	 * Gibt kontoname von gegebene id.
	 * 
	 * @param id kontoid
	 * @return name
	 */
	public static String getName(int id) {
		String name = null;
		try {
			con = DatabaseConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT name " + "FROM konto WHERE kontoid= ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs == null) {
				System.out.println("kein konto.. ");
			} else {
				while (rs.next()) {
					name = rs.getString("name");

				}

			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getName" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getName - Verbindung geschlossen");
			}
		}
		return name;
	}

}
