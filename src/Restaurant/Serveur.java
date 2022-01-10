package Restaurant;

import java.util.ArrayList;
import java.util.Date;

public class Serveur extends Employe {
	
	// - - - - - Attributes - - - - - // 
	
	private double dChiffreAffaire = 0.0;
	private ArrayList<Commande> _aoCcommandes;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		super();
		_aoCcommandes = new ArrayList<Commande>();
	}
	// - - - - - Methods - - - - - //
	public double getdChiffreAffaire() {
		return dChiffreAffaire;
	}
	public void setdChiffreAffaire(double dChiffreAffaire) {
		this.dChiffreAffaire = dChiffreAffaire;
	}
	
	public void prendCommande(Commande commande) {
		dChiffreAffaire += commande.get_dMontantTotal();
		_aoCcommandes.add(commande);
	}
	
	public ArrayList<Commande> get_aoCcommandes() {
		return _aoCcommandes;
	}
	public void set_aoCcommandes(ArrayList<Commande> _aoCcommandes) {
		this._aoCcommandes = _aoCcommandes;
	}

	public Commande declarerCommandeNonPayee(Commande commande, Date dateEpinglage ) {
		// on récupère la commandes auprès du serveur
		Commande com = _aoCcommandes.get(_aoCcommandes.indexOf(commande));
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
