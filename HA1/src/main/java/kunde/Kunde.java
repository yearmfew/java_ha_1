package kunde;

import java.util.ArrayList;

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

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBankinstitut() {
		return bankinstitut;
	}

	public void setBankinstitut(String bankinstitut) {
		this.bankinstitut = bankinstitut;
	}

	public boolean isGeschaeftsbedingungenAkzeptiert() {
		return geschaeftsbedingungenAkzeptiert;
	}

	public void setGeschaeftsbedingungenAkzeptiert(boolean geschaeftsbedingungenAkzeptiert) {
		this.geschaeftsbedingungenAkzeptiert = geschaeftsbedingungenAkzeptiert;
	}

	public boolean isNewsletterAbonniert() {
		return newsletterAbonniert;
	}

	public void setNewsletterAbonniert(boolean newsletterAbonniert) {
		this.newsletterAbonniert = newsletterAbonniert;
	}

	public ArrayList<Konto> getKonten() {
		return Konten;
	}

	public void setKonten(ArrayList<Konto> konten) {
		Konten = konten;
	}

//	public void setKonten(Konto konto) {
//		this.Konten.add(konto);
//	}
}
