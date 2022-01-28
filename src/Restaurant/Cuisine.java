package Restaurant;

import java.util.ArrayList;

public class Cuisine {
	// - - - - - Attributes - - - - - // 
	private ArrayList<Commande> _aoCommandes;
	private Restaurant _oRestaurant;


	// - - - - - Constructors - - - - - //
	public Cuisine() {
		super();
		_aoCommandes = new ArrayList<Commande>();
		_oRestaurant = null;
	}
	public Cuisine(Restaurant restaurantDeLaCuisine) {
		super();
		_aoCommandes = new ArrayList<Commande>();
		_oRestaurant = restaurantDeLaCuisine;
	}
	public Cuisine(ArrayList<Commande> _aoCommandes,Restaurant restaurantDeLaCuisine) {
		super();
		this._aoCommandes = _aoCommandes;
		_oRestaurant = restaurantDeLaCuisine;
	}
	// - - - - - GETTERS && SETTERS - - - - - //

	public ArrayList<Commande> get_aoCommandes() {
		return _aoCommandes;
	}
	public void set_aoCommandes(ArrayList<Commande> _aoCommandes) {
		this._aoCommandes = _aoCommandes;
	}
	
	
	
	// - - - - - METHODS - - - - - //
	/**
	 * Permet de refuser une commmande pour faute de stocks
	 * @param commandeRefusee la commande refusée
	 */
	public void refuseCommande(Commande oCommandeRefusee, String sNomDuPlatASupprimer) {
		System.out.println("Cuisine class: refuseCommande(Commande "+oCommandeRefusee+", String "+sNomDuPlatASupprimer+") ");
		// pour chaque contenu de la commande on regarde si c'est bien ce qu'on cherche
		for(Plat plat: oCommandeRefusee.get_contenuCommande()) {
			if(plat.getsNom().equals(sNomDuPlatASupprimer)) {
				// on récupère la carte du restaurant et on le supprime
				_oRestaurant.supprimerPlatFautDeStock(plat);
			}		
		}
	}
}
