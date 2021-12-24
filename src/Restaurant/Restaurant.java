package Restaurant;

import java.util.Arrays;

public class Restaurant {
	
	// - - - - - Attributes - - - - - //
	private Serveur[] _serveurs;
	private Table[] _tables;
	// - - - - - Constructors - - - - - //
	
	public Restaurant() {
		
	}
	
	public Restaurant(int nombreServeur) {
		_serveurs = new Serveur[nombreServeur];
		for(int i = 0; i < nombreServeur; i++) {
			_serveurs[i] = new Serveur();
		}
	}
	
	public Restaurant(int nombreServeur, int nbTable) {
		_serveurs = new Serveur[nombreServeur]; // y 'avait pas un moyen de raccourcis ce code dupliqué ? 
		for(int i = 0; i < nombreServeur; i++) {
			_serveurs[i] = new Serveur();
		}
		
		_tables = new Table[nbTable];
		for(int j = 0; j<nbTable; j++) {
			_tables[j] = new Table();
		}
	}

	
	// - - - - - Methods - - - - - //
	/**
	 * chiffre d'affaire deu restaurant
	 * @return som (double) la somme des chiffres d'affaires des serveurs
	 */
	public double chiffreAffaire() {
		double som = 0.0;
		for(Serveur serv : _serveurs) {
			som+=serv.getChiffreAffaire();
		}
		
		return som;
	}

	public Serveur[] get_serveurs() {
		return _serveurs;
	}

	public void set_serveurs(Serveur[] _serveurs) {
		this._serveurs = _serveurs;
	}

	@Override
	public String toString() {
		return "Restaurant [_serveurs=" + Arrays.toString(_serveurs) + ", chiffreAffaire()=" + chiffreAffaire()
				+ ", get_serveurs()=" + Arrays.toString(get_serveurs()) + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}


