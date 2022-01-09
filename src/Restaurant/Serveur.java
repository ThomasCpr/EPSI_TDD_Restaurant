package Restaurant;

import java.util.ArrayList;
import java.util.Date;

public class Serveur extends Employe {
	
	// - - - - - Attributes - - - - - // 
	
	public double chiffreAffaire = 0.0;
	public ArrayList<Commande> _commandes;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		super();
		_commandes = new ArrayList<Commande>();
	}
	// - - - - - Methods - - - - - //
	public double getChiffreAffaire() {
		return chiffreAffaire;
	}
	public void setChiffreAffaire(double chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}
	
	public void prendCommande(Commande commande) {
		chiffreAffaire += commande.get_dMontantTotal();
		_commandes.add(commande);
	}
	
	public ArrayList<Commande> get_commandes() {
		return _commandes;
	}
	public void set_commandes(ArrayList<Commande> _commandes) {
		this._commandes = _commandes;
	}

	public Commande declarerCommandeNonPayee(Commande commande, Date dateEpinglage ) {
		// on récupère la commandes auprès du serveur
		Commande com = _commandes.get(_commandes.indexOf(commande));
		// on regarde si on a fournit une date (pour faciliter les tests 2 méthodes ont été faites)
		if(dateEpinglage == null){
			com.set_bEpinglee(true);
		}
		else {
			com.set_bEpingleAUneDate(true, dateEpinglage);
		}
		return com;
	}
	
}
