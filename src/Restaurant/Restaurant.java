package Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
	
	// - - - - - Attributes - - - - - //
	private ArrayList<Serveur> _serveurs;
 	private ArrayList<Table> _tables;
	
 	// Arbitrairement on choisit que le service n'ai pas commenc� lors de l'instanciation d'un restaurant
	private boolean _serviceADebute = false;
	private boolean _serviceEstFini = true;
	private MaitreHotel _maitreHotel;
	
	
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
		_maitreHotel = new MaitreHotel(_tables);
	}
	
//	public Restaurant( int nbTable, int nbServeur) { 
//		_serveurs = new ArrayList<Serveur>();
//		_tables = new ArrayList<Table>();
//		for(int j = 0; j<nbTable; j++) {
//			_tables.add(new Table());
//		}
//		_maitreHotel = new MaitreHotel(_tables);
//	}
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
		System.out.println("- - - Service commence - - -");
		_serviceADebute = true;
		_serviceEstFini = false;
	}
	
	public void serviceTermine() {
		System.out.println("- - - Le service termine - - -");
		_serviceADebute = false;
		_serviceEstFini = true;
		
		// il faut desassigner toutes les tables des serveurs et les r�assigner au maitre d'h�tel
		System.out.println("D�saffectation des tables des  serveurs: START");
		ArrayList<Table> taj= new ArrayList<Table>();
		for(Serveur s : _serveurs) {
			 taj = s.desassignerTableCarServiceTermine();
		}
		for(Table t: _tables) {
			System.out.println(t);
		}
		System.out.println("Table � jour");
		for(Table t: taj) {
			System.out.println(t);
		}
		System.out.println("D�saffectation des tables des  serveurs: END");
		
		// et les r�assigner au maitre d'h�tel
		System.out.println("R�assignation au maitre d'h�tel: START");
		System.out.println(_maitreHotel.nbTableAffectees());
		_maitreHotel.assignerTablesCarMaitreHotel(_tables);
		System.out.println(_maitreHotel.nbTableAffectees());
		System.out.println("R�assignation au maitre d'h�tel: END");
		System.out.println("- - - Service est termin� - - -");
		
	}

	public ArrayList<Table> get_tables() {
		return _tables;
	}

	public void set_tables(ArrayList<Table> _tables) {
		this._tables = _tables;
	}
	
	
	public void assignerTable(Employe e, Table t) {
		// Est ce que l'employ� est le maitre d'h�tel ? 
		if(e.getClass().equals(_maitreHotel.getClass())) {
			// OUI - alors on lui assigne la table
			_maitreHotel.assignerTable(t);
		}else {
			// Non - est ce que le service a d�j� d�but�
			// On l'assigne au serveur 
			e.assignerTable(t);
			// on v�rifie 
			System.out.println("Le serveur poss�de: "+e.nbTableAffectees()+" tables affect�es.");
			System.out.println(("Le ser"));
			// on l'enl�ve des tables assign�es au maitre d'hotel
			_maitreHotel.desassignerUneTable(t);
			
		}
	}
	
}


