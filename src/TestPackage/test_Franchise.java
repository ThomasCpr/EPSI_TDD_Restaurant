package TestPackage;


import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Franchise {
	/**
	 * Test 5) vérifie que CA est égale à somme des CA des restaurants 
	 */
	@Test
	void testChiffreAffaireFranchise() {
		System.out.println("_____TEST_____ : testChiffreAffaireFranchise()");
		// Act
		double dMontantCommande = 1.0;
		int x0, x1, x2, x3, y0, y1, y2, y3;
		x0 = y0 = 0; // X restaurant et Y serveurs
		x1 = y1 = 1;
		x2 = y2 = 2;
		x3 = y3 = 1000;
		
		Franchise f0 = new Franchise(x0, y0);
		Franchise f1 = new Franchise(x1, y1);
		Franchise f2 = new Franchise(x2, y2);
		Franchise f3 = new Franchise(x3, y3);
		
		// Arrange - on ajoute les serveurs aux restaurants
		for(Restaurant r : f0.get_aoRestaurants()) {
			for(int i = 0; i<y0; i++) {
				Serveur s = new Serveur();
				r.recruterServeur(s);
				r.assignerTable(s, r.get_aoTables().get(i));
				s.prendCommande(new Commande(dMontantCommande, false),r.get_aoTables().get(i) );
			}
		}
		
		for(Restaurant r : f1.get_aoRestaurants()) {
			for(int i = 0; i<y1; i++) {
				Serveur s = new Serveur();
				r.recruterServeur(s);
				r.assignerTable(s, r.get_aoTables().get(i));
				s.prendCommande(new Commande(dMontantCommande, false),r.get_aoTables().get(i));
			}
		}
		
		for(Restaurant r : f2.get_aoRestaurants()) {
			for(int i = 0; i<y2; i++) {
				Serveur s = new Serveur();
				r.recruterServeur(s);
				r.assignerTable(s, r.get_aoTables().get(i));
				s.prendCommande(new Commande(dMontantCommande, false),r.get_aoTables().get(i));
			}
		}
		// prend beaucoup trop de temps .. .
//		for(Restaurant r : f3.get_aoRestaurants()) {
//			for(int i = 0; i<y3; i++) {
//				Serveur s = new Serveur();
//				r.recruterServeur(s);
//				r.assignerTable(s, r.get_aoTables().get(i));
//				s.prendCommande(new Commande(dMontantCommande, false),r.get_aoTables().get(i));
//			}
//		} 
		// Assert
		assertEquals(f0.chiffreAffaires(), x0*y0*dMontantCommande);
		assertEquals(f1.chiffreAffaires(), x1*y1*dMontantCommande);
		assertEquals(f2.chiffreAffaires(), x2*y2*dMontantCommande);
//		assertEquals(f3.chiffreAffaires(), x3*y3*dMontantCommande);
	}

}
