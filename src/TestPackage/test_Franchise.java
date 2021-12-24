package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Franchise {
	@Test
	void testChiffreAffaireFranchise() {
		// Act
		double z = 1.0;
		int x0, x1, x2, x3, y0, y1, y2, y3;
		x0 = y0 = 0;
		x1 = y1 = 1;
		x2 = y2 = 2;
		x3 = y3 = 1000;
		
		Franchise f0 = new Franchise(x0,y0);
		Franchise f1 = new Franchise(x1,y1);
		Franchise f2 = new Franchise(x2,y2);
		Franchise f3 = new Franchise(x3,y3);
		
		// Arrange 
		for(Restaurant r : f0.get_restaurants()) {
			for(Serveur s : r.get_serveurs()) {
				s.prendCommande(z);
			}
		}
		
		for(Restaurant r : f1.get_restaurants()) {
			for(Serveur s : r.get_serveurs()) {
				s.prendCommande(z);
			}
		}
		
		for(Restaurant r : f2.get_restaurants()) {
			for(Serveur s : r.get_serveurs()) {
				s.prendCommande(z);
			}
		}
		
		for(Restaurant r : f3.get_restaurants()) {
			for(Serveur s : r.get_serveurs()) {
				s.prendCommande(z);
			}
		}
		// Assert
		assertEquals(f0.chiffreAffaires(), x0*y0*z);
		assertEquals(f1.chiffreAffaires(), x1*y1*z);
		assertEquals(f2.chiffreAffaires(), x2*y2*z);
		assertEquals(f3.chiffreAffaires(), x3*y3*z);
		
	}
}
