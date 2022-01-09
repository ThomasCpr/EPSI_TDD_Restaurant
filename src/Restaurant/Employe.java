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
		System.out.println("\nEmploye Class: assignerTable(Table "+t+") ");		
		// si la table ne possède pas déjà quelqu'un d'assigné
		if(t.get_employeAssigne() == null) {
			this._tables.add(t); // on assigne la table a l'employe
			t.set_employeAssigne(this); // on assigne l'employe à la table
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
		System.out.println("\nEmploye Class: desassignerUneTable(Table "+tableASupprimer+")");
		// est ce que la table était assignées à cet employee? 
		if (tableASupprimer.get_employeAssigne().equals(this)) {
			// OUI - on la supprime de la liste des tables de l'employe
			ArrayList<Table> tablesAJour = new ArrayList<Table>();
			// on récupère toutes les autres tables
			for(Table t: this._tables) {
				if(!t.equals(tableASupprimer)) {
					tablesAJour.add(t);
				}
			}
			// on donne la nouvelle liste de table de l'employee sans celle supprimée
			_tables = tablesAJour;
			tableASupprimer.set_employeAssigne(null); // on màj la table supprimée
		}
		else {
			// NON - on ne fait rien
			System.out.println("\tLa table n'appartient pas à cet employé elle n'est pas désassignée.");
		}
	}
	

}
