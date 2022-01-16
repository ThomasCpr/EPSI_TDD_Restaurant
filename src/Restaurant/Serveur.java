package Restaurant;

import java.util.ArrayList;
import java.util.Date;

public class Serveur extends Employe {
	
	// - - - - - Attributes - - - - - // 
	
	private double dChiffreAffaire = 0.0;
	private ArrayList<Commande> _aoCcommandes;
	private Cuisine _oCuisine;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		super();
		_aoCcommandes = new ArrayList<Commande>();
	}
	
	// - - - - - GETTERS && SETTERS - - - - - //
	public double getdChiffreAffaire() {
		return dChiffreAffaire;
	}
	public void setdChiffreAffaire(double dChiffreAffaire) {
		this.dChiffreAffaire = dChiffreAffaire;
	}
	public ArrayList<Commande> get_aoCcommandes() {
		return _aoCcommandes;
	}
	public void set_aoCcommandes(ArrayList<Commande> _aoCcommandes) {
		this._aoCcommandes = _aoCcommandes;
	}
	public Cuisine get_oCuisine() {
		return _oCuisine;
	}
	public void set_oCuisine(Cuisine _oCuisine) {
		this._oCuisine = _oCuisine;
	}

	// - - - - - METHODS - - - - - //
	public void prendCommande(Commande commande) {
		dChiffreAffaire += commande.get_dMontantTotal();
		_aoCcommandes.add(commande);
		if(commande.is_bNourriture()) {
			_oCuisine.get_aoCommandes().add(commande);
		}
	}
	
	public Table getTablePourCmd(Table table) {
		return get_aoTables().get(get_aoTables().indexOf(table));
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
