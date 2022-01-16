package Restaurant;

import java.util.Date;

public class Commande {
	// - - - - - Attributes - - - - - //
	private double _dMontantTotal;
	private boolean _bEpinglee = false;
	private Date _dDateEpinglage;
	private boolean _bTransmiste = false;
	private boolean _bNourriture; // true si la commande concerne de la nourriture, false sinon.
	
	// - - - - - Constructors - - - - - //
	public Commande(double _montant) {
		_dMontantTotal = _montant;
	}
	public Commande(boolean _bNourriture) {
		this._bNourriture = _bNourriture;
	}
	public Commande(double _montant, boolean _bNourriture) {
		_dMontantTotal = _montant;
		this._bNourriture = _bNourriture;
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
		this._bEpinglee = _bEpinglee;
	}	
	public void set_bEpingleAUneDate(boolean _bEpinglee, Date dateEpinglage) {
		this._bEpinglee = _bEpinglee;
		this._dDateEpinglage = dateEpinglage;	
	}	
	public Date get_dDateEpinglage() {
		return _dDateEpinglage;
	}
	public void set_dDateEpinglage(Date _dDateEpinglage) {
		this._dDateEpinglage = _dDateEpinglage;
	}
	public boolean is_bTransmiste() {
		return _bTransmiste;
	}
	public void set_bTransmiste(boolean _bTransmiste) {
		this._bTransmiste = _bTransmiste;
	}
	public boolean is_bNourriture() {
		return _bNourriture;
	}
	public void set_bNourriture(boolean _bNourriture) {
		this._bNourriture = _bNourriture;
	}
	
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
