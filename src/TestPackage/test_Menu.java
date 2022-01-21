package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Menu;

public class test_Menu {
	
	/**
	 * Test 16) Vérifie que quand franchise modifie prix du menu qu'elle a définit alors le restaurant aussi
	 */
	@Test void testModificationPrixPlat(){
		System.out.println("_____TEST_____ : testModificationPrixPlat()"); 
		// Act
		Restaurant rest = new Restaurant(5); // etant donné un restaurant
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // ayant le statut de filiale d'une franchise
		fran.createMenu("Menu Burger"); // la franchise définissant un menu 
		fran.addPlatToMenu("Menu Burger", "Burger pour obèse",10 ); // //  ayant un plat
		
		// Arrange
		double dNouveauPrixDefinieParFranchise = 8; 
		fran.setPrixPlat("Menu Burger", "Burger pour obèse", dNouveauPrixDefinieParFranchise ); // quand la franchise modifie le prix du plat
		
		// Assert 
		// ALORS le prix du plat dans le menu du restaurant est celui défini par la franchise
		assertEquals(dNouveauPrixDefinieParFranchise, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName("Menu Burger")).get_msdPlatsDuMenu().get("Burger pour obèse"));
	}
	
	/**
	 * Test 17) vérifie que la modification du prix d'un plat n'affecte pas tous les plats des autres menu de la franchise si ce n'est pas le même menu
	 */
	@Test void testModificatioPrixPlatNiveauRestaurant() {
		System.out.println("_____TEST_____ : testModificatioPrixPlatNiveauRestaurant()");
		// Act 
		String sMenuFran, sMenuRest, sPlat;
		sMenuFran = "Menu du jour";
		sMenuRest = "Menu burger";
		sPlat = "Burger";
		Restaurant rest = new Restaurant(5); // Etant donne un restaurant 
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // appartenant à une franchise
		rest.createMenu(sMenuRest); // le restaurant définissant un menu ayant un plat
		rest.addPlatToMenu(sMenuRest, sPlat, 8);
		fran.createMenu(sMenuFran);  // la franchise définissant un menu ayant le même plat
		fran.addPlatToMenu(sMenuFran, sPlat, 7);
		
		// Arrange
		fran.setPrixPlat(sMenuFran,sPlat, 7.5); // quand la franchise modifie le prix du plat
		
		// Assert
		// alors le prix du plat dans le menu du restaurant reste inchangé
		assertEquals(8 , rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sMenuRest)).get_msdPlatsDuMenu().get(sPlat));
	}

	/**
	 * Test 18) L'ajout de plat par la franchise n'affecte pas les plats du restaurant
	 */
	@Test void testAjoutDePlatParFranchise() {
		System.out.println("_____TEST_____ :testAjoutDePlatParFranchise()"); 
		// Act
		String sNomMenu = "Menu bouftou";
		double dPrixPremierPlat = 12;
		double dPrixDeuxiemePlat = 10;
		Restaurant rest = new Restaurant(); // ETANT DONNE un restaurant
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // appartenant à une franchise
		rest.createMenu(sNomMenu); // définissant un menu 
		rest.addPlatToMenu(sNomMenu, "Entrecôte", dPrixPremierPlat); // ayant un plat
		
		// Arrange  
		fran.addPlatToMenu(sNomMenu, "Côtes de porc", dPrixDeuxiemePlat); // QUAND la franchise ajoute un nouveau plat
		
		// Assert 
		assertEquals(dPrixPremierPlat, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().get("Entrecôte"));
		assertEquals(dPrixDeuxiemePlat, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().get("Côtes de porc"));
	}
	
//	/**
//	 * Test 21) 
//	 */
//	@Test void testSuppressionPlatCarteSiFauteDeStock() {
//		System.out.println("_____TEST_____ : testSuppressionPlatCarteSiFauteDeStock()");
//		// Act 
//		Restaurant rest = new Restaurant(5);
//		Serveur serv = new Serveur();
//		rest.recruterServeur(serv); // ETANT donné un serveur dans un restaurant
//		Commande com = new Commande(50, true);
//		serv.prendCommande(com); // ayant pris une commande
//		// Arrange
//		rest.get_oCuisine().refuseCommande(com); // quand la cuisine refuse la commande faute de stocks
//		
//		// assert
//		assertEquals(false,rest.getPlats().contains(plats));
//		
//		
//	}

}
