package Utilities;

import java.util.ArrayList;

import Restaurant.Restaurant;

public class RestaurantGenerator {
	
	
	public ArrayList<Restaurant> Generate(int nombreDeRestaurant, int nbTableParRestaurant, int nombreServeur, double dPourcentage){
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		for(int i = 0; i<nombreDeRestaurant; i++) {
			restaurants.add(new RestaurantBuilder().Build(nbTableParRestaurant, nombreServeur, dPourcentage));
		}
		
		return restaurants;
	}

}
