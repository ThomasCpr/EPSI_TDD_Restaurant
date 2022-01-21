package Restaurant;

import java.util.ArrayList;

public class Table {
	
	// - - - - - Attributes - - - - - // 
	private Employe _oEmployeAssigne;
	private boolean _bEstLibre = true; // on décide qu'une nouvelle table est toujours libre
	private ArrayList<Commande> _aoCcommandes;
	
	// - - - - - Constructors - - - - - //
	public Table() {
		_aoCcommandes = new ArrayList<Commande>();
	}
	public Table(Employe e ) {
		_oEmployeAssigne = e;
		_aoCcommandes = new ArrayList<Commande>();
	}
	// - - - - - Methods - - - - - //
	
	public Employe get_oEmployeAssigne() {
		return _oEmployeAssigne;
	}
	public void set_oEmployeAssigne(Employe _oEmployeAssigne) {
		this._oEmployeAssigne = _oEmployeAssigne;
	}
	public boolean is_bEstLibre() {
		return _bEstLibre;
	}
	public void set_bEstLibre(boolean _bEstLibre) {
		this._bEstLibre = _bEstLibre;
	}
	public ArrayList<Commande> get_aoCcommandes() {
		return _aoCcommandes;
	}
	public void set_aoCcommandes(ArrayList<Commande> _aoCcommandes) {
		this._aoCcommandes = _aoCcommandes;
	}
	
	public double get_TotalTable() {
		double dTotalTable = 0.0;
		for(Commande com: _aoCcommandes) {
			dTotalTable+= com.get_dMontantTotal(); 
		}
		return dTotalTable;
	}
	
	
	
	
}
