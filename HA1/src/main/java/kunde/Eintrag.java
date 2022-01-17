package kunde;

public class Eintrag {
	
	
	String verwendungszweck;
	double betrag;

	/** Konstruktor für einen Kontoeintrag. Wird für den Import von .CSV Dateien verwendet.
	 
	 * @param verwendungszweck
	 * @param betrag
	 */
	public Eintrag(
			String verwendungszweck,
			double betrag) {
		super();
		this.verwendungszweck = verwendungszweck;
		this.betrag = betrag;
	}
	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public double getBetrag() {
		return betrag;
	}
	
}
