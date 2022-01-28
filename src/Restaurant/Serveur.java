package Restaurant;

import java.util.ArrayList;
import java.util.Date;


public class Serveur extends Employe {
	
	// - - - - - Attributes - - - - - // 
	
	private double _dChiffreAffaire = 0.0;
	private Cuisine _oCuisine;
	
	
	// - - - - - Constructors - - - - - //
	public Serveur() {
		super();
	}
	public Serveur(Cuisine cuisine) {
		super();
		_oCuisine = cuisine;
	}
	
	// - - - - - GETTERS && SETTERS - - - - - //
	public double getdChiffreAffaire() {
		return _dChiffreAffaire;
	}
	public void setdChiffreAffaire(double dChiffreAffaire) {
		this._dChiffreAffaire = dChiffreAffaire;
	}
	public Cuisine get_oCuisine() {
		return _oCuisine;
	}
	public void set_oCuisine(Cuisine _oCuisine) {
		this._oCuisine = _oCuisine;
	}

	// - - - - - METHODS - - - - - //
	/**
	 * Permet au serveur de prendre une commande
	 * @param commande la commande prise par le serveur
	 * @param table, la table (�quivalent client) passant la commande 
	 */
	public void prendCommande(Commande commande,Table table) {
		System.out.println("Serveur Class: prendCommande("+commande+", "+table+")");
		// on v�rifie que la table est bien assign�e au serveur
		if(possedeLaTableAssignee(table)) {
			System.out.println("\tServeur poss�de bien la table dans ces assignations");
			// on ajoute la commande � la table
			getSpecificTable(table).get_aoCcommandes().add(commande);
			// on l'envoie � la cuisine si c'est de la nourriture
			if(commande.is_bNourriture()) {
				_oCuisine.get_aoCommandes().add(commande);
			}
			// on ajoute le chiffre d'affaire
			_dChiffreAffaire +=commande.get_dMontantTotal();
		}
	}
	
	
	/**
	 * Permet de r�cuperer toutes les commandes d'un serveur
	 * @return ArrayList<Commande> la liste des commandes
	 */
	public ArrayList<Commande>  getAllCommandes(){
		ArrayList<Commande> allCommandes = new ArrayList<Commande>();
		// on parcourt toutes les tables du serveur
		for(Table t: get_aoTables()) {
			// pour chaque table on recup�re toutes les commandes
			for(Commande com: t.get_aoCcommandes()) {
				allCommandes.add(com);
			}
		}
		return allCommandes;
	}

	/**
	 * Permet de r�cuper la commande au niveau de la table
	 * @param commande
	 * @return
	 */
	public Commande get_SpecifiqueCommande(Commande commande) {
		Commande comARetourner = null;
		int ind= -1;
		// Pour chaque table du serveur 
		for(Table table: get_aoTables()) {
			// on regarde si on a la commande � cette table
			ind = table.get_aoCcommandes().indexOf(commande);
			if( ind != -1) {
				comARetourner = table.get_aoCcommandes().get(ind);
			}
		}
		return comARetourner;
	}
	
	
	/**
	 * Permet d'�pingler une commande � une date
	 * @param commande
	 * @param dateEpinglage , peut �tre null pour �pingler le jour m�me 
	 * @return la commande �pingl�e
	 */
	public Commande declarerCommandeNonPayee(Commande commande, Date dateEpinglage ) {
		System.out.println("Serveur Class: declarerCommandeNonPayee("+commande+","+dateEpinglage+" )");
		// on r�cup�re la commandes aupr�s de la table du serveur
		Commande com = get_SpecifiqueCommande(commande);
		// Commande com = _aoCcommandes.get(_aoCcommandes.indexOf(commande));
		// on regarde si on a fournit une date (pour faciliter les tests 2 m�thodes ont �t� faites)
		if(dateEpinglage == null){
			com.set_bEpinglee(true);
		}
		else {
			com.set_bEpingleAUneDate(true, dateEpinglage);
		}
		return com;
	}

}
