package Utilities;

import Restaurant.Cuisine;
import Restaurant.Serveur;

public class ServeurBuilder {

	
	public Serveur Build(Cuisine cuisine) {
		return new Serveur(cuisine);
	}
}
