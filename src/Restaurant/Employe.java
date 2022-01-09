package Restaurant;

import java.util.ArrayList;

public class Employe {
	
	private ArrayList<Table> _tables;


	public Employe() {
		super();
		_tables = new ArrayList<Table>();
	}

	public Employe(ArrayList<Table> tables) {
		_tables = tables;
	}
	public ArrayList<Table> get_tables() {
		return _tables;
	}

	public void set_tables(ArrayList<Table> _tables) {
		this._tables = _tables;
	}
		
	public int nbTableAffectees() {
		return _tables.size();	}
	
	
	public void assignerTable(Table t) {
		// si la table ne possède pas déjà quelqu'un d'assigné
		if(t.get_employeAssigne() == null) {
			// on assigne la table a l'employe
			this._tables.add(t);
			// on assigne l'employe à la table
			t.set_employeAssigne(this);
		}
	}
	
	public boolean possedeLaTableAssignee(Table tableAVerifier) {
		// si l'employee possède des tables assignées
		if(!(_tables.isEmpty())) {
			for(Table t : _tables) {
				// on regarde si parmis elle on a la table que l'on cherche
				if (t == tableAVerifier) {
					return true;
				}
			}
		}	
		return false;
	}
	
	public void desassignerUneTable(Table tableASupprimer) {
		System.out.println("- - Désassignation d'une table - -");
		this._tables.remove(tableASupprimer); // on la supprime de la liste des tables de l'employe
		tableASupprimer.set_employeAssigne(null); // on màj la table
	}
	
	protected ArrayList<Table> desassignerTablesFinDeService() {
		System.out.println("- - Désassignation de fin de service - -");
		ArrayList<Table> tablesAJour = new ArrayList<Table>();
		// on mets à jour toutes les tables
		for(Table t: _tables) {
			t.set_employeAssigne(null);
		}
		tablesAJour = _tables; // on les stock
		// on mets à jour la liste des tables de l'employé
		_tables = new ArrayList<Table>();
		return tablesAJour;
	}
}
