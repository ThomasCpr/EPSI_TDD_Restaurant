package Restaurant;

import java.util.ArrayList;
import java.util.Date;

public class Commande {
	// - - - - - Attributes - - - - - //
	private double _dMontantTotal;
	private boolean _bEpinglee = false;
	private Date _dDateEpinglage;
	private boolean _bTransmiste = false;
	private boolean _bNourriture; // true si la commande concerne de la nourriture, false sinon.
	private ArrayList<Plat> _contenuCommande;
	
	// - - - - - Constructors - - - - - //
	public Commande() {
		_dMontantTotal = 0;
		this._bNourriture = false;
		_contenuCommande = new ArrayList<Plat>();
	}
	
	public Commande(double _montant, boolean _bNourriture) {
		_dMontantTotal = _montant;
		this._bNourriture = _bNourriture;
		_contenuCommande = new ArrayList<Plat>();
	}
	
	public Commande(Plat oPlat) {
		this._bNourriture = true;
		_contenuCommande = new ArrayList<Plat>();	
		_contenuCommande.add(oPlat);
		_dMontantTotal= oPlat.getdPrix();
		
	}

	
	// - - - - - GETTERS && SETTERS - - - - - //	
	public double get_dMontantTotal() {
		return _dMontantTotal;
	}
	public void set_dMontantTotal(double _dMontantTotal) {
		this._dMontantTotal = _dMontantTotal;
	}
	public boolean is_bEpinglee() {
		return _bEpinglee;
	}
	public void set_bEpinglee(boolean _bEpinglee) {
		_dDateEpinglage = new Date();
		this._bEpinglee = _bEpinglee;
	}	
	public void set_bEpingleAUneDate(boolean _bEpinglee, Date dateEpinglage) {
		this._bEpinglee = _bEpinglee;
		this._dDateEpinglage = dateEpinglage;	
	}	

//	public void set_dDateEpinglage(Date _dDateEpinglage) {
//		this._dDateEpinglage = _dDateEpinglage;
//	}
//	public boolean is_bTransmiste() {
//		return _bTransmiste;
//	}
	public void set_bTransmiste(boolean _bTransmiste) {
		this._bTransmiste = _bTransmiste;
	}
	public boolean is_bNourriture() {
		return _bNourriture;
	}
//	public void set_bNourriture(boolean _bNourriture) {
//		this._bNourriture = _bNourriture;
//	}
	public ArrayList<Plat> get_contenuCommande() {
		return _contenuCommande;
	}
//	public void set_contenuCommande(ArrayList<Plat> _contenuCommande) {
//		this._contenuCommande = _contenuCommande;
//	}

	// - - - - - Methods - - - - - //
	/**
	 * Propriété calculé: Calcul si la date d'épinglage est dépassée de 15 jours
	 * @return True si elle a dépassé les 15 jouurs et qu'il faut la transmettre, False sinon
	 */
	public boolean bATransmettre() {
		Date dToday = new Date(); // on prend la date du jour
		return (differenceEnJours(dToday, this._dDateEpinglage ) > 15); // on regarde si ça fais plus de 15 jours
	}
	
	/**
	 * Permet de retourner un des plats de la commande en cherchant par son nom 
	 * @param sNomPlat du plat recherché
	 * @return le plat recherché ou null
	 */
	public Plat getPlatByName(String sNomPlat) {
		Plat platARetourner = null;
		// on parcourt tous les plats 
		for(Plat plat: get_contenuCommande()) {
			if(plat.getsNom().equals(sNomPlat)) {
				platARetourner = plat;
			}
		}
		return platARetourner;
	}
	
	/**
	 * Calcul la différence de jours entre 2 dates 
	 * @param d1 une date
	 * @param d2 une date
	 * @return nb de jour, si > 0 alors d2 est une date antécédente à d1 
	 */
	private long differenceEnJours(Date d1, Date d2) {
		long diffEnTemps = d1.getTime() - d2.getTime();
		return (diffEnTemps / (1000*60*60*24))%365;  
		
	}


}
