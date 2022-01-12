package kunde;

import java.util.ArrayList;
//Klasse Kunde um ein neues Kundenobjekt zu erstellen, einbindung unter anderem in Registrierung.jsp.
public class Kunde {

	private String vorname;
	private String nachname;
	private int alter;
	private String email;
	private String password;
	private String bankinstitut;
	private boolean geschaeftsbedingungenAkzeptiert;
	private boolean newsletterAbonniert;
	private ArrayList<Konto> Konten;

	public Kunde(String vorname, String nachname, int alter, String email, String password, String bankinstitut,
			boolean geschaeftsbedingungenAkzeptiert, boolean newsletterAbonniert) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.alter = alter;
		this.password = password;
		this.email = email;
		this.bankinstitut = bankinstitut;
		this.geschaeftsbedingungenAkzeptiert = geschaeftsbedingungenAkzeptiert;
		this.newsletterAbonniert = newsletterAbonniert;
	}

	public Kunde() {
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Konto> getKonten() {
		return Konten;
	}

	public void setKonten(ArrayList<Konto> konten) {
		Konten = konten;
	}
}
