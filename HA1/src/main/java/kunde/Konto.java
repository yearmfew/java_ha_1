package kunde;



public class Konto {



	private String name; 
	private String kundenEmail; 
	private double kontoStand;
	private int kontoId;
	
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
	public Konto(int kontoId, String name, String kundenEmail, double kontoStand) {
		super();
		this.kontoId = kontoId;
		this.name = name;
		this.kundenEmail = kundenEmail;
		this.kontoStand = kontoStand;
	}
	
	public Konto() {
		
	}

	public int getKontoId() {
		return kontoId;
	}
	public void setKontoId(int kontoId) {
		this.kontoId = kontoId;
	}
	public double getKontoStand() {
		return kontoStand;
	}


	public void setKontoStand(double kontoStand) {
		this.kontoStand = kontoStand;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setKundenEmail(String kundenEmail) {
		this.kundenEmail = kundenEmail;
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


