package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import kunde.Kunde;
import java.util.UUID;

public class DatabasePassword {
	private static Connection con = null;
	
	public static boolean addPassword(Kunde kunde) {
		boolean erfolg = false;
		while(!erfolg) {
		try {
			System.out.println(erfolg + " I am here in first try");

			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO passwort VALUES (" +
			"?, " +			// Kunden id - Integer
			"?" +			// password - String
			")");
			pstmt.setInt(1, kunde.getId());
			pstmt.setString(2, kunde.getEmail());
			int zeilen = pstmt.executeUpdate();
			if(zeilen > 0) {
				erfolg = true;
				
			}
			
			
			
		} catch(SQLException e) {
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
		
		}
		return erfolg;
	}
	

}
