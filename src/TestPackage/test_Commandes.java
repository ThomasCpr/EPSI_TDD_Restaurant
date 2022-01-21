package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Menu;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Table;

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
	@Test 
	void testEpinglageCommande() {
		System.out.println("\n_____TEST_____ : testEpinglageCommande()");
		// Act
		Restaurant rest = new Restaurant(3);
		Serveur serv = new Serveur(); // etant donne un serveur
		Commande c = new Commande(10, false, null);
		rest.assignerTable(serv, rest.get_aoTables().get(1));
		serv.prendCommande(c,rest.get_aoTables().get(1)); // ayant pris une commande
		// Arrange 
		Commande commandeEpinglee = serv.declarerCommandeNonPayee(c, new Date()); // quand il la d�clare comme non-pay�e
		// Assert
		assertEquals(true, commandeEpinglee.is_bEpinglee()); // alors la cmd est marqu�e comme �pingl�e
	}

	/**
	 * Test 11) Commande �pingl�e depuis au moins 15 jours alors marqu�e comme � transmettre gendarmerie
	 */
	@Test
	void testEpinglageDepuisQuinzeJour() {
		System.out.println("\n_____TEST_____ : testEpinglageDepuisQuinzeJour()");
		// Act
		Restaurant rest = new Restaurant(3);
		Serveur serv = new Serveur(); // etant donne un serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		Commande com = new Commande(10, false, null);
		serv.prendCommande(com, rest.get_aoTables().get(0)); // ayant pris une commande
		// Arrange
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // qu'il a d�clar� comme non-pay�e il y a au moins 15 jours
		// Assert
		assertEquals(true, com.bATransmettre()); // alors cette commande est marqu�e comme � transmettre
	}
	
	/**
	 * Test 12 ) si cmd est � transmettre � gendarmerie alors figure dans la listes des commandes en question 
	 */
	@Test
	void testcommandeATransmettreEstDansLaListe() {
		System.out.println("\n_____TEST_____ : testcommandeATransmettreEstDansLaListe()"); 
		// Act 
		Restaurant rest = new Restaurant(3); // soit un restaurant
		Serveur serv = new Serveur();
		Table tableQuiNePaieraPasSacommande = rest.get_aoTables().get(0) ;
		rest.recruterServeur(serv); //  avec un serveur
		rest.assignerTable(serv, tableQuiNePaieraPasSacommande);
		Commande com = new Commande(10, false, null); // et une commande
		
		// Arrange
		serv.prendCommande(com, tableQuiNePaieraPasSacommande); 
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // d�clar�e � transmettre
		rest.recupererCommandesATransmettre(); 
		
		// Assert
		assertEquals(true, rest.get_aoCommandeATransmettre().contains(com));// ALORS quand on consulte la lsite des cmd � transmettre elle y figure
	}
	
	/**
	 * Test 13) v�rifie que les commandes transmisent n'apparaissent plus dans les commandes � transmettre
	 */
	@Test void testPresenceCommandeTransmiseApresTransmission() {
		System.out.println("\n_____TEST_____ : testPresenceCommandeTransmiseApresTransmission()");
		// Act 
		Restaurant rest = new Restaurant(5); // etant donn� un restaurant
		Serveur serv = new Serveur();
		Commande com = new Commande(100, true, null);
		rest.recruterServeur(serv); 
		rest.assignerTable(serv, rest.get_aoTables().get(2));
		serv.prendCommande(com, rest.get_aoTables().get(2)); // et un serveur ayant pris une commande
		serv.declarerCommandeNonPayee(com , dDateDepassee()); // non pay�e donc �pingl�e
		
		// Arrange
		rest.recupererCommandesATransmettre(); // une fois que les commandes � transmettre sont r�cup�r�es
		rest.transmettreCommandeGendarmerie(); // et qu'elles sont transmises
		// Assert 
		assertEquals(false, rest.get_aoCommandeATransmettre().contains(com)); // elle ne figure plus dans la liste des cmd � transmettre
	}

	/**
	 * Test 19) Lors de la prise de commande dans un restaurant, celle-ci apparait dans la liste des t�ches de la cuisine
	 */
	@Test
	void testAjoutDeTacheALaCuisine() {
		System.out.println("\n_____TEST_____ : testAjoutDeTacheALaCuisine()");
		// Act 
		Serveur serv = new Serveur();
		Restaurant rest = new Restaurant(3); // etant donn� un serveur
		rest.recruterServeur(serv); // dans un restaurant
		rest.assignerTable(serv,rest.get_aoTables().get(2) );

		// Arrange
		Commande com = new Commande(100,true, new Menu("Menu Burger", Map.of("Burger vegan lol", 200.0) ));   
		serv.prendCommande(com, rest.get_aoTables().get(2));// QUAND il prend une commande de nourriture
		
		// Assert
		assertEquals(true, rest.get_oCuisine().get_aoCommandes().contains(com)); // ALROS cette commande appara�t dans la liste des t�ches de la cuisines de ce restaurant
	}
	
	
	/**
	 * Test 20) Une commande ne concernant pas de nourriture n'appara�t pas dans les t�ches de la cuisine
	 */
	@Test 
	void testPriseDeCommandeSansAjoutTacheCuisine() {
		System.out.println("\n_____TEST_____ : testPriseDeCommandeSansAjoutTacheCuisine()");
		// Act 
		Serveur serv = new Serveur(); // ETANT donn� un serveur 
		Restaurant rest = new Restaurant(3); // dans un restaurant 
		rest.recruterServeur(serv);
		Table t =  rest.get_aoTables().get(1);
		rest.assignerTable(serv,t);
		// Arrange 
		Commande com = new Commande(20, false, null); 
		serv.prendCommande(com,t ); // QUAND il prend une commande de boissons
		
		// Assert
		assertEquals(false, rest.get_oCuisine().get_aoCommandes().contains(com)); // ALORS cette commande n'apparait pas dans la liste de t�che
	}
	/**
	 * Test 21) Permet de v�rifier que le serveur prenant la commande soit bien en charge de la table
	 */
	@Test
	void testCommandeEstBienAUneTableDuServeur() {
		System.out.println("\n_____TEST_____ : testCommandeEstBienAUneTableDuServeur()");
		// Act 
		
		
		// Arrange 
		
		// Assert
		
		
	}
	
	/**
	 * Test 22) Permet de v�rifier que le total d'une table est bien la somme des commandes pass�es
	 */
	@Test
	void testTotalCommandeTable() {
		System.out.println("\n_____TEST_____ : testTotalCommandeTable()");
		// ACT 
		Serveur serv= new Serveur(); // ETANT donn� un serveur
		Restaurant rest = new Restaurant(5); // dans un restaurant
		rest.recruterServeur(serv);
		Table tableQuiCommande = rest.get_aoTables().get(2);
		rest.assignerTable(serv, tableQuiCommande);
		Commande commande1 = new Commande(100, false); 
		serv.prendCommande(commande1, tableQuiCommande);// ayant pris une commande aupr�s d'un client
		
		// Arrange
		Commande commande2 = new Commande(10, false); // QUand il prend une autre commande de la part du m�me client
		serv.prendCommande(commande2, tableQuiCommande);
		
		// Assert
		assertEquals(110,  tableQuiCommande.get_TotalTable()); //  ALROS le total de la command est la somme des deux commandes
	}
	
}
