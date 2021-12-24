package Restaurant;

public class Serveur {
	
	// - - - - - Attributes - - - - - // 
	
	public double chiffreAffaire = 0.0;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		
	}
	// - - - - - Methods - - - - - //
	public void prendCommande(double montant) {
		chiffreAffaire += montant;
	}
	
	
	public double getChiffreAffaire() {
		return chiffreAffaire;
	}
	public void setChiffreAffaire(double chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}

	@Override
	public String toString() {
		return "Serveur [chiffreAffaire=" + chiffreAffaire + ", getChiffreAffaire()=" + getChiffreAffaire()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
}
