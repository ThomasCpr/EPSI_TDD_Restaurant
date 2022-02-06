package Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utilities.RestaurantGenerator;

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

	public Franchise(int nombreDeRestaurant, int nbTableParRestaurant, int nombreServeur, double dPourcentage) {
		_aoMenuFranchise = new ArrayList<Menu>();
		_aoRestaurants = new RestaurantGenerator().Generate(nombreDeRestaurant, nbTableParRestaurant, nombreServeur, dPourcentage);
	}

	// - - - - - GETTERS && SETTERS - - - - - //
	public ArrayList<Restaurant> get_aoRestaurants() {
		return _aoRestaurants;
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
	public void createMenu(String _sName,String sNomPlat, double dPrixPlat) {
		System.out.println("DANS CREATE MENUE");
		Menu menu = new Menu(_sName,sNomPlat, dPrixPlat);
		_aoMenuFranchise.add(menu);
		// ajout au restaurants de la franchise
		for(Restaurant rest : _aoRestaurants) {
			System.out.println(rest);
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
		if(getMenuByName(sNomMenu) != null) {
			// OUI - on ajoute le plat au menu de la franchise
			getMenuByName(sNomMenu).get_aoPlatsDuMenu().add(new Plat(sNomPlat,dPrix));
		}
		else {
			// NON - on en déduit que le plat est pour les menus des restaurants donc on ne creer pas le menu
		}
		// pour chaque restaurants
		System.out.println("Ajout du plat ("+sNomPlat+","+ dPrix+") la franchise aux restaurants.");
		for(Restaurant rest: _aoRestaurants) {
			// Si le menu existe on y ajoute le plat
			if(rest.getMenuByName(sNomMenu) != null) {
				rest.getMenuByName(sNomMenu).get_aoPlatsDuMenu().add(new Plat(sNomPlat, dPrix));
			}
			else {
				// sinon on crée le menu
				rest.createMenu(sNomMenu, sNomPlat, dPrix);
			}
			// on ajoute le plat au menu du restaurants
			
			
			
		}
		
	}
	
	/**
	 * Permet la récuparation d'un menu par son nom. 
	 * @param sNomDuMenu
	 * @return le menu dont le nom est égale au paramètre sNomDuMenu  
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
	 * Fixe le prix d'un plat déjà présent sur la carte de la franchise tout 
	 * @param sNomMenu
	 * @param sNomPlat
	 * @param dNouveauPrix
	 */
	public void setPrixPlat(String sNomMenu,String sNomPlat, double dNouveauPrix) {
		System.out.println("Franchise Class: setPrixPlat(String "+sNomMenu+",String "+sNomPlat+", double "+dNouveauPrix+")");
		// on récupère le menu de la franchise et on le met à jour au niveau de la franchise
		getMenuByName(sNomMenu).getPlatByName(sNomPlat).setdPrix(dNouveauPrix);
	}
}
