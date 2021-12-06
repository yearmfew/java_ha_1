package kunde;

import java.util.UUID;
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

}
