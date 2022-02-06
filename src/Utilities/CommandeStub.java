package Utilities;

import Restaurant.Commande;
import Restaurant.Plat;

public class CommandeStub extends Commande {
	public CommandeStub(double dMontant) {
		super(new Plat("TestStub", dMontant));
	}
}
