package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Restaurant {
	
		@Test
		void testChiffreAffaireRestaurant() {
			// Act
			
			Restaurant resto_1 = new Restaurant(0);
			Restaurant resto_2 = new Restaurant(1);
			Restaurant resto_3 = new Restaurant(2);
			Restaurant resto_4 = new Restaurant(100);
			
			// Arrange 
			resto_2.get_serveurs()[0].prendCommande(1.0);
			resto_3.get_serveurs()[0].prendCommande(1.0);
			resto_3.get_serveurs()[1].prendCommande(1.0);
			
			for(Serveur s : resto_4.get_serveurs()) {
				s.prendCommande(1.0);
			}
			
			// Assert
			assertEquals(resto_1.chiffreAffaire(), 0.0); // vaut-il mieux faire des valeurs dure ou var ? 
			assertEquals(resto_2.chiffreAffaire(), 1.0);
			assertEquals(resto_3.chiffreAffaire(), 2.0);
			assertEquals(resto_4.chiffreAffaire(), 100.0);
		}
		
		@Test
		void testAffectationTableMaitreHotel() {
			// Act 
			Restaurant r = new Restaurant(3);
			// Arrange 
			
			// Assert

		}

		
		
		
		// Act 
		// Arrange 
		// Assert
}
