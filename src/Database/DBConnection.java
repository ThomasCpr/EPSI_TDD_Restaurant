package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	/**
	 * Permet d'instancier une connexion à la base de donnée
	 * @return l'instance le base de donnée
	 */
	  public static Connection connect() {
		  Connection con = null; 
		    try {
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:serveurDB.db"); // connexion à la bdd
		      System.out.println("Connected !");
		    } catch (ClassNotFoundException | SQLException e ) {
		      // TODO Auto-generated catch block
		      System.out.println("An exception has occured while connecting to Database: "+e.getMessage());
		    }
		    return con; 
	  }
	  
	  /**
	   * Permet de fermer une connexion
	   * @param rs ResultSet à close
	   * @param ps PReparedStatemeent à close
	   * @param con Connection à close
	   */
	  private static void Disconnect(ResultSet rs, PreparedStatement ps, Connection con) {
	      try {
	          rs.close();
	          ps.close();
	          con.close();
	      }
	      catch(SQLException exception) {
	          System.out.println("An exception has occurend while closing SQL connection: "+exception.getMessage());
	      }
	  }
	  
	  
	  
	  public static void readAllData() {
	        Connection con = DBConnection.connect(); 
	        PreparedStatement ps = null; 
	        ResultSet rs = null; 
	        
	        try {
	          String sql = "SELECT  * FROM  serveurs;";
	          ps = con.prepareStatement(sql); 
	          rs = ps.executeQuery();
	          System.out.println(rs +"ALL SERVEURS\n");
	          while(rs.next()) {
	            String id = rs.getString("id"); 
	            String name = rs.getString("name"); 
	            String tables = rs.getString("tables"); 
	            String CA = rs.getString("CA"); 
	            
	            System.out.println("Id: "+id);
	            System.out.println("Name: "+name);
	            System.out.println("Tables: "+tables);
	            System.out.println("CA: "+CA+"\n\n");
	            
	          }
	        } catch(SQLException e) {
	          System.out.println(e.toString());
	        } finally {
	            DBConnection.Disconnect(rs, ps, con);
	        }
	      }
	  


	  public static void readSpecificRow() {
	      // lets read specific row on the database
	      Connection con = DBConnection.connect(); 
	      PreparedStatement ps = null; 
	      ResultSet rs = null; 
	      try {    
	          String sql = "Select tables from serveurs where name = ? "; 
	          ps = con.prepareStatement(sql); 
	          ps.setString(1, "Jean");
	          rs = ps.executeQuery(); 
	          
	          String firstName = rs.getString(1); 
	          System.out.println(firstName);

	      } catch(SQLException e) {
	          System.out.println(e.toString());
	      } finally {
	          DBConnection.Disconnect(rs, ps, con);
	      }
	  }
	  public static String readName(int id) {
		  Connection con = DBConnection.connect(); 
	      PreparedStatement ps = null; 
	      ResultSet rs = null; 
	      try {    
	          String sql = "Select name from serveurs where id = ? "; 
	          ps = con.prepareStatement(sql); 
	          ps.setInt(1, id);
	          rs = ps.executeQuery(); 
	          
	          String name = rs.getString(1); 
	          System.out.println(name);
	          return name;
	      } catch(SQLException e) {
	          System.out.println(e.toString());
	      } finally {
	          DBConnection.Disconnect(rs, ps, con);
	      }
		return null;
		
	  }
	  
	  public static int readCA(String name) {
		  Connection con = DBConnection.connect(); 
	      PreparedStatement ps = null; 
	      ResultSet rs = null; 
	      try {    
	          String sql = "Select CA from serveurs where name = ? "; 
	          ps = con.prepareStatement(sql); 
	          ps.setString(1, name);
	          rs = ps.executeQuery(); 

	          int id = rs.getInt(1); 
	          System.out.println(id);
	          return id;
	      } catch(SQLException e) {
	          System.out.println(e.toString());
	      } finally {
	          DBConnection.Disconnect(rs, ps, con);
	      }
		return 0;
	  }
	  
	  public static void updateCA(double ca, int id) {
		    // we will update only first name of a certain row
		    Connection con = DBConnection.connect(); 
		    PreparedStatement ps = null; 
		    try {
		      String sql = "UPDATE serveurs set ca = ? WHERE id = ? ";
		      ps = con.prepareStatement(sql); 
		      ps.setDouble(1, ca);
		      ps.setInt(2, id);
		      ps.execute();
		      System.out.println("Data has been updated");
		    } catch (SQLException e) {
		      // TODO: handle exception
		      System.out.println(e.toString());
		    }
		  }
}