package Utilities;

import java.util.ArrayList;

import Restaurant.Table;

public class TableGenerator {
	
	public ArrayList<Table> Generate(int nTables){
		 ArrayList<Table> tables =  new ArrayList<Table>();
		for(int i = 0; i<nTables;i++) {
			tables.add(new Table());
		}
		return tables;
	}
}
