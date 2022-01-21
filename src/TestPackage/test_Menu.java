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
	 * Test 16) V�rifie que quand franchise modifie prix du menu qu'elle a d�finit alors le restaurant aussi
	 */
	@Test void testModificationPrixPlat(){
		System.out.println("_____TEST_____ : testModificationPrixPlat()"); 
		// Act
		Restaurant rest = new Restaurant(5); // etant donn� un restaurant
		Franchise fran = new Franchise(); 
		fran.get_aoRestaurants().add(rest); // ayant le statut de filiale d'une franchise
		fran.createMenu("Menu Burger"); // la franchise d�finissant un menu 
		fran.addPlatToMenu("Menu Burger", "Burger pour ob�se",10 ); // //  ayant un plat
		
		// Arrange
		double dNouveauPrixDefinieParFranchise = 8; 
		fran.setPrixPlat("Menu Burger", "Burger pour ob�se", dNouveauPrixDefinieParFranchise ); // quand la franchise modifie le prix du plat
		
		// Assert 
		// ALORS le prix du plat dans le menu du restaurant est celui d�fini par la franchise
		assertEquals(dNouveauPrixDefinieParFranchise, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName("Menu Burger")).get_msdPlatsDuMenu().get("Burger pour ob�se"));
	}
	
	/**
	 * Test 17) v�rifie que la modification du prix d'un plat n'affecte pas tous les plats des autres menu de la franchise si ce n'est pas le m�me menu
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
		fran.get_aoRestaurants().add(rest); // appartenant � une franchise
		rest.createMenu(sMenuRest); // le restaurant d�finissant un menu ayant un plat
		rest.addPlatToMenu(sMenuRest, sPlat, 8);
		fran.createMenu(sMenuFran);  // la franchise d�finissant un menu ayant le m�me plat
		fran.addPlatToMenu(sMenuFran, sPlat, 7);
		
		// Arrange
		fran.setPrixPlat(sMenuFran,sPlat, 7.5); // quand la franchise modifie le prix du plat
		
		// Assert
		// alors le prix du plat dans le menu du restaurant reste inchang�
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
		fran.get_aoRestaurants().add(rest); // appartenant � une franchise
		rest.createMenu(sNomMenu); // d�finissant un menu 
		rest.addPlatToMenu(sNomMenu, "Entrec�te", dPrixPremierPlat); // ayant un plat
		
		// Arrange  
		fran.addPlatToMenu(sNomMenu, "C�tes de porc", dPrixDeuxiemePlat); // QUAND la franchise ajoute un nouveau plat
		
		// Assert 
		assertEquals(dPrixPremierPlat, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().get("Entrec�te"));
		assertEquals(dPrixDeuxiemePlat, rest.get_aoMenuRestaurant().get(rest.get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().get("C�tes de porc"));
	}
	
//	/**
//	 * Test 21) 
//	 */
//	@Test void testSuppressionPlatCarteSiFauteDeStock() {
//		System.out.println("_____TEST_____ : testSuppressionPlatCarteSiFauteDeStock()");
//		// Act 
//		Restaurant rest = new Restaurant(5);
//		Serveur serv = new Serveur();
//		rest.recruterServeur(serv); // ETANT donn� un serveur dans un restaurant
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
