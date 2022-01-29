package TestPackage;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Menu;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Table;

public class test_CommandesHamcrest {
		
	/**
	 * Permet de toujours avoir une date dépassée de 15 jours
	 * @return Date , la date du jour - 1 mois 
	 */
	private Date dDateDepassee() {
		Date dateDepassee = new Date(); // date d'aujourdui
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateDepassee);
		cal.add(GregorianCalendar.MONTH, -1); // à laquelle on enlève 1 mois
		return cal.getTime();
	}
	
		/**
	 * Test 10) serveur déclare une commande non payée alors commande est épinglée
	 */
	@Test 
	void testEpinglageCommandeHamcrest() {
		System.out.println("_____TEST_____ : testEpinglageCommandeHamcrest()");
		// Act
		Restaurant rest = new Restaurant(3);
		Serveur serv = new Serveur(); // etant donne un serveur
		Commande c = new Commande();
		rest.assignerTable(serv, rest.get_aoTables().get(1));
		serv.prendCommande(c,rest.get_aoTables().get(1)); // ayant pris une commande
		// Arrange 
		Commande commandeEpinglee = serv.declarerCommandeNonPayee(c, new Date()); // quand il la déclare comme non-payée
		// Assert
		assertThat(true, is(commandeEpinglee.is_bEpinglee())); // alors la cmd est marquée comme épinglée
	}

	/**
	 * Test 11) Commande épinglée depuis au moins 15 jours alors marquée comme à transmettre gendarmerie
	 */
	@Test
	void testEpinglageDepuisQuinzeJourHamcrest() {
		System.out.println("_____TEST_____ : testEpinglageDepuisQuinzeJourHamcrest()");
		// Act
		Restaurant rest = new Restaurant(3);
		Serveur serv = new Serveur(); // etant donne un serveur
		rest.recruterServeur(serv);
		rest.assignerTable(serv, rest.get_aoTables().get(0));
		Commande com = new Commande();
		serv.prendCommande(com, rest.get_aoTables().get(0)); // ayant pris une commande
		// Arrange
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // qu'il a déclaré comme non-payée il y a au moins 15 jours
		// Assert
		assertThat(true, is(com.bATransmettre())); // alors cette commande est marquée comme à transmettre
	}
	
	/**
	 * Test 12 ) si cmd est à transmettre à gendarmerie alors figure dans la listes des commandes en question 
	 */
	@Test
	void testcommandeATransmettreEstDansLaListeHamcrest() {
		System.out.println("_____TEST_____ : testcommandeATransmettreEstDansLaListeHamcrest()"); 
		// Act 
		Restaurant rest = new Restaurant(3); // soit un restaurant
		Serveur serv = new Serveur();
		Table tableQuiNePaieraPasSacommande = rest.get_aoTables().get(0) ;
		rest.recruterServeur(serv); //  avec un serveur
		rest.assignerTable(serv, tableQuiNePaieraPasSacommande);
		Commande com = new Commande(); // et une commande
		
		// Arrange
		serv.prendCommande(com, tableQuiNePaieraPasSacommande); 
		serv.declarerCommandeNonPayee(com, dDateDepassee()); // déclarée à transmettre
		rest.recupererCommandesATransmettre(); 
		
		// Assert
		assertThat(true, is(rest.get_aoCommandeATransmettre().contains(com))); // ALORS quand on consulte la lsite des cmd à transmettre elle y figure
	}
	
	/**
	 * Test 13) vérifie que les commandes transmisent n'apparaissent plus dans les commandes à transmettre
	 */
	@Test void testPresenceCommandeTransmiseApresTransmissionHamcrest() {
		System.out.println("_____TEST_____ : testPresenceCommandeTransmiseApresTransmissionHamcrest()");
		// Act 
		Restaurant rest = new Restaurant(5); // etant donné un restaurant
		Serveur serv = new Serveur();
		Commande com = new Commande();
		rest.recruterServeur(serv); 
		rest.assignerTable(serv, rest.get_aoTables().get(2));
		serv.prendCommande(com, rest.get_aoTables().get(2)); // et un serveur ayant pris une commande
		serv.declarerCommandeNonPayee(com , dDateDepassee()); // non payée donc épinglée
		
		// Arrange
		rest.recupererCommandesATransmettre(); // une fois que les commandes à transmettre sont récupérées
		rest.transmettreCommandeGendarmerie(); // et qu'elles sont transmises
		// Assert 
		assertThat(false, is(rest.get_aoCommandeATransmettre().contains(com)));  // elle ne figure plus dans la liste des cmd à transmettre
	}

	/**
	 * Test 19) Lors de la prise de commande dans un restaurant, celle-ci apparait dans la liste des tâches de la cuisine
	 */
	@Test
	void testAjoutDeTacheALaCuisineHamcrest() {
		System.out.println("_____TEST_____ : testAjoutDeTacheALaCuisineHamcrest()");
		// Act 
		Serveur serv = new Serveur();
		Restaurant rest = new Restaurant(3); // etant donné un serveur
		rest.recruterServeur(serv); // dans un restaurant
		rest.assignerTable(serv,rest.get_aoTables().get(2) );

		// Arrange
		rest.createMenu("Menu Burger","Burger vegan", 200.0);
		Commande com = new Commande(rest.get_aoMenuRestaurant().get(0).getPlatByName("Burger vegan"));   
		serv.prendCommande(com, rest.get_aoTables().get(2));// QUAND il prend une commande de nourriture
		
		// Assert
		assertThat(true, is(rest.get_oCuisine().get_aoCommandes().contains(com)));   // ALROS cette commande apparaît dans la liste des tâches de la cuisines de ce restaurant
	}
	
	
	/**
	 * Test 20) Une commande ne concernant pas de nourriture n'apparaît pas dans les tâches de la cuisine
	 */
	@Test 
	void testPriseDeCommandeSansAjoutTacheCuisineHamcrest() {
		System.out.println("_____TEST_____ : testPriseDeCommandeSansAjoutTacheCuisineHamcrest()");
		// Act 
		Serveur serv = new Serveur(); // ETANT donné un serveur 
		Restaurant rest = new Restaurant(3); // dans un restaurant 
		rest.recruterServeur(serv);
		Table t =  rest.get_aoTables().get(1);
		rest.assignerTable(serv,t);
		// Arrange 
		Commande com = new Commande(20, false); 
		serv.prendCommande(com,t ); // QUAND il prend une commande de boissons
		
		// Assert
		assertThat(false, is(rest.get_oCuisine().get_aoCommandes().contains(com)));   // ALORS cette commande n'apparait pas dans la liste de tâche
	}
	/**
	 * Test 21) Permet de vérifier que le serveur prenant la commande soit bien en charge de la table
	 */
	@Test
	void testCommandeEstBienAUneTableDuServeurHamcrest() {
		System.out.println("_____TEST_____ : testCommandeEstBienAUneTableDuServeurHamcrest()");
		// Act 
		Serveur serv = new Serveur(); // ETANT DONNE un serveur 
		Restaurant rest = new Restaurant(5); // dans un restaurant 
		rest.recruterServeur(serv); // Sans table assignées
		// Arrange 
		Commande commande_non_presente_en_cuisine = new Commande(100, true); 
		Table table = new Table();
		serv.prendCommande(commande_non_presente_en_cuisine, table); // quand il prend un commande de nourriture 
		// Assert
		assertThat(false, is(rest.get_oCuisine().get_aoCommandes().contains(commande_non_presente_en_cuisine))); // ALORS la commande n'apparait pas dans les tâches de la cuisine du r estaurant
		
	}
	
	/**
	 * Test 22) Permet de vérifier que le total d'une table est bien la somme des commandes passées
	 */
	@Test
	void testTotalCommandeTableHamcrest() {
		System.out.println("_____TEST_____ : testTotalCommandeTableHamcrest()");
		// ACT 
		Serveur serv= new Serveur(); // ETANT donné un serveur
		Restaurant rest = new Restaurant(5); // dans un restaurant
		rest.recruterServeur(serv);
		Table tableQuiCommande = rest.get_aoTables().get(2);
		rest.assignerTable(serv, tableQuiCommande);
		Commande commande1 = new Commande(100, false); 
		serv.prendCommande(commande1, tableQuiCommande);// ayant pris une commande auprès d'un client
		
		// Arrange
		Commande commande2 = new Commande(10, false); // QUand il prend une autre commande de la part du même client
		serv.prendCommande(commande2, tableQuiCommande);
		
		// Assert
		assertThat(110.0, equalTo(tableQuiCommande.get_TotalTable()));   //  ALROS le total de la command est la somme des deux commandes
	}
	
	/**
	 * Test 23) Permet de vérifier que le total d'une table est bien la somme des commandes passées
	 * Qu'elles soient de boissons ou de nourriture
	 */
	@Test
	void testTotalCommandesVarieesTableHamcrest() {
		System.out.println("_____TEST_____ : testTotalCommandesVarieesTableHamcrest()");
		// ACT 
		Restaurant rest = new Restaurant(5, 2, 0.2);  
		Table tableQuiCommande = rest.get_aoTables().get(2);
		Serveur serv = rest.get_aoServeurs().get(0); // ETANT donné un serveur dans un restaurant
		rest.assignerTable(serv, tableQuiCommande);
				
		// Arrange
		Commande commande1 = new Commande(100, false); 
		serv.prendCommande(commande1, tableQuiCommande);// ayant pris une commande de nourriture auprès d'un client
		
		Commande commande2 = new Commande(10, true ); // QUand il prend une commande de boisson de la part du même client
		serv.prendCommande(commande2, tableQuiCommande);
		
		// Assert
		assertThat(110.0, equalTo(tableQuiCommande.get_TotalTable()));   //  ALORS le total de la commande est la somme des deux commandes
	}
	
	/**
	 * Test 25) Permet de vérifier que le prix d'une commande dont le plat est remplacé est toujours le bon
	 */
	@Test
	void testPrixCommandeRemplaceeHamcrest() {
		System.out.println("_____TEST_____ : testPrixCommandeRemplaceeHamcrest()");
		// ACT 
		Restaurant rest = new Restaurant(10, 2, 0.2);
		Menu menu_herbes = new Menu("Menu aux herbes", "Poulet aux herbes", 10);
		rest.addMenuCarte(menu_herbes);
		rest.addPlatToMenu("Menu aux herbes", "Herbe au poulet", 12);
		
		Serveur serv = rest.get_aoServeurs().get(1);  // ETANT DONNE un serveur 
		rest.assignerTable(serv, rest.get_aoTables().get(0)); // dans un restaurant
		Commande commande_poulet = new Commande(rest.get_aoMenuRestaurant().get(0).getPlatByName("Poulet aux herbes")); // Ayant pris une commande
		serv.prendCommande(commande_poulet, rest.get_aoTables().get(0));
		
		// Arrange
		rest.get_oCuisine().refuseCommande(commande_poulet,"Poulet aux herbes"); // QUAND la cuisine refuse la commande faute de stocks
		rest.proposerPlatRemplacement(commande_poulet, "Poulet aux herbes",  "Herbe au poulet");  // ET QUE LE client accepte le plat de remplacement
		
		// Assert
		assertThat(10.0, equalTo(commande_poulet.getPlatByName("Herbe au poulet").getdPrix()));   //  ALORS le prix du nouveau plat est égale au prix du plat d orgine 
	}
	
	
}
