package validierung;

import java.util.ArrayList;
import java.util.regex.Pattern;

import database.DatabaseKunde;
import kunde.Kunde;
// Klasse Validierung, welche ausgefüllte Felder des Formulars überprüft
public class Validierung {

	public Validierung() {

	}
	/**
	 * Es kontrolliert ob der Name in der gewünchte Format ist.
	 * @param name, vor- oder nachname
	 * @return true oder false. wenn name gultig ist True usw. 
	 */
	public boolean nameCheck(String name) {
		return Pattern.matches("[A-Z]+([a-z]|[\\- ])*", name);
	}
	
	
	/**
	 * Es kontrolliert ob email in der gewünschten format ist.
	 * gewünshte Format: 
	 * • Sie besteht aus zwei Teilen, die mit einem @ getrennt werden
	 * • Erlaubte Zeichen sind Kleinbuchstaben, Punkte, Plus und Minus
	 * • Sie ist nicht beliebig lang: Der lokale Teil (vor dem ”@“) ist nicht länger als 63 Zeichen,
	 * • die E-Mail Adresse insgesamt ist nicht länger als 254 Zeichen.
	 * @param email email zu registrieren.
	 * @return true or false. Wenn email gultig ist True usw.
	 */
	public boolean mailCheck(String email) {
		System.out.println(email);
		return Pattern.matches("^([a-z]|[\\+|\\-|\\.]){1,63}[@](([a-z]|[\\+|\\-|\\.]){1,190})$", email);
				
	}
	
	/**
	 * Überprüfung ob beide Passwörter gleich sind.
	 * @param password
	 * @param password2
	 * @return isSame, wenn Passwörter gleich sind ist es true usw.
	 */
	public boolean passwordCheck(String password, String password2) {
		boolean isSame = false;
		if ((password.equals(password2))) {
			isSame = true;
		}
		return isSame;
	}
	// 
	/**
	 * Überprüfung ob agb checked ist. Falls Geschäftsbedingungen unchecked bleiben wird boolean icChecked false übermittelt,
	// sodass der Users die Daten nicht submitten kann.
	 * @param geschaeftsbedingungen
	 * @return isChecked, wenn agb checked ist, ist es true usw.
	 */
	public boolean geschaeftsbedingungenCheck(boolean geschaeftsbedingungen) {
		boolean isChecked = false;
		if (geschaeftsbedingungen) {
			isChecked = true;
		}
		return isChecked;
	}

}
