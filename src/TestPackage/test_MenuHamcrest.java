package TestPackage;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Cuisine;
import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Menu;

public class test_MenuHamcrest {
	
	/**
	 * Test 16) Vérifie que quand franchise modifie prix du menu qu'elle a définit alors le restaurant aussi
	 */
	@Test void testModificationPrixPlatHamcrest(){
		System.out.println("_____TEST_____ : testModificationPrixPlatHamcrest()"); 
		// Act
		Restaurant rest = new Restaurant(5); // etant donné un restaurant
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // ayant le statut de filiale d'une franchise
		fran.createMenu("Menu Burger", "Burger pour obèse",10); // la franchise définissant un menu ayant un plat 
		
		// Arrange
		double dNouveauPrixDefinieParFranchise = 8; 
		fran.setPrixPlat("Menu Burger", "Burger pour obèse", dNouveauPrixDefinieParFranchise ); // quand la franchise modifie le prix du plat
		
		// Assert 
		// ALORS le prix du plat dans le menu du restaurant est celui défini par la franchise

		assertThat(dNouveauPrixDefinieParFranchise, equalTo(rest.getMenuByName("Menu Burger").getPlatByName("Burger pour obèse").getdPrix()));
	}
	
	/**
	 * Test 17) vérifie que la modification du prix d'un plat n'affecte pas tous les plats des autres menu de la franchise si ce n'est pas le même menu
	 */
	@Test void testModificatioPrixPlatNiveauRestaurantHamcrest() {
		System.out.println("_____TEST_____ : testModificatioPrixPlatNiveauRestaurantHamcrest()");
		// Act 
		String sNomMenuFran, sNomMenuRest, sNomPlat;
		sNomMenuFran = "Menu du jour";
		sNomMenuRest = "Menu burger";
		sNomPlat = "Burger";
		Restaurant rest = new Restaurant(5); // Etant donne un restaurant 
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // appartenant à une franchise
		rest.createMenu(sNomMenuRest,sNomPlat, 8); // le restaurant définissant un menu ayant un plat
		fran.createMenu(sNomMenuFran,sNomPlat, 8);  // la franchise définissant un menu ayant le même plat
		
		// Arrange
		fran.setPrixPlat(sNomMenuFran,sNomPlat, 7.5); // quand la franchise modifie le prix du plat
		
		// Assert
		// alors le prix du plat dans le menu du restaurant reste inchangé
		assertThat(8.0, equalTo(rest.getMenuByName(sNomMenuRest).getPlatByName(sNomPlat).getdPrix()));
	}

	/**
	 * Test 18) L'ajout de plat par la franchise n'affecte pas les plats du restaurant
	 */
	@Test void testAjoutDePlatParFranchiseHamcrest() {
		System.out.println("_____TEST_____ :testAjoutDePlatParFranchiseHamcrest()"); 
		// Act
		String sNomMenu = "Menu bouftou";
		double dPrixPremierPlat = 12;
		double dPrixDeuxiemePlat = 10;
		Restaurant rest = new Restaurant(); // ETANT DONNE un restaurant
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // appartenant à une franchise
		rest.createMenu(sNomMenu, "Entrecôte", dPrixPremierPlat); // définissant un menu ayant un plat 
		
		// Arrange  
		fran.addPlatToMenu(sNomMenu, "Côtes de porc", dPrixDeuxiemePlat); // QUAND la franchise ajoute un nouveau plat
		
		// Assert 
		assertThat(dPrixPremierPlat, equalTo(rest.getMenuByName(sNomMenu).getPlatByName("Entrecôte").getdPrix()));
		assertThat(dPrixDeuxiemePlat, equalTo(rest.getMenuByName(sNomMenu).getPlatByName("Côtes de porc").getdPrix()));
	}
	
	/**
	 * Test 24) Quand la cuisine déclare un plat en rupture alors il doit disparaitre de la carte du restaurant 
	 */
	@Test void testSuppressionPlatCarteSiFauteDeStockHamcrest() {
		System.out.println("_____TEST_____ : testSuppressionPlatCarteSiFauteDeStockHamcrest()");
		// Act 
		Restaurant rest = new Restaurant(5, 2, 0.2); 
		Menu menu_burger = new Menu("Menu Burger", "Burger vegan pour les relous", 15.0);
		menu_burger.addPlatToMenu("Burger quintuple steak", 12.0);// on ajoute plusieurs plats au menu
		Serveur serv = rest.get_aoServeurs().get(0);  // ETANT donné un serveur dans un restaurant
		Cuisine cuisineRefusantLaCommande = rest.get_oCuisine(); 
		rest.addMenuCarte(menu_burger);
		
		Commande com = new Commande(menu_burger.getPlatByName("Burger vegan pour les relous") );
		serv.prendCommande(com, rest.get_aoTables().get(0)); // ayant pris une commande à une table
		
		// Arrange
		cuisineRefusantLaCommande.refuseCommande(com, "Burger vegan pour les relous"); // quand la cuisine refuse la commande faute de stocks
	
		// assert
		assertThat(false, is(rest.getAllPlats().contains(menu_burger.getPlatByName("Burger vegan pour les relous"))));  // ALORS le plat en rupture n'apparrait plus sur la carte du restaurant.
	}
	
	

}
