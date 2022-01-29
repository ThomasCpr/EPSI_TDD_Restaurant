package TestPackage;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_ServeurHamcrest {
	
	/**
	 * Test 1) Les serveurs sont initialisé avec un chiffre d'affaire à 0
	 */
	@Test
	void testChiffreAffaireAZeroHamcrest() {
		System.out.println("_____TEST_____ : testChiffreAffaireAZeroHamcrest()");
		// Act 
		Serveur serv = new Serveur();
		// Arrange 
		// Assert 
		assertThat(0.0, equalTo(serv.getdChiffreAffaire()));
	}
	
	/**
	 * Test 2) 	serveur prend une cmd alors CA est égale à la seul commande qu'il prend 
	 */
	@Test
	void testChiffreAffaireEgaleCommandeHamcrest() {
		System.out.println("_____TEST_____ : testChiffreAffaireEgaleCommandeHamcrest()");
		// Act 
		Restaurant rest = new Restaurant(5);
		Serveur serv = new Serveur(); // ETANT DONNE un nouveau serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		// Arrange 
		serv.prendCommande(new Commande(100,false), rest.get_aoTables().get(0)); // QUAND il prend une nouvelle commande
		// Assert 
		assertThat(100.0, equalTo(serv.getdChiffreAffaire())); // alors son chiffre d'affaire est le montant de celle ci
	}
	/**
	 * Test 3) 	CA d'un serveur est égale à la somme de ces commandes 
	 */
	@Test
	void testChiffreAffaireEstSommeCommandeHamcrest() {
		System.out.println("_____TEST_____ : testChiffreAffaireEstSommeCommandeHamcrest()");
		// Act 
		Restaurant rest = new Restaurant(4); 
		Serveur serv = new Serveur(); // ETANT DONNE un serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		rest.assignerTable(serv, rest.get_aoTables().get(1));
		serv.prendCommande(new Commande(100,false), rest.get_aoTables().get(0));// alors son chiffre d'affaire est la somme des deux commandes
		
		// Arrange 
		serv.prendCommande(new Commande(100,false), rest.get_aoTables().get(0));
		// Assert 
		assertThat(200.0, equalTo(serv.getdChiffreAffaire()));
	}
}
