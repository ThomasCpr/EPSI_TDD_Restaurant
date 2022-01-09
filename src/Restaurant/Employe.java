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
		// si la table ne poss�de pas d�j� quelqu'un d'assign�
		if(t.get_employeAssigne() == null) {
			// on assigne la table a l'employe
			this._tables.add(t);
			// on assigne l'employe � la table
			t.set_employeAssigne(this);
		}
	}
	
	public boolean possedeLaTableAssignee(Table tableAVerifier) {
		// si l'employee poss�de des tables assign�es
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
		System.out.println("- - D�sassignation d'une table - -");
		this._tables.remove(tableASupprimer); // on la supprime de la liste des tables de l'employe
		tableASupprimer.set_employeAssigne(null); // on m�j la table
	}
	
	protected ArrayList<Table> desassignerTablesFinDeService() {
		System.out.println("- - D�sassignation de fin de service - -");
		ArrayList<Table> tablesAJour = new ArrayList<Table>();
		// on mets � jour toutes les tables
		for(Table t: _tables) {
			t.set_employeAssigne(null);
		}
		tablesAJour = _tables; // on les stock
		// on mets � jour la liste des tables de l'employ�
		_tables = new ArrayList<Table>();
		return tablesAJour;
	}
}
