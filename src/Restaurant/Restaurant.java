package Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
	
	// - - - - - Attributes - - - - - //
	private ArrayList<Serveur> _serveurs;
 	private ArrayList<Table> _tables;
	private boolean _serviceADebute = false;  	// Arbitrairement on choisit que le service n'ai pas commenc� lors de l'instanciation d'un restaurant
	private boolean _serviceEstFini = true;
	private MaitreHotel _maitreHotel;
	private ArrayList<Commande> _commandeATransmettre;
	
	// - - - - - Constructors - - - - - //
	
	public Restaurant() {
		_maitreHotel = new MaitreHotel();
		 _serveurs = new ArrayList<Serveur>();
		 _tables = new ArrayList<Table>();
	}
	
	public Restaurant( int nbTable) { 
		_serveurs = new ArrayList<Serveur>();
		_tables = new ArrayList<Table>();
		for(int j = 0; j<nbTable; j++) {
			_tables.add(new Table());
		}
		_maitreHotel = new MaitreHotel(); // on instancie le MH
		_maitreHotel.assignerTablesCarMaitreHotel(_tables); // on lui ajoute automatiquement les tables
		_commandeATransmettre = new ArrayList<Commande>();
	}

	// - - - - - Methods - - - - - //
	/**
	 * chiffre d'affaire deu restaurant
	 * @return som (double) la somme des chiffres d'affaires des serveurs
	 */
	public double chiffreAffaire() {
		double som = 0.0;
		for(Serveur serv : _serveurs) {
			som+=serv.getChiffreAffaire();
		}
		
		return som;
	}
	public ArrayList<Serveur> get_serveurs() {
		return _serveurs;
	}
	public void set_serveurs(ArrayList<Serveur> _serveurs) {
		this._serveurs = _serveurs;
	}
	public MaitreHotel get_maitreHotel() {
		return _maitreHotel;
	}
	public void set_maitreHotel(MaitreHotel _maitreHotel) {
		this._maitreHotel = _maitreHotel;
	}

	public void serviceCommence() {
		System.out.println("\nRestaurant Class: serviceCommence()");
		_serviceADebute = true;
		_serviceEstFini = false;
	}
	
	public void serviceTermine() {
		System.out.println("\nRestaurant Class: serviceTermine()");
		_serviceADebute = false;
		_serviceEstFini = true;
		// il faut desassigner toutes les tables des serveurs et les r�assigner au MH
		System.out.println("\tSuppression des tables affect�es aux serveur.");
		// Pour chaque serveur on retire ses tables assign�es
		for(Serveur s : _serveurs) {
			 s.set_tables(new ArrayList<Table>()); 
		}
		// Pour chaque tables on enl�ve l'employe assigne et on met le MH
		System.out.println("\tSuppresion des Serveur assign�s aux tables et remplacement par MH");
		for(Table t: _tables) {
			t.set_employeAssigne(_maitreHotel);
		}
		System.out.println("\tService termin�: Table � jour");
		// on mets � jour les tables du MH
		System.out.println(("\t MAJ des tables du maitre d'h�tel:"));
		_maitreHotel.set_tables(_tables);
		
	}

	public ArrayList<Table> get_tables() {
		return _tables;
	}
	public void set_tables(ArrayList<Table> _tables) {
		this._tables = _tables;
	}
	
	public void assignerTable(Employe e, Table t) {
		System.out.println("\nRestaurant Class: assignerTable(Employe "+e+", Table "+t+")");
		// Est ce que l'employ� est le maitre d'h�tel ? 
		if(e.getClass().equals(_maitreHotel.getClass())) {
			// OUI - alors on lui assigne la table
			System.out.println("\tL'employ� est le ma�tre d'h�tel donc on lui affecte la table");
			_maitreHotel.assignerTable(t);
		}else {
			// Non - est ce que le service a d�j� d�but� ?
			System.out.println("\tAvant assignation\n\t\tRestaurant: "+_tables.size()+" tables: "+_tables+"\n\t\tMaitreHotel: "+_maitreHotel.nbTableAffectees()+" tables:"+_maitreHotel.get_tables()+"\n\t\tServeur: "+e.nbTableAffectees()+" tables:"+e.get_tables());			
			// on l'enl�ve des tables assign�es au maitre d'hotel
			_maitreHotel.desassignerUneTable(t);
			e.assignerTable(t); // On l'assigne au serveur 
			// on v�rifie
			System.out.println("\tApr�s assignation\n\t\tRestaurant: "+_tables.size()+" tables: "+_tables+"\n\t\tMaitreHotel: "+_maitreHotel.nbTableAffectees()+" tables:"+_maitreHotel.get_tables()+"\n\t\tServeur: "+e.nbTableAffectees()+" tables:"+e.get_tables());
			
		}
	}

	public ArrayList<Commande> get_commandeATransmettre() {
		return _commandeATransmettre;
	}

	public void set_commandeATransmettre(ArrayList<Commande> _commandeATransmettre) {
		this._commandeATransmettre = _commandeATransmettre;
	}

	public void recupererCommandesATransmettre() {
		System.out.println("Restaurant Class: recupererTableATransmettre()");
		// Pour chaque serveur
		for(Serveur ser : _serveurs) {
			// On regarde ces commandes
			for(Commande com: ser.get_commandes()) {
				if(com.bATransmettre()) {
					// si elles sont � transmettre on les ajoute � la liste
					_commandeATransmettre.add(com);
				}
			}
			
		}
	}
	
	
	public void transmettreCommandeGendarmerie() {
		for(Commande com : _commandeATransmettre) {
			com.set_bTransmiste(true);
		}
	}
	
	public ArrayList<Table> get_tablesLibres(){
		ArrayList<Table> tableLibres = new ArrayList<Table>();
		for(Table table : _tables) {
			if(table.is_estLibre()) {
				tableLibres.add(table);
			}
		}
		return tableLibres;
	}
}


