package kunde;

public class Eintrag {
	
	String auftragskonto;
	String buchungstage;
	String valutdatum;
	String buchungstext;
	String verwendungszweck;
	String glaeubigerID;
	String beguenstigter;
	String kontonummer;
	String bic;
	double betrag;

	/** Konstruktor für einen Kontoeintrag. Wird für den Import von .CSV Dateien verwendet.
	 * 
	 * @param auftragskonto
	 * @param buchungstage
	 * @param valutdatum
	 * @param buchungstext
	 * @param verwendungszweck
	 * @param glaeubigerID
	 * @param beguenstigter
	 * @param kontonummer
	 * @param bic
	 * @param betrag
	 */
	public Eintrag(String auftragskonto, String buchungstage, String valutdatum, String buchungstext,
			String verwendungszweck, String glaeubigerID, String beguenstigter, String kontonummer, String bic,
			double betrag) {
		super();
		this.auftragskonto = auftragskonto;
		this.buchungstage = buchungstage;
		this.valutdatum = valutdatum;
		this.buchungstext = buchungstext;
		this.verwendungszweck = verwendungszweck;
		this.glaeubigerID = glaeubigerID;
		this.beguenstigter = beguenstigter;
		this.kontonummer = kontonummer;
		this.bic = bic;
		this.betrag = betrag;
	}

	public String getAuftragskonto() {
		return auftragskonto;
	}

	public String getBuchungstage() {
		return buchungstage;
	}

	public String getValutdatum() {
		return valutdatum;
	}

	public String getBuchungstext() {
		return buchungstext;
	}

	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public String getGlaeubigerID() {
		return glaeubigerID;
	}

	public String getBeguenstigter() {
		return beguenstigter;
	}

	public String getKontonummer() {
		return kontonummer;
	}

	public String getBic() {
		return bic;
	}

	public double getBetrag() {
		return betrag;
	}

}
