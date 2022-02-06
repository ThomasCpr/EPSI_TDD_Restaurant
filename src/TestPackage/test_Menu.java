package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Cuisine;
import Restaurant.Franchise;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Utilities.RestaurantBuilder;
import Restaurant.Menu;

public class test_Menu {
	
	/**
	 * Test 16) V�rifie que quand franchise modifie prix du menu qu'elle a d�finit alors le restaurant aussi
	 */
	@Test void testModificationPrixPlat(){
		System.out.println("_____TEST_____ : testModificationPrixPlat()"); 
		// Act
		String sNomPlat = "Burger pour ob�se";
		String sNomMenu = "Menu Burger";
		Franchise fran = new Franchise(3,5,1,2.0); 
		Restaurant rest = fran.get_aoRestaurants().get(0); // etant donn� un restaurant
		fran.createMenu(sNomMenu,sNomPlat ,10); // la franchise d�finissant un menu ayant un plat 

		// Arrange
		double dNouveauPrixDefinieParFranchise = 8; 
		fran.setPrixPlat(sNomMenu, sNomPlat, dNouveauPrixDefinieParFranchise ); // quand la franchise modifie le prix du plat
		
		// Assert 
		// ALORS le prix du plat dans le menu du restaurant est celui d�fini par la franchise
		assertEquals(dNouveauPrixDefinieParFranchise, rest.getMenuByName(sNomMenu).getPlatByName(sNomPlat).getdPrix());
	}
	
	/**
	 * Test 17) v�rifie que la modification du prix d'un plat n'affecte pas tous les plats des autres menu de la franchise si ce n'est pas le m�me menu
	 */
	@Test void testModificatioPrixPlatNiveauRestaurant() {
		System.out.println("_____TEST_____ : testModificatioPrixPlatNiveauRestaurant()");
		// Act 
		String sNomMenuFran, sNomMenuRest, sNomPlat;
		sNomMenuFran = "Menu du jour";
		sNomMenuRest = "Menu burger";
		sNomPlat = "Burger"; 
		Franchise fran = new Franchise(3,3,1,0.2); 
		Restaurant rest = fran.get_aoRestaurants().get(0); // Etant donne un restaurant appartenant � une franchise
		rest.createMenu(sNomMenuRest,sNomPlat, 8); // le restaurant d�finissant un menu ayant un plat
		fran.createMenu(sNomMenuFran,sNomPlat, 8);  // la franchise d�finissant un menu ayant le m�me plat
		
		// Arrange
		fran.setPrixPlat(sNomMenuFran,sNomPlat, 7.5); // quand la franchise modifie le prix du plat
		
		// Assert
		// alors le prix du plat dans le menu du restaurant reste inchang�
		assertEquals(8 , rest.getMenuByName(sNomMenuRest).getPlatByName(sNomPlat).getdPrix());
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
		Franchise fran = new Franchise(3,3,1,0.2);
		Restaurant rest = fran.get_aoRestaurants().get(0); // ETANT DONNE un restaurant appartenant � une franchise
		rest.createMenu(sNomMenu, "Entrec�te", dPrixPremierPlat); // d�finissant un menu ayant un plat 
		
		// Arrange  
		fran.addPlatToMenu(sNomMenu, "C�tes de porc", dPrixDeuxiemePlat); // QUAND la franchise ajoute un nouveau plat
		
		// Assert 
		assertEquals(dPrixPremierPlat, rest.getMenuByName(sNomMenu).getPlatByName("Entrec�te").getdPrix());
		assertEquals(dPrixDeuxiemePlat, rest.getMenuByName(sNomMenu).getPlatByName("C�tes de porc").getdPrix());
	}
	
	/**
	 * Test 24) Quand la cuisine d�clare un plat en rupture alors il doit disparaitre de la carte du restaurant 
	 */
	@Test void testSuppressionPlatCarteSiFauteDeStock() {
		System.out.println("_____TEST_____ : testSuppressionPlatCarteSiFauteDeStock()");
		// Act 
		Restaurant rest = new Restaurant(5, 2, 0.2); 
		Menu menu_burger = new Menu("Menu Burger", "Burger vegan pour les relous", 15.0);
		menu_burger.addPlatToMenu("Burger quintuple steak", 12.0);// on ajoute plusieurs plats au menu
		Serveur serv = rest.get_aoServeurs().get(0);  // ETANT donn� un serveur dans un restaurant
		Cuisine cuisineRefusantLaCommande = rest.get_oCuisine(); 
		rest.addMenuCarte(menu_burger);
		
		Commande com = new Commande(menu_burger.getPlatByName("Burger vegan pour les relous") );
		serv.prendCommande(com, rest.get_aoTables().get(0)); // ayant pris une commande � une table
		
		// Arrange
		cuisineRefusantLaCommande.refuseCommande(com, "Burger vegan pour les relous"); // quand la cuisine refuse la commande faute de stocks
	
		// assert
		assertEquals(false,rest.getAllPlats().contains(menu_burger.getPlatByName("Burger vegan pour les relous"))); // ALORS le plat en rupture n'apparrait plus sur la carte du restaurant.
	}
	
	

}
