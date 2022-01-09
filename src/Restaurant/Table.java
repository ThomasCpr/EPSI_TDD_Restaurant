package Restaurant;

public class Table {
	private Employe _employeAssigne;
	private boolean _estLibre = true; // on décide qu'une nouvelle table est toujours libre
	
	public Table() {
		
	}
	public Table(Employe e ) {
		_employeAssigne = e;
	}
	
	
	public Employe get_employeAssigne() {
		return _employeAssigne;
	}
	public void set_employeAssigne(Employe _employeAssigne) {
		this._employeAssigne = _employeAssigne;
	}
	public boolean is_estLibre() {
		return _estLibre;
	}
	public void set_estLibre(boolean _estLibre) {
		this._estLibre = _estLibre;
	}
	
	
	
	
}
