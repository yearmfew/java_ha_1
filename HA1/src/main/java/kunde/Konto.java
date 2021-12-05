package kunde;

import java.util.UUID;

public class Konto {
	private String kontoName;
	private String id;
	private Kunde Kunde;

	public Konto(String kontoName, Kunde kunde) {
		this.kontoName = kontoName;
		Kunde = kunde;
		String email = kunde.getEmail();
		String[] splittedMail = email.split("@");
		id = splittedMail[0] + UUID.randomUUID().toString();
	}

	public String getKontoName() {
		return kontoName;
	}

	public void setKontoName(String kontoName) {
		this.kontoName = kontoName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Kunde getKunde() {
		return Kunde;
	}

	public void setKunde(Kunde kunde) {
		Kunde = kunde;
	}

}
