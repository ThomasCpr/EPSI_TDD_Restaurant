package Restaurant;


public class Franchise {
	
	// - - - - - Attributes - - - - - // 
	
	private Restaurant[] _restaurants;
	
	// - - - - - Constructors - - - - - //
	
	public Franchise(Restaurant[] restaurants) {
		super();
		this._restaurants = restaurants;
	}
	
	public Franchise(int nombreRestaurants, int nombreServeurs) {
		super();
		_restaurants = new Restaurant[nombreRestaurants];
		for(int i = 0; i < nombreRestaurants; i++) {
			_restaurants[i] = new Restaurant(nombreServeurs);
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

	public Restaurant[] get_restaurants() {
		return _restaurants;
	}

	public void set_restaurants(Restaurant[] _restaurants) {
		this._restaurants = _restaurants;
	}
	
	
}
