package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Franchise {
	/**
	 * Test 5) vérifie que CA est égale à somme des CA des restaurants 
	 */
	@Test
	void testChiffreAffaireFranchise() {
		// Act
		double z = 1.0;
		int x0, x1, x2, x3, y0, y1, y2, y3;
		x0 = y0 = 0;
		x1 = y1 = 1;
		x2 = y2 = 2;
		x3 = y3 = 1000;
		
		Franchise f0 = new Franchise(x0);

		Franchise f1 = new Franchise(x1);
		Franchise f2 = new Franchise(x2);
		Franchise f3 = new Franchise(x3);
		
		// Arrange - on ajoute les serveurs aux restaurants
		for(Restaurant r : f0.get_restaurants()) {
			for(int i = 0; i<y0; i++) {
				Serveur s = new Serveur();
				s.prendCommande(z);
				r.get_serveurs().add(s);
			}
		}
		
		for(Restaurant r : f1.get_restaurants()) {
			for(int i = 0; i<y1; i++) {
				Serveur s = new Serveur();
				s.prendCommande(z);
				r.get_serveurs().add(s);
			}
		}
		
		for(Restaurant r : f2.get_restaurants()) {
			for(int i = 0; i<y2; i++) {
				Serveur s = new Serveur();
				s.prendCommande(z);
				r.get_serveurs().add(s);
			}
		}
		
		for(Restaurant r : f3.get_restaurants()) {
			for(int i = 0; i<y3; i++) {
				Serveur s = new Serveur();
				s.prendCommande(z);
				r.get_serveurs().add(s);
			}
		} 
		// Assert
		assertEquals(f0.chiffreAffaires(), x0*y0*z);
		assertEquals(f1.chiffreAffaires(), x1*y1*z);
		assertEquals(f2.chiffreAffaires(), x2*y2*z);
		assertEquals(f3.chiffreAffaires(), x3*y3*z);
		
	}
}
