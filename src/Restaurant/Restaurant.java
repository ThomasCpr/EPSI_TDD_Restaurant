package Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
	
	// - - - - - Attributes - - - - - //
	
	private ArrayList<Serveur> _aoServeurs;
 	private ArrayList<Table> _aoTables;
	private boolean _bServiceADebute = false;  	// Arbitrairement on choisit que le service n'ai pas commencé lors de l'instanciation d'un restaurant
	private boolean _bServiceEstFini = true;
	private MaitreHotel _oMaitreHotel;
	private ArrayList<Commande> _aoCommandeATransmettre;
	private ArrayList<Menu> _aoMenuRestaurant;
	private Cuisine _oCuisine;
	
	
	// - - - - - Constructors - - - - - //
	
	public Restaurant() {
		_oMaitreHotel = new MaitreHotel();
		 _aoServeurs = new ArrayList<Serveur>();
		 _aoTables = new ArrayList<Table>();
		 _aoMenuRestaurant = new ArrayList<Menu>();
		 _oCuisine = new Cuisine();
	}
	
	public Restaurant( int nbTable) { 
		_aoServeurs = new ArrayList<Serveur>();
		_aoTables = new ArrayList<Table>();
		for(int j = 0; j<nbTable; j++) {
			_aoTables.add(new Table());
		}
		_oMaitreHotel = new MaitreHotel(); // on instancie le MH
		_oMaitreHotel.assignerTablesCarMaitreHotel(_aoTables); // on lui ajoute automatiquement les tables
		_aoCommandeATransmettre = new ArrayList<Commande>();
		_aoMenuRestaurant = new ArrayList<Menu>();
		_oCuisine = new Cuisine();
	}
	
	
	// - - - - - GETTERS && SETTERS - - - - - //
	
	public ArrayList<Serveur> get_aoServeurs() {
		return _aoServeurs;
	}
	public void set_aoServeurs(ArrayList<Serveur> _aoServeurs) {
		this._aoServeurs = _aoServeurs;
	}
	public MaitreHotel get_oMaitreHotel() {
		return _oMaitreHotel;
	}
	public void set_oMaitreHotel(MaitreHotel _oMaitreHotel) {
		this._oMaitreHotel = _oMaitreHotel;
	}
	public ArrayList<Table> get_aoTables() {
		return _aoTables;
	}
	public void set_aoTables(ArrayList<Table> _aoTables) {
		this._aoTables = _aoTables;
	}
	public ArrayList<Commande> get_aoCommandeATransmettre() {
		return _aoCommandeATransmettre;
	}
	public void set_aoCommandeATransmettre(ArrayList<Commande> _aoCommandeATransmettre) {
		this._aoCommandeATransmettre = _aoCommandeATransmettre;
	}	
	public ArrayList<Menu> get_aoMenuRestaurant() {
		return _aoMenuRestaurant;
	}
	public void set_aoMenuRestaurant(ArrayList<Menu> _aoMenuRestaurant) {
		this._aoMenuRestaurant = _aoMenuRestaurant;
	}
	public Cuisine get_oCuisine() {
		return _oCuisine;
	}

	public void set_oCuisine(Cuisine _oCuisine) {
		this._oCuisine = _oCuisine;
	}

	// - - - - - Methods - - - - - //
	/**
	 * chiffre d'affaire deu restaurant
	 * @return som (double) la somme des chiffres d'affaires des serveurs
	 */
	public double chiffreAffaire() {
		double som = 0.0;
		for(Serveur serv : _aoServeurs) {
			som+=serv.getdChiffreAffaire();
		}		
		return som;
	}
	
	public void serviceCommence() {
		System.out.println("\nRestaurant Class: serviceCommence()");
		_bServiceADebute = true;
		_bServiceEstFini = false;
	}
	
	public void serviceTermine() {
		System.out.println("\nRestaurant Class: serviceTermine()");
		_bServiceADebute = false;
		_bServiceEstFini = true;
		// il faut desassigner toutes les tables des serveurs et les réassigner au MH
		System.out.println("\tSuppression des tables affectées aux serveur.");
		// Pour chaque serveur on retire ses tables assignées
		for(Serveur s : _aoServeurs) {
			 s.set_aoTables(new ArrayList<Table>()); 
		}
		// Pour chaque tables on enlève l'employe assigne et on met le MH
		System.out.println("\tSuppresion des Serveur assignés aux tables et remplacement par MH");
		for(Table t: _aoTables) {
			t.set_oEmployeAssigne(_oMaitreHotel);
		}
		System.out.println("\tService terminé: Table à jour");
		// on mets à jour les tables du MH
		System.out.println(("\t MAJ des tables du maitre d'hôtel:"));
		_oMaitreHotel.set_aoTables(_aoTables);
		
	}
	
	public void assignerTable(Employe e, Table t) {
		System.out.println("\nRestaurant Class: assignerTable(Employe "+e+", Table "+t+")");
		// Est ce que l'employé est le maitre d'hôtel ? 
		if(e.getClass().equals(_oMaitreHotel.getClass())) {
			// OUI - alors on lui assigne la table
			System.out.println("\tL'employé est le maître d'hôtel donc on lui affecte la table");
			_oMaitreHotel.assignerTable(t);
		}else {
			// Non - est ce que le service a déjà débuté ?
			System.out.println("\tAvant assignation\n\t\tRestaurant: "+_aoTables.size()+" tables: "+_aoTables+"\n\t\tMaitreHotel: "+_oMaitreHotel.nbTableAffectees()+" tables:"+_oMaitreHotel.get_aoTables()+"\n\t\tServeur: "+e.nbTableAffectees()+" tables:"+e.get_aoTables());			
			// on l'enlève des tables assignées au maitre d'hotel
			_oMaitreHotel.desassignerUneTable(t);
			e.assignerTable(t); // On l'assigne au serveur 
			// on vérifie
			System.out.println("\tAprès assignation\n\t\tRestaurant: "+_aoTables.size()+" tables: "+_aoTables+"\n\t\tMaitreHotel: "+_oMaitreHotel.nbTableAffectees()+" tables:"+_oMaitreHotel.get_aoTables()+"\n\t\tServeur: "+e.nbTableAffectees()+" tables:"+e.get_aoTables());
			
		}
	}

	
	/**
	 * Permet de récupérer auprès des servuers les commandes à transmettre à la gendarmerie
	 */
	public void recupererCommandesATransmettre() {
		System.out.println("Restaurant Class: recupererCommandesATransmettre()");
		// Pour chaque serveur
		for(Serveur ser : _aoServeurs) {
			// On regarde ces commandes
//			for(Commande com: ser.get_aoCcommandes()) {
			for(Commande com: ser.getAllCommandes()) {
				if(com.bATransmettre()) {
					// si elles sont à transmettre on les ajoute à la liste
					_aoCommandeATransmettre.add(com);
				}
			}
		}
		System.out.println("Les commandes à transmettre sont: "+_aoCommandeATransmettre);
	}
	/**
	 * Permet de transmettre les commandes à la gendarmerie en 
	 * mettant à jour au passage le status des commandes 	
	 */
	public void transmettreCommandeGendarmerie() {
		for(Commande com : _aoCommandeATransmettre) {
			com.set_bTransmiste(true);
		}
		// on remet la liste la liste à vide 
		_aoCommandeATransmettre = new ArrayList<Commande>();
	}
	/**
	 * Renvoi une liste de table libres
	 * @return ArrayList<Table> les tables libres
	 */
	public ArrayList<Table> get_aoTablesLibres(){
		ArrayList<Table> tableLibres = new ArrayList<Table>();
		for(Table table : _aoTables) {
			if(table.is_bEstLibre()) {
				tableLibres.add(table);
			}
		}
		return tableLibres;
	}
	
	/**
	 * Renvoi le menu dont le nom est égale au paramètre sNameDuMenu (ici le nom d'un menu fait office de pseudo identifiant)
	 * @param sNomDuMenu
	 * @return l'index du menu, clairement de grosse faille sur cette méthode 
	 */
	public int get_iIndexMenuByName(String sNomDuMenu) {
		int indexOf = -1; // par défaut non trouvé
		for(Menu menu : _aoMenuRestaurant) {
			if(menu.get_sName().equals(sNomDuMenu)) {
				indexOf = _aoMenuRestaurant.indexOf(menu);
			}
		}
		return indexOf;
	}
	
	public void createMenu(String _sNom) {
		Menu menu = new Menu(_sNom);
		_aoMenuRestaurant.add(menu);
	}
	
	/**
	 * Permet d'ajouter un plat et son prix à un menu
	 * @param sNomMenu <String> le nom du menu
	 * @param sNomPlat <String> le nom du polat
	 * @param dPrixPlat <double> le prix du plat
	 */
	public void addPlatToMenu(String sNomMenu, String sNomPlat, double dPrixPlat) {
		_aoMenuRestaurant.get(get_iIndexMenuByName(sNomMenu)).get_msdPlatsDuMenu().put(sNomPlat, dPrixPlat);
	}
	/**
	 * Permet d'ajouter le serveur au restaurant tout en lui indiquant quelle est sa cuisine 
	 * @param s <Serveur> le serveur recruté
	 */
	public void recruterServeur(Serveur s) {
		_aoServeurs.add(s);
		s.set_oCuisine(_oCuisine);
	}
}


