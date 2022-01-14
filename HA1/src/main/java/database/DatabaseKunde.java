package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kunde.Kunde;

public class DatabaseKunde {
	private static Connection con = null;

	public static boolean addUser(Kunde kunde) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO kunde VALUES (" + "?, " + // Kunden id -
																									// Integer
					"?, " + // email - String
					"?, " + // vorname - String
					"?, " + // alter - Integer
					"?, " + // bankinstitute - String
					"?, " + // agb - boolean
					"?, " + // newsletter - boolean
					"?" + // nachname - String
					")");
			// Eindeutig id problem ? mit pgadmin soll sein

			pstmt.setInt(1, 17);
			pstmt.setString(2, kunde.getEmail());
			pstmt.setString(3, kunde.getVorname());
			pstmt.setInt(4, kunde.getAlter());
			pstmt.setString(5, kunde.getBankinstitut());
			pstmt.setBoolean(6, kunde.isGeschaeftsbedingungenAkzeptiert());
			pstmt.setBoolean(7, kunde.isNewsletterAbonniert());
			pstmt.setString(8, kunde.getNachname());
			int zeilen = pstmt.executeUpdate();
			if (zeilen > 0) {
				erfolg = true;
			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei addUser()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei addUser() - Verbindung geschlossen");
			}
		}

		return erfolg;
	}

}
