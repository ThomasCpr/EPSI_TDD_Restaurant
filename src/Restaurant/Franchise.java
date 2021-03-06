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
	 * Permet la cr?ation d'un menu et l'ajoute aux diff?rents resto de la franchise
	 * @param _sName le nom du menu
	 */
	public void createMenu(String _sName,String sNomPlat, double dPrixPlat) {
		Menu menu = new Menu(_sName,sNomPlat, dPrixPlat);
		_aoMenuFranchise.add(menu);
		// ajout au restaurants de la franchise
		for(Restaurant rest : _aoRestaurants) {
			rest.get_aoMenuRestaurant().add(menu);
		}
	}
	
	/**
	 * Permet d'ajouter un plat ? un menu de la franchise et aux menus correspondant dans les
	 * restaurant de la franchise
	 * @param sNomMenu , le nom du menu auquel il faut rajouter le plat
	 * @param sNomPlat , le nom du plat ? ajouter
	 * @param dPrix , le prix du plat ? ajouter
	 */
	public void addPlatToMenu(String sNomMenu,String sNomPlat, double dPrix) { 
		// on regarde si le menu existe aupr?s de la franchise
		if(getMenuByName(sNomMenu) != null) {
			// OUI - on ajoute le plat au menu de la franchise
			getMenuByName(sNomMenu).get_aoPlatsDuMenu().add(new Plat(sNomPlat,dPrix));
		}
		else {
			// NON - on en d?duit que le plat est pour les menus des restaurants donc on ne creer pas le menu
		}
		// pour chaque restaurants
		for(Restaurant rest: _aoRestaurants) {
			// on ajoute le plat au menu du restaurants
			rest.getMenuByName(sNomMenu).get_aoPlatsDuMenu().add(new Plat(sNomPlat, dPrix));
		}
		
	}
	
	/**
	 * Permet la r?cuparation d'un menu par son nom. 
	 * @param sNomDuMenu
	 * @return le menu dont le nom est ?gale au param?tre sNomDuMenu  
	 */
	public Menu getMenuByName(String sNomDuMenu) {
		Menu menuRecherche = null;
		
		for(Menu menu : _aoMenuFranchise) {
			if(menu.get_sName().equals(sNomDuMenu)) {
				menuRecherche = menu;
			}
		}
		return menuRecherche;
	}
	/**
	 * Fixe le prix d'un plat d?j? pr?sent sur la carte de la franchise tout 
	 * @param sNomMenu
	 * @param sNomPlat
	 * @param dNouveauPrix
	 */
	public void setPrixPlat(String sNomMenu,String sNomPlat, double dNouveauPrix) {
		System.out.println("Franchise Class: setPrixPlat(String "+sNomMenu+",String "+sNomPlat+", double "+dNouveauPrix+")");
		// on r?cup?re le menu de la franchise et on le met ? jour au niveau de la franchise
		getMenuByName(sNomMenu).getPlatByName(sNomPlat).setdPrix(dNouveauPrix);
	}
}
