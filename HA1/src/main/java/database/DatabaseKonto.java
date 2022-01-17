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
	// private int id = 0;
	public static boolean addKonto(Konto konto) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();
				
			System.out.println("ich bin in databasekonto");
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO konto (kundenemail, name, kontostand)  VALUES (" + 
					"?, " + // kundenemail - String
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
	
	public static boolean addEintrag(Eintrag eintrag, int id) {
		boolean erfolg = false;

		try {
			con = DatabaseConnection.getConnection();
				
			System.out.println("ich bin in databaseEintrag");
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO posten (kontoid, betrag, Verwendungszweck)  VALUES (" + 
					"?, " + // auf welches konto soll er das packen über konto id 
					"?, " + // Betrag double
					"? " + // Verwendungszweck String
					")");
			
			
			pstmt.setInt(1, id); // kontoID??
			pstmt.setDouble(2, eintrag.getBetrag());
			pstmt.setString(3, eintrag.getVerwendungszweck());
			
			int zeilen = pstmt.executeUpdate();
			System.out.println("zeilen:: " + zeilen);
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
            System.out.println("unsere kontoid.. "+ kontoid);


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
	
	public static ArrayList getKontoData(String email) {
        ArrayList<Konto> konten = new ArrayList<Konto>();

	        try {
	            con = DatabaseConnection.getConnection();
	            String passwordDB = null;

	            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM konto WHERE kundenemail= ?");
	            pstmt.setString(1, email);
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs == null) {
		            System.out.println("kein konto.. ");
	            }else {
	            	while (rs.next()) {
		                Konto konto = new Konto(rs.getInt("kontoid"), rs.getString("name"), email, rs.getDouble("kontostand"));
	            		konten.add(konto);
		                
		                int kontoid = rs.getInt("kontoid");
		                
		                
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
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                System.out.println("[SQL] Fehler bei getKontoData - Verbindung geschlossen");
	            }
	        }
	        return konten;
    }
	
}

