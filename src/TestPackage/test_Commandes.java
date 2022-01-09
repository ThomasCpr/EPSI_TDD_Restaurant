package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;

public class test_Commandes {
		
	/**
	 * Permet de toujours avoir une date d�pass�e de 15 jours
	 * @return Date , la date du jour - 1 mois 
	 */
	private Date dDateDepassee() {
		Date dateDepassee = new Date(); // date d'aujourdui
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateDepassee);
		cal.add(GregorianCalendar.MONTH, -1); // � laquelle on enl�ve 1 mois
		return cal.getTime();
	}
	
		/**
	 * Test 10) serveur d�clare une commande non pay�e alors commande est �pingl�e
	 */
	@Test void testEpinglageCommande() {
		System.out.println("_____TEST_____ : testEpinglageCommande()");
		// Act
		Serveur serv = new Serveur(); // etant donne un serveur
		Commande c = new Commande(10);
		serv.prendCommande(c); // ayant pris une commande
		// Arrange 
		Commande commandeEpinglee = serv.declarerCommandeNonPayee(c, new Date()); // quand il la d�clare comme non-pay�e
		// Assert
		assertEquals(true, commandeEpinglee.is_bEpinglee()); // alors la cmd est marqu�e comme �pingl�e
	}

	/**
	 * Test 11) Commande �pingl�e depuis au moins 15 jours alors marqu�e comme � transmettre gendarmerie
	 */
	@Test void testEpinglageDepuisQuinzeJour() {
		System.out.println("_____TEST_____ : testEpinglageDepuisQuinzeJour()");
		// Act
		Serveur serv = new Serveur(); // etant donne un serveur
		Commande com = new Commande(10);
		serv.prendCommande(com); // ayant pris une commande
		// Arrange
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // qu'il a d�clar� comme non-pay�e il y a au moins 15 jours
		// Assert
		assertEquals(true, com.bATransmettre()); // alors cette commande est marqu�e comme � transmettre
	}
	
	/**
	 * Test 12 ) si cmd est � transmettre � gendarmerie alors figure dans la listes des commandes en question 
	 */
	@Test void testcommandeATransmettreEstDansLaListe() {
		System.out.println("_____TEST_____ : testcommandeATransmettreEstDansLaListe()"); 
		// Act 
		Restaurant rest = new Restaurant(3); // soit un restaurant
		Serveur serv = new Serveur();
		rest.get_serveurs().add(serv); //  avec un serveur
		Commande com = new Commande(10); // et une commande
		// Arrange
		serv.prendCommande(com); 
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // d�clar�e � transmettre
		rest.recupererCommandesATransmettre(); 
		// Assert
		assertEquals(true, rest.get_commandeATransmettre().contains(com));// quand on consulte la lsite des cmd � transmettre elle y figure
	}
	
	/**
	 * Test 13) v�rifie que les commandes transmisent n'apparaissent plus dans les commandes � transmettre
	 */
	@Test void testPresenceCommandeTransmiseApresTransmission() {
		System.out.println("_____TEST_____ : testPresenceCommandeTransmiseApresTransmission()");
		// Act 
		Restaurant rest = new Restaurant(5); // etant donn� un restaurant
		Serveur serv = new Serveur();
		Commande com = new Commande(100);
		serv.prendCommande(com); // et un serveur ayant pris une commande
		serv.declarerCommandeNonPayee(com , dDateDepassee()); // non pay�e donc �pingl�e
		// Arrange
		rest.recupererCommandesATransmettre(); // une fois que les commandes � transmettre sont r�cup�r�es
		rest.transmettreCommandeGendarmerie(); // et qu'elles sont transmises
		// Assert 
		assertEquals(false, rest.get_commandeATransmettre().contains(com)); // elle ne figure plus dans la liste des cmd � transmettre
	}
}
