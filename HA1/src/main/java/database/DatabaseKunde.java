package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kunde.Kunde;

/**
 * Alle database Prozess mit kunde table
 * 
 * @author yearmfew
 *
 */
public class DatabaseKunde {
	private static Connection con = null;
	/**
	 * Fügt einen Benutzer zur Datenbank hinzu.
	 * @param kunde Kundenobjekt, welche alle Kundendaten aus dem Formular enthält.
	 * @return erfolg
	 */
	public static boolean addUser(Kunde kunde) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO kunde (email, vorname, nachname, alter, bank, agb, newsletter)  VALUES (" + "?, " + // email
																														// -
																														// String
							"?, " + // vorname - String
							"?, " + // alter - Integer
							"?, " + // bankinstitute - String
							"?, " + // agb - boolean
							"?, " + // newsletter - boolean
							"?" + // nachname - String
							")");

			pstmt.setString(1, kunde.getEmail());
			pstmt.setString(2, kunde.getVorname());
			pstmt.setString(3, kunde.getNachname());
			pstmt.setInt(4, kunde.getAlter());
			pstmt.setString(5, kunde.getBankinstitut());
			pstmt.setBoolean(6, kunde.isGeschaeftsbedingungenAkzeptiert());
			pstmt.setBoolean(7, kunde.isNewsletterAbonniert());

			int zeilen = pstmt.executeUpdate();
			if (zeilen > 0) {
				erfolg = true;
				int kundenId = 0;

				// abrufe die Id kundenid kunde auf der Datenbank
				// wir speichern es damit wir es beim password adding und wenn braucht nutzen
				// kann
				PreparedStatement pstmtForKundenId = con.prepareStatement("SELECT kundenid FROM kunde WHERE email = ?");
				pstmtForKundenId.setString(1, kunde.getEmail());
				ResultSet rs = pstmtForKundenId.executeQuery();
				System.out.println("rs:::" + rs);

				while (rs.next()) {
					kundenId = rs.getInt("kundenid");
				}

				kunde.setId(kundenId);
				

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
	/**
	 * Prüft ob Kundenemail bereits in der Datenbank existiert.
	 * @param email
	 * @return erfolg
	 */
	public static boolean isEmailExist(String email) {
		boolean isExist = false;
		try {
			con = DatabaseConnection.getConnection();

			PreparedStatement pstmt = con
					.prepareStatement("SELECT COUNT('email') as result FROM kunde WHERE email = ?");

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int returnInt = rs.getInt("result");
				if (returnInt == 1) {
					isExist = true;
				}
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
		return isExist;
	}
	/**
	 * Die KundenId wird aus der Datenbank zur Session gesendet.
	 * @param email
	 * @return erfolg
	 */
	public static int getId(String email) {
		int kundenId = 0;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT kundenid FROM kunde WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {
				kundenId = rs.getInt("kundenid");
			}
			System.out.println(kundenId);

		}catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getId()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getId() - Verbindung geschlossen");
			}
		}
		
		return kundenId;
	}

	/**
	 * Die Kundendaten werden aus Datenbank geholt.
	 * @param email vom angemeldeten User
	 * @return erfolg
	 */
	public static Kunde getKundenData(String email ) {
		Kunde kunde = new Kunde();
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kunde WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {
				int kundenId = rs.getInt("kundenid");
				String kundenVorname = rs.getString("vorname");
				String kundenNachname = rs.getString("nachname");
				String kundenBank = rs.getString("bank");
				int kundenAlter = rs.getInt("alter");
				boolean kundenAgb = rs.getBoolean("agb");
				boolean kundenNewsletter = rs.getBoolean("newsletter");
				
				kunde.setId(kundenId);
				kunde.setAlter(kundenAlter);
				kunde.setBankinstitut(kundenBank);
				kunde.setEmail(email);
				kunde.setGeschaeftsbedingungenAkzeptiert(kundenNewsletter);
				kunde.setNachname(kundenNachname);
				kunde.setNewsletterAbonniert(kundenNewsletter);
				kunde.setVorname(kundenVorname);
				

			}

		}catch (SQLException e) {
			System.err.println(e);
			System.err.println("SQL Fehler bei getKundenData()" + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[SQL] Fehler bei getKundenData() - Verbindung geschlossen");
			}
		}
		
		
		return kunde;
	}


}
