package Restaurant;

import java.util.ArrayList;


public class Serveur extends Employe {
	
	// - - - - - Attributes - - - - - // 
	
	public double chiffreAffaire = 0.0;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		super();
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


	
	public ArrayList<Table> desassignerTableCarServiceTermine() {
		return desassignerTablesFinDeService();
	}
	
}
