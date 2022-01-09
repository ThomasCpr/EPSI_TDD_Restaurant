package TestPackage;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Serveur;

public class test_Serveur {
	
	/**
	 * Test 1) Les serveurs sont initialis� avec un chiffre d'affaire � 0
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
	 * Test 2) 	serveur prend une cmd alors CA est �gale � la seul commande qu'il prend 
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
	 * Test 3) 	CA d'un serveur est �gale � la somme de ces commandes 
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
