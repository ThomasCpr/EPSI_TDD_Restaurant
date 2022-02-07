package TestPackage;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Database.DBConnection;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_ServeurBDD {
	
	@BeforeEach
	public void init() {
		DBConnection.updateCA(0,1);
	}
	
	@AfterAll
	public static void close() {
		DBConnection.updateCA(0,1);
	}
	/**
	 * Test 1) Les serveurs sont initialisé avec un chiffre d'affaire à 0
	 */
	@Test
	void testChiffreAffaireAZero() {
		System.out.println("_____TEST_____ : testChiffreAffaireAZero()");
		// Act 
		String name = DBConnection.readName(1);
		int ca = DBConnection.readCA(name);
		Serveur serv = new Serveur(name, ca, null);
		// Arrange 
		// Assert 
		assertEquals(0, serv.getdChiffreAffaire());
		assertEquals(0, DBConnection.readCA(name));
	}
	
	/**
	 * Test 2) 	serveur prend une cmd alors CA est égale à la seul commande qu'il prend 
	 */
	@Test
	void testChiffreAffaireEgaleCommande() {
		System.out.println("_____TEST_____ : testChiffreAffaireEgaleCommande()");
		// Act 
		String name = DBConnection.readName(1);
		int ca = DBConnection.readCA(name);
		
		Restaurant rest = new Restaurant(5);
		Serveur serv = new Serveur(name, ca, rest.get_oCuisine()); // ETANT DONNE un nouveau serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		// Arrange 
		serv.prendCommande(new Commande(100,false), rest.get_aoTables().get(0)); // QUAND il prend une nouvelle commande
		// Assert 
		DBConnection.updateCA(serv.getdChiffreAffaire(),1);
		assertEquals(100, DBConnection.readCA(name));
		assertEquals(100, serv.getdChiffreAffaire()); // alors son chiffre d'affaire est le montant de celle ci
	}
}