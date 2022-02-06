package Utilities;

import java.util.Objects;

import Restaurant.Restaurant;

public class RestaurantBuilder {

	public Restaurant Build(int nTable, int nServeur, double dPourcentage) {
		return new Restaurant(nTable, nServeur,Objects.isNull(dPourcentage)?0.0:dPourcentage);
	}
//	
//	public Restaurant 
//	public Restaurant Empty() {
//		return new Restaurant();
//	}
		
}
