package Restaurant;

import java.util.ArrayList;

public class Franchise {
	
	// - - - - - Attributes - - - - - // 
	
	private ArrayList<Restaurant> _restaurants;
	
	// - - - - - Constructors - - - - - //
	/**
	 * Constructeur avec une liste de restaurant
	 * @param restaurants
	 */
	public Franchise(ArrayList<Restaurant> restaurants) {
		super();
		this._restaurants = restaurants;
	}
	/**
	 * Constructeur avec un nombre de restaurant
	 * @param nombreRestaurants
	 */
	public Franchise(int nombreRestaurants) {
		super();
		_restaurants = new ArrayList<Restaurant>();
		for(int i = 0; i < nombreRestaurants; i++) {
			_restaurants.add(new Restaurant());
		}
	}
	
	
	// - - - - - Methods - - - - - //
	
	
	/**
	 * chiffre d'affaire de la franchise
	 * @return som (double) la somme des chiffres d'affaires des restaurants
	 */
	public double chiffreAffaires() {
		double som = 0.0;
		for(Restaurant resto : _restaurants) {
			som+=resto.chiffreAffaire();
		}
		return som;
	}
	public ArrayList<Restaurant> get_restaurants() {
		return _restaurants;
	}
	public void set_restaurants(ArrayList<Restaurant> _restaurants) {
		this._restaurants = _restaurants;
	}

	
}
