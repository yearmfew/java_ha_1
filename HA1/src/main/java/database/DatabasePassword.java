package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import kunde.Kunde;
import java.util.UUID;

public class DatabasePassword {
	private static Connection con = null;
	/**
	 * Speichert das Passwort des Users in die Datenbank.
	 * @param kunde Kundenobjekt, welche alle Kundendaten aus dem Formular enthält.
	 * @return erfolg
	 */
	public static boolean addPassword(Kunde kunde) {
		boolean erfolg = false;
		while (!erfolg) {
			try {
				con = DatabaseConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO passwort VALUES (" + "?, " + // Kunden id -
																											// Integer
						"?" + // password - String
						")");
				pstmt.setInt(1, kunde.getId());
				pstmt.setString(2, kunde.getPassword());
				int zeilen = pstmt.executeUpdate();
				if (zeilen > 0) {
					erfolg = true;

				}

			} catch (SQLException e) {
				System.err.println("SQL Fehler bei addPassword()" + e.toString());
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("[SQL] Fehler bei addPassword() - Verbindung geschlossen");
				}
			}

		}
		return erfolg;
	}
	/**
	 * Prüft ob die eingegebenen Passwörter übereinstimmen.
	 * @param kundenid des Users, Passwort des Users
	 * @return erfolg
	 */
	public static boolean checkPassword(int kundenId, String password) {
		boolean loginSuccessfull = false;
		try {
			con = DatabaseConnection.getConnection();
			String passwordDB = null;

			PreparedStatement pstmt = con.prepareStatement("SELECT passwort FROM passwort WHERE kundenid= ?");
			pstmt.setInt(1, kundenId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				passwordDB = rs.getString("passwort");
			}
			System.out.println("pass DB: "+ passwordDB);

			if (passwordDB.equals(password)) {
				loginSuccessfull = true;
			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei checkPassword()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei checkPassword() - Verbindung geschlossen");
			}
		}
		return loginSuccessfull;
	}

}
