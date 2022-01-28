package Restaurant;

import java.util.ArrayList;

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
	private double _dPourcentageDeDifference;
	
	// - - - - - Constructors - - - - - //
	
	public Restaurant() {
		_oMaitreHotel = new MaitreHotel();
		 _aoServeurs = new ArrayList<Serveur>();
		 _aoTables = new ArrayList<Table>();
		 _aoMenuRestaurant = new ArrayList<Menu>();
		 _oCuisine = new Cuisine(this);
		 _dPourcentageDeDifference = 0.0;
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
		_oCuisine = new Cuisine(this);
		_dPourcentageDeDifference = 0.0;
	}
	
	
	public Restaurant(int nTable, int nServeur, double dPourcentage) {
		_oMaitreHotel = new MaitreHotel();
		 _aoTables = new ArrayList<Table>();
		 for(int j = 0; j<nTable; j++) {
				_aoTables.add(new Table());
		}
		 _oMaitreHotel.assignerTablesCarMaitreHotel(_aoTables); // on lui ajoute automatiquement les tables
		 _oCuisine = new Cuisine(this);
		 _aoServeurs = new ArrayList<Serveur>();
		 for(int cpt = 0; cpt< nServeur; cpt++) {
			 recruterServeur(new Serveur());
		 }
		 _aoMenuRestaurant = new ArrayList<Menu>();
		 _dPourcentageDeDifference = dPourcentage;
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
	public double get_dPourcentageDeDifference() {
		return _dPourcentageDeDifference;
	}
	public void set_dPourcentageDeDifference(double _dPourcentageDeDifference) {
		this._dPourcentageDeDifference = _dPourcentageDeDifference;
	}

	// - - - - - METHODS - - - - - //
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
	/**
	 * Permet d'assigner une table à un employé.
	 * Gère le passage d'une table d'un maître d'hôtel à à serveur
	 * @param e l'employé (serveur ou maitre d'hôtel) à qui va être assigné la table
	 * @param t la table à assigner à l'employ"
	 */
	public void assignerTable(Employe e, Table t) {
		System.out.println("Restaurant Class: assignerTable(Employe "+e+", Table "+t+")");
		// Est ce que l'employé est le maitre d'hôtel ? 
		if(e.getClass().equals(_oMaitreHotel.getClass())) {
			// OUI - alors on lui assigne la table
			_oMaitreHotel.assignerTable(t);
		}else {
			// Non - on l'enlève des tables assignées au maitre d'hotel
			_oMaitreHotel.desassignerUneTable(t);
			e.assignerTable(t); // On l'assigne au serveur 
		}
	}
	/**
	 * Permet d'ajouter le serveur au restaurant tout en lui indiquant quelle est sa cuisine 
	 * @param s <Serveur> le serveur recruté
	 */
	public void recruterServeur(Serveur s) {
		_aoServeurs.add(s);
		s.set_oCuisine(_oCuisine);
	}
	
	/**
	 * Permet de récupérer auprès des serveurs les commandes à transmettre à la gendarmerie
	 */
	public void recupererCommandesATransmettre() {
		System.out.println("Restaurant Class: recupererCommandesATransmettre()");
		// Pour chaque serveur
		for(Serveur ser : _aoServeurs) {
			// On regarde ces commandes
			for(Commande com: ser.getAllCommandes()) {
				if(com.bATransmettre()) {
					// si elles sont à transmettre on les ajoute à la liste
					_aoCommandeATransmettre.add(com);
				}
			}
		}
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
	 * Permet de retrouver un menu parmis la carte du restaurant avec son nom
	 * @param sNomDuMenu le nom du menu recherché
	 * @return le menu recherché, null s'il n'existe pas
	 */
	public Menu getMenuByName(String sNomDuMenu) {
		Menu menuRecherche = null;
		for(Menu menuAVerifier: get_aoMenuRestaurant()) {
			if(menuAVerifier.get_sName().equals(sNomDuMenu)) {
				menuRecherche = menuAVerifier;
			}
		}
		return menuRecherche;
	}
	/**
	 * Permet à un restaurant de créer son propre menu.
	 * @param _sNom
	 */
	public void createMenu(String _sNomMenu, String sNomPlat, double dPrixPlat) {
		Menu menu = new Menu(_sNomMenu, sNomPlat,dPrixPlat);
		_aoMenuRestaurant.add(menu);
	}
	/**
	 * Permet d' ajouter un menu à la carte
	 * @param menuAAjouter
	 */
	public void addMenuCarte(Menu menuAAjouter) {
		_aoMenuRestaurant.add(menuAAjouter);
	}
	
	/**
	 * Permet d'ajouter un plat et son prix à un menu existant
	 * @param sNomMenu <String> le nom du menu
	 * @param sNomPlat <String> le nom du polat
	 * @param dPrixPlat <double> le prix du plat
	 */
	public void addPlatToMenu(String sNomMenu, String sNomPlat, double dPrixPlat) {
		getMenuByName(sNomMenu).get_aoPlatsDuMenu().add(new Plat(sNomPlat, dPrixPlat));
	}

	
	/**
	 * Permet de retourner tous les plats de tous les menus du restaurant (avec le prix associé)
	 * @return
	 */
	public ArrayList<Plat> getAllPlats(){
		ArrayList<Plat> listPlats = new ArrayList<Plat>();
		// Pour chaque menu du restaurant
		for(Menu menu: _aoMenuRestaurant) {
			// on parcourt les platset on les ajoutes 
			for(Plat plat: menu.get_aoPlatsDuMenu()) {
				listPlats.add(plat);
			}	
		}
		return listPlats;
	}
	/**
	 * Supprime un plat de la carte du restaurant
	 * @param platASupprimer le plat à supprimer
	 */
	public void supprimerPlatFautDeStock(Plat platASupprimer) {
		// Pour chaque menu
		for(Menu menu: _aoMenuRestaurant) {
			// s'il contient le plat on le supprime
			if(menu.get_aoPlatsDuMenu().contains(platASupprimer)) {
				menu.get_aoPlatsDuMenu().remove(platASupprimer);
			}
		}
	}
	/**
	 * Permet de remplacer le plat commandé par un client après le refus par la cuisine en:
	 * - verifiant que le plat demandé est bien sur la carte
	 * - vérifiant que la différence de prix est conforme.
	 * @param oCommandeAModifier
	 * @param platARemplacer
	 * @param sNomPlatDeRemplacement
	 */
	public void proposerPlatRemplacement(Commande oCommandeAModifier,String platARemplacer, String sNomPlatDeRemplacement) {
		System.out.println("Restaurant Class: proposerPlatRemplacement(Commande oCommandeAModifier,String platARemplacer, String sNomPlatDeRemplacement)");
		Plat platDeRemplacement = null;
		// On vérifie que le plats existee
		// pour chaque plat de la carte on regarde si c'est celui qu'on cherche
		for(Plat platAVerifier : getAllPlats()) {
			// si c'est le plat que l'on cherche 
			if(platAVerifier.getsNom().equals(sNomPlatDeRemplacement)) {
				platDeRemplacement = platAVerifier;
				break;
			}
		}
		System.out.println("Plat de remplacement '"+platDeRemplacement.getsNom()+"' est disponible.");
		// si le plat existe 
		if(platDeRemplacement != null) {
			Plat platASupprimer = oCommandeAModifier.getPlatByName(platARemplacer);
			// on vérifie que la diff de prix soit bien inférieurs aux pourcentage
			if(estPrixAutorise(platDeRemplacement.getdPrix(), platASupprimer.getdPrix())) {
				
				// Oui alors on remplace
				platASupprimer.setsNom(sNomPlatDeRemplacement);
			}
		}
		
	}
	
	/**
	 * Permet de savoir si le remplacement d'un plat est aurotisé et respecte bien la règle:
	 * le prix du nouveau plat est au prix du plat d'origine si la différence est inférieur à un pourcentage fixé 
	 * Ici on prendra le prix du plat d'origine pour calculer le pourcentage
	 * @param prixPlatOrigine
	 * @param PrixNouveauPlat
	 * @return Vrai si respecte la règle , false sinon
	 */
	public boolean estPrixAutorise(double prixPlatOrigine, double PrixNouveauPlat) {
		System.out.println("_dPourcentageDeDifference*(prixPlatOrigine): "+_dPourcentageDeDifference*(prixPlatOrigine));
		return (prixPlatOrigine - PrixNouveauPlat) <=  _dPourcentageDeDifference*(prixPlatOrigine);
	}

}


