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
	 * Assigne une table � l'employ� seulement si la table est libre
 	 * @param t la table � assigner
	 */
	public void assignerTable(Table t) {
		System.out.println("Employe Class: assignerTable(Table "+t+") ");		
		// si la table ne poss�de pas d�j� un serveur  d'assign�
		if(t.get_oEmployeAssigne() == null) {
			this._aoTables.add(t); // on assigne la table a l'employe
			t.set_oEmployeAssigne(this); // on assigne l'employe � la table
			System.out.println("\tAssignation de la table "+t+" � employ� "+this+"");
		}
	}
	
	/**
	 * Permet de savoir si un employ� poss�de la table comme lui �tant assign�e
	 * @param tableAVerifier
	 * @return true si elle fait partie de la liste des tables qui lui sont assign�es, false sinon.
	 */
	public boolean possedeLaTableAssignee(Table tableAVerifier) {
		System.out.println(("Employe Class: possedeLaTableAssignee("+tableAVerifier+")"));
		// si l'employee poss�de des tables assign�es
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
	 * Permet de d�sassigner une table d'un employ� en mettant la liste des table de l'employ� � jour
	 * mais aussi en mettant la table en question � jour
	 * @param tableASupprimer
	 */
	public void desassignerUneTable(Table tableASupprimer) {
		System.out.println("Employe Class: desassignerUneTable(Table "+tableASupprimer+")");
		// est ce que la table �tait assign�es � cet employee? 
		if (tableASupprimer.get_oEmployeAssigne().equals(this)) {
			// OUI - on la supprime de la liste des tables de l'employe
			ArrayList<Table> tablesAJour = new ArrayList<Table>();
			// on r�cup�re toutes les autres tables
			for(Table t: this._aoTables) {
				if(!t.equals(tableASupprimer)) {
					tablesAJour.add(t);
				}
			}
			// on donne la nouvelle liste de table de l'employee sans celle supprim�e
			_aoTables = tablesAJour;
			tableASupprimer.set_oEmployeAssigne(null); // on m�j la table supprim�e
		}
		else {
			// NON - on ne fait rien
			System.out.println("\tLa table n'appartient pas � cet employ� elle n'est pas d�sassign�e.");
		}
	}
	
	public Table getSpecificTable(Table t) {
		return _aoTables.get(_aoTables.indexOf(t));
	}
}
