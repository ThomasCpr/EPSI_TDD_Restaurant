package Restaurant;

import java.util.ArrayList;

public class Employe {
	// - - - - - Attributes - - - - - //
	private ArrayList<Table> _aoTables;

	// - - - - - Constructors - - - - - //
	public Employe() {
		super();
		_aoTables = new ArrayList<Table>();
	}

	public Employe(ArrayList<Table> tables) {
		_aoTables = tables;
	}
	
	// - - - - - GETTERS && SETTERS - - - - - //
	public ArrayList<Table> get_aoTables() {
		return _aoTables;
	}
	public void set_aoTables(ArrayList<Table> _aoTables) {
		this._aoTables = _aoTables;
	}

	// - - - - - METHODS - - - - - //
	public int nbTableAffectees() {
		return _aoTables.size();	}
	
	/**
	 * Assigne une table à l'employé seulement si la table est libre
 	 * @param t la table à assigner
	 */
	public void assignerTable(Table t) {
		System.out.println("Employe Class: assignerTable(Table "+t+") ");		
		// si la table ne possède pas déjà un serveur  d'assigné
		if(t.get_oEmployeAssigne() == null) {
			this._aoTables.add(t); // on assigne la table a l'employe
			t.set_oEmployeAssigne(this); // on assigne l'employe à la table
			System.out.println("\tAssignation de la table "+t+" à employé "+this+"");
		}
	}
	
	/**
	 * Permet de savoir si un employé possède la table comme lui étant assignée
	 * @param tableAVerifier
	 * @return true si elle fait partie de la liste des tables qui lui sont assignées, false sinon.
	 */
	public boolean possedeLaTableAssignee(Table tableAVerifier) {
		System.out.println(("Employe Class: possedeLaTableAssignee("+tableAVerifier+")"));
		// si l'employee possède des tables assignées
		if(!(_aoTables.isEmpty())) {
			for(Table t : _aoTables) {
				// on regarde si parmis elle on a la table que l'on cherche
				if (t == tableAVerifier) {
					return true;
				}
			}
		}	
		return false;
	}
	/**
	 * Permet de désassigner une table d'un employé en mettant la liste des table de l'employé à jour
	 * mais aussi en mettant la table en question à jour
	 * @param tableASupprimer
	 */
	public void desassignerUneTable(Table tableASupprimer) {
		System.out.println("Employe Class: desassignerUneTable(Table "+tableASupprimer+")");
		// est ce que la table était assignées à cet employee? 
		if (tableASupprimer.get_oEmployeAssigne().equals(this)) {
			// OUI - on la supprime de la liste des tables de l'employe
			ArrayList<Table> tablesAJour = new ArrayList<Table>();
			// on récupère toutes les autres tables
			for(Table t: this._aoTables) {
				if(!t.equals(tableASupprimer)) {
					tablesAJour.add(t);
				}
			}
			// on donne la nouvelle liste de table de l'employee sans celle supprimée
			_aoTables = tablesAJour;
			tableASupprimer.set_oEmployeAssigne(null); // on màj la table supprimée
		}
		else {
			// NON - on ne fait rien
			System.out.println("\tLa table n'appartient pas à cet employé elle n'est pas désassignée.");
		}
	}
	
	public Table getSpecificTable(Table t) {
		return _aoTables.get(_aoTables.indexOf(t));
	}
}
