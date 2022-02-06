package TestPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Restaurant.Commande;
import Restaurant.Restaurant;
import Restaurant.Serveur;
import Utilities.CommandeStub;

public class test_ServeurRecette {
	
	 /**
     * Test Recette)
     *  - Un serveurs est initialisé avec un chiffre d'affaire à 0
     *  - Puis il prend une commande, alors son CA est égal au prix de la commande
     *  - Puis il prend un autre commande, alors son CA est la somme de ces commandes
     */
    @Test
    void testServeurRecette() {
  
        System.out.println("_____TEST_____ : testServeurRecette()");
        Serveur serv = new Serveur();

        assertEquals(0, serv.getdChiffreAffaire()); // chiffre d'affaire à 0

        Restaurant rest = new Restaurant(5);
        rest.recruterServeur(serv);
        rest.assignerTable(serv, rest.get_aoTables().get(0));
        
        serv.prendCommande(new CommandeStub(100), rest.get_aoTables().get(0)); // QUAND il prend une nouvelle commande

        assertEquals(100, serv.getdChiffreAffaire()); // alors son chiffre d'affaire est le montant de celle ci

        rest.assignerTable(serv, rest.get_aoTables().get(1)); // il prend une 2e commande 

        serv.prendCommande(new CommandeStub(100), rest.get_aoTables().get(1));

        assertEquals(200, serv.getdChiffreAffaire()); // alors son chiffre d'affaire est la somme des deux commandes
    }
}
