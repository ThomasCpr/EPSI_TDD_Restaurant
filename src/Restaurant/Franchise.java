package Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Franchise {
	
	// - - - - - Attributes - - - - - // 
	
	private ArrayList<Restaurant> _aoRestaurants;
	private ArrayList<Menu> _aoMenuFranchise;
	
	
	// - - - - - Constructors - - - - - //
	
	/**
	 * Contructeur vide
	 */
	public Franchise() {
		super();
		_aoRestaurants = new ArrayList<Restaurant>();
		_aoMenuFranchise = new ArrayList<Menu>();
	}
	/**
	 * Constructeur avec une liste de restaurant
	 * @param restaurants
	 */
	public Franchise(ArrayList<Restaurant> restaurants) {
		super();
		this._aoRestaurants = restaurants;
		_aoMenuFranchise = new ArrayList<Menu>();
	}
	/**
	 * Constructeur avec un nombre de restaurant
	 * @param nombreRestaurants
	 */
	public Franchise(int nombreRestaurants) {
		super();
		_aoRestaurants = new ArrayList<Restaurant>();
		_aoMenuFranchise = new ArrayList<Menu>();
		for(int i = 0; i < nombreRestaurants; i++) {
			_aoRestaurants.add(new Restaurant());
		}
	}
	
	/**
	 * Constructeur avec un nombre de restaurant et leur nombre de table pour simplifier le test franchise
	 * @param nombreRestaurants le nombre de restaurant de la franchise
	 * @param nbTableParRestaurant le nombre de tables par restaurant
	 */
	public Franchise(int nombreRestaurants, int nbTableParRestaurant) {
		super();
		_aoRestaurants = new ArrayList<Restaurant>();
		_aoMenuFranchise = new ArrayList<Menu>();
		for(int i = 0; i < nombreRestaurants; i++) {
			_aoRestaurants.add(new Restaurant(nbTableParRestaurant));
		}
	}
	
	// - - - - - GETTERS && SETTERS - - - - - //
	public ArrayList<Restaurant> get_aoRestaurants() {
		return _aoRestaurants;
	}
	public void set_aoRestaurants(ArrayList<Restaurant> _aoRestaurants) {
		this._aoRestaurants = _aoRestaurants;
	}
	public ArrayList<Menu> get_aoMenuFranchise() {
		return _aoMenuFranchise;
	}
	public void set_aoMenuFranchise(ArrayList<Menu> _aoMenuFranchise) {
		this._aoMenuFranchise = _aoMenuFranchise;
	}
	
	// - - - - - Methods - - - - - //
	
	/**
	 * chiffre d'affaire de la franchise
	 * @return som (double) la somme des chiffres d'affaires des restaurants
	 */
	public double chiffreAffaires() {
		double som = 0.0;
		for(Restaurant resto : _aoRestaurants) {
			som+=resto.chiffreAffaire();
		}
		return som;
	}
	
	/**
	 * Permet la création d'un menu et l'ajoute aux différents resto de la franchise
	 * @param _sName le nom du menu
	 */
	public void createMenu(String _sName) {
		Menu menu = new Menu(_sName);
		_aoMenuFranchise.add(menu);
		// ajout au restaurants de la franchise
		for(Restaurant rest : _aoRestaurants) {
			rest.get_aoMenuRestaurant().add(menu);
		}
	}
	
	/**
	 * Permet d'ajouter un plat à un menu de la franchise et aux menus correspondant dans les
	 * restaurant de la franchise
	 * @param sNomMenu , le nom du menu auquel il faut rajouter le plat
	 * @param sNomPlat , le nom du plat à ajouter
	 * @param dPrix , le prix du plat à ajouter
	 */
	public void addPlatToMenu(String sNomMenu,String sNomPlat, double dPrix) { 
		// on regarde si le menu existe auprès de la franchise
		if(get_iIndexMenuByName(sNomMenu) !=-1) {
			// OUI - on ajoute le plat au menu de la franchise
			_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().put(sNomPlat,dPrix);
		}
		else {
			// NON - on en déduit que le plat est pour les menus des restaurants donc on ne creer pas le menu
		}

		// pour chaque restaurants
		for(Restaurant rest: _aoRestaurants) {
			// on ajoute le plat au menu du restaurants
			rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().put(sNomPlat, dPrix);
		}
		
	}
	
	
	/**
	 * Renvoi le menu dont le nom est égale au paramètre sNameDuMenu (ici le nom d'un menu fait office de pseudo identifiant)
	 * @param sNomDuMenu
	 * @return l'index du menu, clairement de grosse faille sur cette méthode 
	 */
	public int get_iIndexMenuByName(String sNomDuMenu) {
		int indexOf = -1; // par défaut non trouvé
		for(Menu menu : _aoMenuFranchise) {
			if(menu.get_sName().equals(sNomDuMenu)) {
				indexOf = _aoMenuFranchise.indexOf(menu);
			}
		}
		return indexOf;
	}
	
	public void setPrixPlat(String sNomMenu,String sNomPlat, double dNouveauPrix) {
		System.out.println("Franchise Class: setPrixPlat(String "+sNomMenu+",String "+sNomPlat+", double "+dNouveauPrix+")");
		// on récupère le menu de la franchise et on le met à jour au niveau de la franchise
		System.out.println("\tAvant modification: "+_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu());
		_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().replace(sNomPlat, dNouveauPrix);
//		_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().put(sNomPlat, dNouveauPrix);
		System.out.println("\tAprès modification: "+_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu());
		// on le mets à jour au niveau des restaurants
//		for(Restaurant rest: _aoRestaurants) {
//			System.out.println("\tAvant modification: "+_aoMenuFranchise.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu());
//			rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().replace(sNomPlat, dNouveauPrix);
//			System.out.println("\tAprès modification: "+rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu());
//		}
	}
}
