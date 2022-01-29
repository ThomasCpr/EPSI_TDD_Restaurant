package TestPackage;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.jupiter.api.Test;

import Restaurant.Restaurant;
import Restaurant.Table;

public class test_TablesHamcrest {
	
	/**
	 * Test 14) une table ayant des client affectée n'est plus libre
	 */
	@Test void testDisponibiliteTableHamcrest() {
		System.out.println("_____TEST_____ : testDisponibiliteTableHamcrest()");
		//Act
		Restaurant rest = new Restaurant(5); // etant donne un restaurant 
		rest.serviceCommence(); // ayant débuté sont service
		
		// Arrange 	
		rest.get_aoTables().get(0).set_bEstLibre(false); // quand un client est affecté à une table
		
		// Assert
		// alors cette table n'est plus sur la liste des tables libres du restaurant
		assertThat(false, is(rest.get_aoTablesLibres().contains(rest.get_aoTables().get(0))));
	}
	
	/**
	 * Test 15) vérifie qu'une table libérée est bien dans la liste des tables libres
	 */
	@Test void testLiberationTableHamcrest() {
		System.out.println("_____TEST_____ : testLiberationTableHamcrest()"); 
		// Act 
		Restaurant rest = new Restaurant(5); // etant donné un restaurant 
		rest.get_aoTables().get(1).set_bEstLibre(false); // avec une table occupée par un client
		// Arrange
		rest.get_aoTables().get(1).set_bEstLibre(true); // quand la talbe est libérée
		// Assert
		assertThat(true, is(rest.get_aoTablesLibres().contains(rest.get_aoTables().get(1))));
	}

}
