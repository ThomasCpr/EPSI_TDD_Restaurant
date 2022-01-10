package Restaurant;

public class Table {
	
	// - - - - - Attributes - - - - - // 
	private Employe _oEmployeAssigne;
	private boolean _bEstLibre = true; // on décide qu'une nouvelle table est toujours libre
	// - - - - - Constructors - - - - - //
	public Table() {
		
	}
	public Table(Employe e ) {
		_oEmployeAssigne = e;
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
	
	
	
	
}
