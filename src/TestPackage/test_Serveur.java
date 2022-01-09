package TestPackage;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Serveur;

public class test_Serveur {
	
	/**
	 * Test 1) Les serveurs sont initialisé avec un chiffre d'affaire à 0
	 */
	@Test
	void testChiffreAffaireAZero() {
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		// Assert 
		assertEquals(serv.chiffreAffaire, 0);
	}
	
	/**
	 * Test 2) 	serveur prend une cmd alors CA est égale à la seul commande qu'il prend 
	 */
	@Test
	void testChiffreAffaireEgaleCommande() {
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		serv.prendCommande(100);
		// Assert 
		assertEquals(serv.chiffreAffaire, 100);
	}
	/**
	 * Test 3) 	CA d'un serveur est égale à la somme de ces commandes 
	 */
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
