package Restaurant;

public class Plat {
	
	// - - - - - Attributes - - - - - // 
	private String _sNom;
	private double _dPrix;
	
	
	
	// - - - - - Constructors - - - - - //
	public Plat(String sNom, double dPrix) {
		super();
		_sNom = sNom;
		_dPrix = dPrix;
	}
	// - - - - - GETTERS && SETTERS - - - - - //
	public String getsNom() {
		return _sNom;
	}
	public void setsNom(String sNom) {
		_sNom = sNom;
	}
	public double getdPrix() {
		return _dPrix;
	}
	public void setdPrix(double dPrix) {
		_dPrix = dPrix;
	}
	@Override
	public String toString() {
		return "Plat [sNom=" + _sNom + ", dPrix=" + _dPrix + "]";
	}
	
	
}
