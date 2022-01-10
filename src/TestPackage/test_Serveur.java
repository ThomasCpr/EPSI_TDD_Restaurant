package TestPackage;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Serveur;

public class test_Serveur {
	
	/**
	 * Test 1) Les serveurs sont initialis� avec un chiffre d'affaire � 0
	 */
	@Test
	void testChiffreAffaireAZero() {
		System.out.println("_____TEST_____ : testChiffreAffaireAZero()");
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		// Assert 
		assertEquals(0, serv.getdChiffreAffaire());
	}
	
	/**
	 * Test 2) 	serveur prend une cmd alors CA est �gale � la seul commande qu'il prend 
	 */
	@Test
	void testChiffreAffaireEgaleCommande() {
		System.out.println("_____TEST_____ : testChiffreAffaireEgaleCommande()");
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		serv.prendCommande(new Commande(100));
		// Assert 
		assertEquals(100, serv.getdChiffreAffaire());
	}
	/**
	 * Test 3) 	CA d'un serveur est �gale � la somme de ces commandes 
	 */
	@Test
	void testChiffreAffaireEstSommeCommande() {
		System.out.println("_____TEST_____ : testChiffreAffaireEstSommeCommande()");
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		serv.prendCommande(new Commande(100));
		serv.prendCommande(new Commande(100));
		// Assert 
		assertEquals(200, serv.getdChiffreAffaire());
	}
}
