package servlet;
import java.util.HashMap;
import java.util.Map;

import database.DatabaseKunde;
import validierung.Validierung;

public class checkFormData {

	/**
	 * Kontrolliert ob die Form data rictig ist.
	 * es returniert einen result mit size 0 wenn alles okay ist. wir nutzen diese info 
	 * wenn etwas falsh ist es returniert einen result mit fehler name und erklarung. 
	 * 
	 * @param vorname
	 * @param nachname
	 * @param alter
	 * @param email
	 * @param password
	 * @param password2
	 * @param geschaeftsbedingungen
	 * @return 
	 * 
	 */
	public Map checkForm(String vorname, String nachname, 
			int alter, String email, String password, 
			String password2, boolean geschaeftsbedingungen ) {
		Validierung validierung = new Validierung();
		boolean passwordCheck = validierung.passwordCheck(password, password2);
		boolean geschaeftsbedingungenCheck = validierung.geschaeftsbedingungenCheck(geschaeftsbedingungen);
		boolean istMailGueltig = validierung.mailCheck(email);
		boolean istVornameGueltig = validierung.nameCheck(vorname); 
		boolean isNachnameGueltig = validierung.nameCheck(nachname); 

		boolean isEmailExist = DatabaseKunde.isEmailExist(email);
		
		Map<String, String> result = new HashMap<String, String>();
		
		if(isEmailExist) {
			result.put("emailAlreadyUsed", "Es gibt einen Akkount mit dieser email adresse.");
		}
		if (!istMailGueltig) {
			result.put("emailNichtGueltig", "email adresse ist nicht gültig.");
	    }
		if (!istVornameGueltig) {
			result.put("istVornameGueltig", "vorname ist nicht gültig.");
		}
		if (!isNachnameGueltig) {
			result.put("istNachnameGueltig", "Nachname ist nicht gültig.");
		}
		
		if (!passwordCheck) {
			result.put("passwordsAreNotEqual", "Die Passwörter sind nicht gleich!");
		}

		if (!geschaeftsbedingungenCheck) {
			result.put("bedingungenNotAccepted", "Bitte akezeptieren Sie die Geschäftsbedingungen");
		}
		
		
		return result;
		
	}
	
}
