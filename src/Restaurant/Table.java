package Restaurant;

public class Table {
	private Employe _employeAssigne;
	
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
	
	
	
}
