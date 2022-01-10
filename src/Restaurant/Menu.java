package Restaurant;


import java.util.HashMap;
import java.util.Map;

public class Menu {
	// - - - - - Attributes - - - - - // 
	private String _sName; // nom du menu
	private Map<String, Double> _msdPlatsDuMenu; // une liste de plat, un plat étant une association nom/prix
	
	// - - - - - Constructors - - - - - //
	public Menu(String _sName) {
		this._sName = _sName;
		_msdPlatsDuMenu = new HashMap<String,Double>();
	}
	
	public Menu(String _sName, Map<String, Double> _platsDuMenu) {
		super();
		this._sName = _sName;
		this._msdPlatsDuMenu = _platsDuMenu;
	}
	
	// - - - - - Methods - - - - - //
	public String get_sName() {
		return _sName;
	}
	public Map<String, Double> get_msdPlatsDuMenu() {
		return _msdPlatsDuMenu;
	}
	public void set_msdPlatsDuMenu(Map<String, Double> _platsDuMenu) {
		this._msdPlatsDuMenu = _platsDuMenu;
	}
	public void set_sName(String _sName) {
		this._sName = _sName;
	}

	@Override
	public String toString() {
		return "Menu [_sName=" + _sName + ", _msdPlatsDuMenu=" + _msdPlatsDuMenu + "]";
	}
	
	

}
