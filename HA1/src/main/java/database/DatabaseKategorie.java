package database;


import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class DatabaseKategorie {
	private static Connection con = null;

	/**
	 * Erstellt Kategorie für benutzer Eingaben. 
	 * @param name name der Kategorie
	 * @param schlagwort schlagwprt nach dem gesucht werden soll
	 * @return erfolg
	 */
	public static boolean addKategorie(String name, String schlagwort) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();
				
			
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO kategorie (name, schlagwort)  VALUES (" + 
					"?, " + // name - String
					"? " + // schlagwort - String
					")");
			
			pstmt.setString(1, name);
			pstmt.setString(2, schlagwort);
			
			int zeilen = pstmt.executeUpdate();
			System.out.println("zeilen:: " + zeilen);
			if (zeilen > 0) {
				erfolg = true;
				
			
			}

		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei addKategorie()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei addKategorie() - Verbindung geschlossen");
			}
		}     

		return erfolg;
	}
}
