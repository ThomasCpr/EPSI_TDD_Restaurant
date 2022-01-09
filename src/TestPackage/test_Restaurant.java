package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Restaurant.Table;

public class test_Restaurant {
		/**
		 * Test 4) V�rifie que le CA restaurant est bien la somme des CA des serveurs
		 */
		@Test
		void testChiffreAffaireRestaurant() {
			System.out.println("_____TEST_____ : testChiffreAffaireRestaurant()"); 
			// Act
			double y = 1.0;
			Restaurant resto_1 = new Restaurant(0);
			
			Restaurant resto_2 = new Restaurant(1);
			resto_2.get_serveurs().add(new Serveur());
			
			Restaurant resto_3 = new Restaurant(2);			
			resto_3.get_serveurs().add(new Serveur());
			resto_3.get_serveurs().add(new Serveur());

			Restaurant resto_4 = new Restaurant(100);
			for(int i = 0 ; i<100; i++) {
				resto_4.get_serveurs().add(new Serveur());
			}
			
			// Arrange 
			for(Serveur s : resto_2.get_serveurs()) {
				s.prendCommande(new Commande(y));
			}
			
			for(Serveur s : resto_3.get_serveurs()) {
				s.prendCommande(new Commande(y));
			}
			
			for(Serveur s : resto_4.get_serveurs()) {
				s.prendCommande(new Commande(y));
			}
			
			// Assert
			assertEquals(0.0,resto_1.chiffreAffaire()); // vaut-il mieux faire des valeurs dure ou var ? 
			assertEquals(1.0,resto_2.chiffreAffaire());
			assertEquals(2.0,resto_3.chiffreAffaire());
			assertEquals(100.0 ,resto_4.chiffreAffaire() );
		}
		
		/**
		 * Test 6) v�rifie qu'en d�but de service toutes les tables sont affect�es au MH
		 */
		@Test
		void testAffectationTableMaitreHotel() {
			System.out.println("_____TEST_____ : testAffectationTableMaitreHotel()"); 
			// Act
			int nbTables = 3;
			Restaurant r = new Restaurant(nbTables); // restaurant � 3 tables
			// Arrange 
			r.serviceCommence(); // le service d�bute
			// Assert
			// le maitre d'hotel poss�de toutes les tables
			assertEquals(nbTables, r.get_maitreHotel().nbTableAffectees()); 			
			
		}
		/**
		 * Test 7) Apr�s affectation toutes les tales (sauf celles assign�s) sont au MH 
		 */
		@Test
		void testAffectationTableMaitreHotelEtServeur() {
			System.out.println("_____TEST_____ : testAffectationTableMaitreHotelEtServeur()"); 
			// Act
			int nbTables = 3;
			Serveur s = new Serveur();
			Restaurant r = new Restaurant(nbTables); // restaurant � 3 tables
			r.get_serveurs().add(s); // ajoute serveur au resto
			
			for(Serveur serv: r.get_serveurs()) {
				System.out.println(serv);
			}
			// Arrange 
			r.assignerTable(s, r.get_tables().get(0)); // on assigne une table au serveur
			r.serviceCommence(); // le service d�bute
			
			// Assert
			assertEquals(2, r.get_maitreHotel().nbTableAffectees()); // le maitre d'hotel poss�de toutes les tables sauf une 	
			assertEquals(1, s.nbTableAffectees());
			
		}
		
		
		/**
		 * Test 8) Impossible de modifier un serveur affect� � une table si service � d�but�
		 */
		@Test
		void testModificationServeurAffecteATable() {
			System.out.println("_____TEST_____ : testModificationServeurAffecteATable()");
			// Act 
			Restaurant r = new Restaurant(3); // etant donn� un restaurant a 3 tables
			Serveur s = new Serveur();  // dont un serveur 
			r.get_serveurs().add(s); //  
			Table t = r.get_tables().get(0); // dont une table
			r.assignerTable(s, t); // lui �tant affect�
			
			// Arrange
			r.serviceCommence(); // quand le service d�bute
			Serveur nouveauServeur = new Serveur();
			r.assignerTable(nouveauServeur, t ); // si on assigne la table � une autre personne			 
			// Assert
			assertEquals(false, nouveauServeur == t.get_employeAssigne());
		}
		
		
		/**
		 * Test 9) fin de service fait que toutes les tables reviennent au maitre d'h�tel 
		 */
		@Test
		void testAffectationDebutService() {
			System.out.println("_____TEST_____ : testAffectationDebutService()"); 
			// Act
			Restaurant r = new Restaurant(3); // etant donn� un restaurant a 3 tables
			Serveur serveur = new Serveur();  // ayant un serveur
			r.get_serveurs().add(serveur); 
			Table t0 = r.get_tables().get(0);
			Table t1 = r.get_tables().get(1);
			r.assignerTable(serveur, t0); // avec une table affect�e
			r.serviceCommence(); // et ayant d�but� son service
			// Arrange 
			r.serviceTermine(); // quand le service se termine
			
			r.assignerTable(serveur, t1); // et qu'une table est affect� au serveur

			// Assert
			assertEquals(true, serveur.possedeLaTableAssignee(t1)); // la table editee est affect�e au serveur
			// la table du serveur n'est pas dans les tables assign�es au MH mais il poss�de les deux autres
			assertEquals(true, (r.get_maitreHotel().possedeLaTableAssignee(t1)==false &&  r.get_maitreHotel().nbTableAffectees() == 2));
		}
		
		
		// Act 
		// Arrange 
		// Assert
		
}
