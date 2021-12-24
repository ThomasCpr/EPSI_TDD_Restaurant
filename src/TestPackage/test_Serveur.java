package TestPackage;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Serveur;

public class test_Serveur {
	@Test
	void testChiffreAffaireAZero() {
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		// Assert 
		assertEquals(serv.chiffreAffaire, 0);
	}
	
	@Test
	void testChiffreAffaireEgaleCommande() {
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		serv.prendCommande(100);
		// Assert 
		assertEquals(serv.chiffreAffaire, 100);
	}
	
	@Test
	void testChiffreAffaireEstSommeCommande() {
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		serv.prendCommande(100);
		serv.prendCommande(100);
		// Assert 
		assertEquals(serv.chiffreAffaire, 200);
	}
}
