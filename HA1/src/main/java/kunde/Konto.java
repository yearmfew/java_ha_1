package kunde;



public class Konto {
	private String name; 
	private String kundenEmail; 
	private double kontoStand;
	
	/** Konstruktor: Name, Mail, ID
	 * @param name
	 * @param kundenEmail
	 * @param kontoID
	 */
	public Konto(String name, String kundenEmail, double kontoStand) {
		super();
		this.name = name;
		this.kundenEmail = kundenEmail;
		this.kontoStand = kontoStand;
	}


	public String getName() {
		return name;
	}


	public String getKundenEmail() {
		return kundenEmail;
	}


	public double getKontostand() {
		return kontoStand;
	} 
	

}



/*
// Beinhaltet getter und setter für FormKonto
public class Konto {
	private String kontoName;
	private String id;
	private Kunde Kunde;

	public Konto(String kontoName, Kunde kunde) {
		this.kontoName = kontoName;
		Kunde = kunde;
		// KontoID von einem Kunden wird mithilfe der email erstellt
		String email = kunde.getEmail();
		String[] splittedMail = email.split("@");
		this.id = splittedMail[0] + UUID.randomUUID().toString();
	}

	public void setKontoName(String kontoName) {
		this.kontoName = kontoName;
	}
	
	public String getKontoName(String kontoName) {
		return kontoName;
	}
	*/