package TestPackage;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Serveur {
	
	/**
	 * Test 1) Les serveurs sont initialisé avec un chiffre d'affaire à 0
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
	 * Test 2) 	serveur prend une cmd alors CA est égale à la seul commande qu'il prend 
	 */
	@Test
	void testChiffreAffaireEgaleCommande() {
		System.out.println("_____TEST_____ : testChiffreAffaireEgaleCommande()");
		// Act 
		Restaurant rest = new Restaurant(5);
		Serveur serv = new Serveur(); // ETANT DONNE un nouveau serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		// Arrange 
		serv.prendCommande(new Commande(100,true, null), rest.get_aoTables().get(0)); // QUAND il prend une nouvelle commande
		// Assert 
		assertEquals(100, serv.getdChiffreAffaire()); // alors son chiffre d'affaire est le montant de celle ci
	}
	/**
	 * Test 3) 	CA d'un serveur est égale à la somme de ces commandes 
	 */
	@Test
	void testChiffreAffaireEstSommeCommande() {
		System.out.println("_____TEST_____ : testChiffreAffaireEstSommeCommande()");
		// Act 
		Restaurant rest = new Restaurant(4); 
		Serveur serv = new Serveur(); // ETANT DONNE un serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		rest.assignerTable(serv, rest.get_aoTables().get(1));
		serv.prendCommande(new Commande(100,true, null), rest.get_aoTables().get(0));// alors son chiffre d'affaire est la somme des deux commandes
		
		// Arrange 
		serv.prendCommande(new Commande(100,true, null), rest.get_aoTables().get(0));
		// Assert 
		assertEquals(200, serv.getdChiffreAffaire());
	}
}
