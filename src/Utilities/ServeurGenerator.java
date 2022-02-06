package Utilities;

import java.util.ArrayList;

import Restaurant.Cuisine;
import Restaurant.Serveur;

public class ServeurGenerator {

	
	
	public ArrayList<Serveur> Generate(int dNombreServeurs, Cuisine cuisine){
		ArrayList<Serveur> serveurs = new ArrayList<Serveur>();
		if(cuisine != null) {
			for( int i = 0; i< dNombreServeurs; i++) {
				serveurs .add(new ServeurBuilder().Build(cuisine));
			}
		}
		else {
			serveurs .add(new ServeurBuilder().Build(null));	
		}
			
		return serveurs;
	}
	 
}
