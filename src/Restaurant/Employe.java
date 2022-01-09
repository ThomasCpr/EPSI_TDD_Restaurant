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
		// si la table ne poss�de pas d�j� quelqu'un d'assign�
		if(t.get_employeAssigne() == null) {
			this._tables.add(t); // on assigne la table a l'employe
			t.set_employeAssigne(this); // on assigne l'employe � la table
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
		System.out.println("\nEmploye Class: desassignerUneTable(Table "+tableASupprimer+")");
		// est ce que la table �tait assign�es � cet employee? 
		if (tableASupprimer.get_employeAssigne().equals(this)) {
			// OUI - on la supprime de la liste des tables de l'employe
			ArrayList<Table> tablesAJour = new ArrayList<Table>();
			// on r�cup�re toutes les autres tables
			for(Table t: this._tables) {
				if(!t.equals(tableASupprimer)) {
					tablesAJour.add(t);
				}
			}
			// on donne la nouvelle liste de table de l'employee sans celle supprim�e
			_tables = tablesAJour;
			tableASupprimer.set_employeAssigne(null); // on m�j la table supprim�e
		}
		else {
			// NON - on ne fait rien
			System.out.println("\tLa table n'appartient pas � cet employ� elle n'est pas d�sassign�e.");
		}
	}
	

}
