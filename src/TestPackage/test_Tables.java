package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Restaurant;
import Restaurant.Table;

public class test_Tables {
	
	/**
	 * Test 14) une table ayant des client affectée n'est plus libre
	 */
	@Test void testDisponibiliteTable() {
		System.out.println("_____TEST_____ : testDisponibiliteTable()");
		//Act
		Restaurant rest = new Restaurant(5); // etant donne un restaurant 
		rest.serviceCommence(); // ayant débuté sont service
		
		// Arrange 	
		rest.get_tables().get(0).set_estLibre(false); // quand un client est affecté à une table
		
		// Assert
		// alors cette table n'est plus sur la liste des tables libres du restaurant
		assertEquals(false,  rest.get_tablesLibres().contains(rest.get_tables().get(0))); 
	}
	
	/**
	 * Test 15) vérifie qu'une table libérée est bien dans la liste des tables libres
	 */
	@Test void testLiberationTable() {
		System.out.println("_____TEST_____ : testLiberationTable()"); 
		// Act 
		Restaurant rest = new Restaurant(5); // etant donné un restaurant 
		rest.get_tables().get(1).set_estLibre(false); // avec une table occupée par un client
		// Arrange
		rest.get_tables().get(1).set_estLibre(true); // quand la talbe est libérée
		// Assert
		assertEquals(true, rest.get_tablesLibres().contains(rest.get_tables().get(1))); 
	}

}
