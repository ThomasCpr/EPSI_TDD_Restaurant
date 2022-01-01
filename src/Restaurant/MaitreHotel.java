package Restaurant;

import java.util.ArrayList;

public class MaitreHotel extends Employe {
	
	
	public MaitreHotel() {
		super();
	}
	public MaitreHotel(ArrayList<Table> tables) {
		super(tables);
	}
	
	public void assignerTablesCarMaitreHotel(ArrayList<Table> tablesAReassigner) {
		for(Table t : tablesAReassigner) {
			t.set_employeAssigne(this);
		}
		set_tables(tablesAReassigner);
	}
	
	

}
