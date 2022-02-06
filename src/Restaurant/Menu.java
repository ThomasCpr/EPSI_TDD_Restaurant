package Restaurant;


import java.util.ArrayList;

public class Menu {
	// - - - - - Attributes - - - - - // 
	private String _sName; // nom du menu
	private ArrayList<Plat> _aoPlatsDuMenu; // une liste de plat, un plat étant une association nom/prix
	
	// - - - - - Constructors - - - - - //
	public Menu(String _sName, String sPlatName, double dPrixPlat) {
		super();
		this._sName = _sName;
		this._aoPlatsDuMenu = new ArrayList<Plat>();
		addPlatToMenu(sPlatName, dPrixPlat);
	}
	
	
	// - - - - - GETTERS && SETTERS - - - - - //
	public String get_sName() {
		return _sName;
	}
	public ArrayList<Plat> get_aoPlatsDuMenu() {
		return _aoPlatsDuMenu;
	}

	// - - - - - METHODS - - - - - //
	
	/**
	 * Permet d'ajouter un plat et son prix au menu
	 * @param sNomPlat
	 * @param dPrix
	 */
	public void addPlatToMenu(String sNomPlat, double dPrix) {
		System.out.println("Le plat '"+sNomPlat+"' va être ajouté au menu '"+this._sName+"' pour : "+dPrix+"€.");
		this._aoPlatsDuMenu.add(new Plat(sNomPlat, dPrix));
	}
	
	/**
	 * Permet de récuper un objet plat grâce à son nom
	 * @param sNomPlat le nom du plat à récupérer 
	 * @return le plat cherché ou null si celui ci n'existe pas
	 */
	public Plat getPlatByName(String sNomPlat) {
		Plat platARetourner = null;
		// on parcourt tous les plats 
		for(Plat plat: get_aoPlatsDuMenu()) {
			if(plat.getsNom() == sNomPlat) {
				platARetourner = plat;
			}
		}
		return platARetourner;
	}
	
	
	@Override
	public String toString() {
		return "Menu [_sName=" + _sName + ", _aoPlatsDuMenu=" + _aoPlatsDuMenu + "]";
	}
	
	
	

}
