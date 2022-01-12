package validierung;

import java.util.ArrayList;

import kunde.Kunde;
// Klasse Validierung, welche ausgefüllte Felder des Formulars überprüft
public class Validierung {

	public Validierung() {

	}
	
	// Bei doppelter Eingabe überprüfung. Falls Email schon benutzt boolean = true
	public boolean emailCheck(ArrayList<Kunde> Kunden, String email) {
				boolean isEmailAlreadyUsed = false;
		if (Kunden != null) {
			for (Kunde k : Kunden) {
				String mail = k.getEmail();
				if (mail.equals(email)) {
					isEmailAlreadyUsed = true;
				}
			}
		}
		return isEmailAlreadyUsed;

	}
	// Überprüfung ob beide Passwörter richtig sind.Boolean Rückgabe true bei Übereinstimmung.
	public boolean passwordCheck(String password, String password2) {
		boolean isSame = false;
		if ((password.equals(password2))) {
			isSame = true;
		}
		return isSame;
	}
	// Falls Geschäftsbedingungen unchecked bleiben wird boolean icChecked false übermittelt,
	// sodass der Users die Daten nicht submitten kann.
	public boolean geschaeftsbedingungenCheck(boolean geschaeftsbedingungen) {
		boolean isChecked = false;
		if (geschaeftsbedingungen) {
			isChecked = true;
		}
		return isChecked;
	}

}
