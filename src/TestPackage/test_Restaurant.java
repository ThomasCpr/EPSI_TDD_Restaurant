package TestPackage;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Table;

public class test_Restaurant {
		/**
		 * Test 4) Vérifie que le CA restaurant est bien la somme des CA des serveurs
		 */
		@Test
		void testChiffreAffaireRestaurant() {
			System.out.println("_____TEST_____ : testChiffreAffaireRestaurant()"); 
			// Act
			double dMontant = 1.0;
			int nbServeur = 0;
			// Cas X = 0
			Restaurant resto_1 = new Restaurant(nbServeur); // (il faut au moins le même nombre de table que de serveurs)
			assertEquals(dMontant*nbServeur,resto_1.chiffreAffaire());
			
			// Cas X = 1	
			nbServeur = 1;
			Restaurant resto_2 = new Restaurant(nbServeur);
			Serveur s = new Serveur();
			resto_2.recruterServeur(s);
			resto_2.assignerTable(s, resto_2.get_aoTables().get(0));
			s.prendCommande(new Commande(dMontant,false), resto_2.get_aoTables().get(0));
			assertEquals(dMontant*nbServeur,resto_2.chiffreAffaire());
			
			// Cas X = 2
			nbServeur = 2;
			Restaurant resto_3 = new Restaurant(nbServeur);
			for(int i = 0; i<nbServeur; i++) {
				Serveur serveur = new Serveur();
				resto_3.recruterServeur(serveur);
				resto_3.assignerTable(serveur, resto_3.get_aoTables().get(i));
				// Arrange
				serveur.prendCommande(new Commande(dMontant, false), resto_3.get_aoTables().get(i));
			}
				// Assert
			assertEquals(dMontant*nbServeur,resto_3.chiffreAffaire());
			// Cas X = 100
			nbServeur = 100;
			Restaurant resto_4 = new Restaurant(nbServeur);
			for(int i = 0; i<nbServeur; i++) {
				Serveur serveur = new Serveur();
				resto_4.recruterServeur(serveur);
				resto_4.assignerTable(serveur, resto_4.get_aoTables().get(i));
				serveur.prendCommande(new Commande(dMontant, false), resto_4.get_aoTables().get(i));
			}
				// Assert
			assertEquals(dMontant*nbServeur ,resto_4.chiffreAffaire() );

		}
		
		/**
		 * Test 6) vérifie qu'en début de service toutes les tables sont affectées au MH
		 */
		@Test
		void testAffectationTableMaitreHotel() {
			System.out.println("_____TEST_____ : testAffectationTableMaitreHotel()"); 
			// Act
			int nbTables = 3;
			Restaurant r = new Restaurant(nbTables); // restaurant à 3 tables
			// Arrange 
			r.serviceCommence(); // le service débute
			// Assert
			// le maitre d'hotel possède toutes les tables
			assertEquals(nbTables, r.get_oMaitreHotel().nbTableAffectees()); 			
			
		}
		/**
		 * Test 7) Après affectation toutes les tales (sauf celles assignés) sont au MH 
		 */
		@Test
		void testAffectationTableMaitreHotelEtServeur() {
			System.out.println("_____TEST_____ : testAffectationTableMaitreHotelEtServeur()"); 
			// Act
			int nbTables = 3;
			Serveur s = new Serveur();
			Restaurant r = new Restaurant(nbTables); // restaurant à 3 tables
			r.get_aoServeurs().add(s); // ajoute serveur au resto
			
			for(Serveur serv: r.get_aoServeurs()) {
				System.out.println(serv);
			}
			// Arrange 
			r.assignerTable(s, r.get_aoTables().get(0)); // on assigne une table au serveur
			r.serviceCommence(); // le service débute
			
			// Assert
			assertEquals(2, r.get_oMaitreHotel().nbTableAffectees()); // le maitre d'hotel possède toutes les tables sauf une 	
			assertEquals(1, s.nbTableAffectees());
			
		}
		
		
		/**
		 * Test 8) Impossible de modifier un serveur affecté à une table si service à débuté
		 */
		@Test
		void testModificationServeurAffecteATable() {
			System.out.println("_____TEST_____ : testModificationServeurAffecteATable()");
			// Act 
			Restaurant r = new Restaurant(3); // etant donné un restaurant a 3 tables
			Serveur s = new Serveur();  // dont un serveur 
			r.get_aoServeurs().add(s); //  
			Table t = r.get_aoTables().get(0); // dont une table
			r.assignerTable(s, t); // lui étant affecté
			
			// Arrange
			r.serviceCommence(); // quand le service débute
			Serveur nouveauServeur = new Serveur();
			r.assignerTable(nouveauServeur, t ); // si on assigne la table à une autre personne			 
			// Assert
			assertEquals(false, nouveauServeur == t.get_oEmployeAssigne());
		}
		
		
		/**
		 * Test 9) fin de service fait que toutes les tables reviennent au maitre d'hôtel 
		 */
		@Test
		void testAffectationDebutService() {
			System.out.println("_____TEST_____ : testAffectationDebutService()"); 
			// Act
			Restaurant r = new Restaurant(3); // etant donné un restaurant a 3 tables
			Serveur serveur = new Serveur();  // ayant un serveur
			r.get_aoServeurs().add(serveur); 
			Table t0 = r.get_aoTables().get(0);
			Table t1 = r.get_aoTables().get(1);
			r.assignerTable(serveur, t0); // avec une table affectée
			r.serviceCommence(); // et ayant débuté son service
			// Arrange 
			r.serviceTermine(); // quand le service se termine
			
			r.assignerTable(serveur, t1); // et qu'une table est affecté au serveur

			// Assert
			assertEquals(true, serveur.possedeLaTableAssignee(t1)); // la table editee est affectée au serveur
			// la table du serveur n'est pas dans les tables assignées au MH mais il possède les deux autres
			assertEquals(true, (r.get_oMaitreHotel().possedeLaTableAssignee(t1)==false &&  r.get_oMaitreHotel().nbTableAffectees() == 2));
		}
		
		

}
