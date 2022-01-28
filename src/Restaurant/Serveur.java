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
	 * @param table, la table (équivalent client) passant la commande 
	 */
	public void prendCommande(Commande commande,Table table) {
		System.out.println("Serveur Class: prendCommande("+commande+", "+table+")");
		// on vérifie que la table est bien assignée au serveur
		if(possedeLaTableAssignee(table)) {
			System.out.println("\tServeur possède bien la table dans ces assignations");
			// on ajoute la commande à la table
			getSpecificTable(table).get_aoCcommandes().add(commande);
			// on l'envoie à la cuisine si c'est de la nourriture
			if(commande.is_bNourriture()) {
				_oCuisine.get_aoCommandes().add(commande);
			}
			// on ajoute le chiffre d'affaire
			_dChiffreAffaire +=commande.get_dMontantTotal();
		}
	}
	
	
	/**
	 * Permet de récuperer toutes les commandes d'un serveur
	 * @return ArrayList<Commande> la liste des commandes
	 */
	public ArrayList<Commande>  getAllCommandes(){
		ArrayList<Commande> allCommandes = new ArrayList<Commande>();
		// on parcourt toutes les tables du serveur
		for(Table t: get_aoTables()) {
			// pour chaque table on recupère toutes les commandes
			for(Commande com: t.get_aoCcommandes()) {
				allCommandes.add(com);
			}
		}
		return allCommandes;
	}

	/**
	 * Permet de récuper la commande au niveau de la table
	 * @param commande
	 * @return
	 */
	public Commande get_SpecifiqueCommande(Commande commande) {
		Commande comARetourner = null;
		int ind= -1;
		// Pour chaque table du serveur 
		for(Table table: get_aoTables()) {
			// on regarde si on a la commande à cette table
			ind = table.get_aoCcommandes().indexOf(commande);
			if( ind != -1) {
				comARetourner = table.get_aoCcommandes().get(ind);
			}
		}
		return comARetourner;
	}
	
	
	/**
	 * Permet d'épingler une commande à une date
	 * @param commande
	 * @param dateEpinglage , peut être null pour épingler le jour même 
	 * @return la commande épinglée
	 */
	public Commande declarerCommandeNonPayee(Commande commande, Date dateEpinglage ) {
		System.out.println("Serveur Class: declarerCommandeNonPayee("+commande+","+dateEpinglage+" )");
		// on récupère la commandes auprès de la table du serveur
		Commande com = get_SpecifiqueCommande(commande);
		// Commande com = _aoCcommandes.get(_aoCcommandes.indexOf(commande));
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
