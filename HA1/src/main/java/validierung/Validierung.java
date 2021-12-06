package validierung;

import java.util.ArrayList;

import kunde.Kunde;

public class Validierung {

	public Validierung() {

	}

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

	public boolean passwordCheck(String password, String password2) {
		boolean isSame = false;
		if ((password.equals(password2))) {
			isSame = true;
		}
		return isSame;
	}

	public boolean geschaeftsbedingungenCheck(boolean geschaeftsbedingungen) {
		boolean isChecked = false;
		if (geschaeftsbedingungen) {
			isChecked = true;
		}
		return isChecked;
	}

}
