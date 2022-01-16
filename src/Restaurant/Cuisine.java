package Restaurant;

import java.util.ArrayList;

public class Cuisine {
	// - - - - - Attributes - - - - - // 
	private ArrayList<Commande> _aoCommandes;


	// - - - - - Constructors - - - - - //
	public Cuisine() {
		super();
		_aoCommandes = new ArrayList<Commande>();
	}
	public Cuisine(ArrayList<Commande> _aoCommandes) {
		super();
		this._aoCommandes = _aoCommandes;
	}
	// - - - - - GETTERS && SETTERS - - - - - //

	public ArrayList<Commande> get_aoCommandes() {
		return _aoCommandes;
	}
	public void set_aoCommandes(ArrayList<Commande> _aoCommandes) {
		this._aoCommandes = _aoCommandes;
	}
	
	
	
	// - - - - - Methods - - - - - //
	
	
}
