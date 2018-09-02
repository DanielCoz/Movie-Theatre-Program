
package javamysql;
import java.sql.*;
import javafx.scene.control.ListView;

public class SQLQuery {
    
    /** Creates a new instance of SQLQuery */
    public SQLQuery() {
    }
    
    
    public ListView<String> getMoviesList(Connection con){
		
		 ListView<String> moviesList = new ListView<String>();

		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from movies";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	             
	            	//Obtains column values.
	            	String movieNo = rs.getString("movieNo");     
	            	String title = rs.getString("title");
	            	String genre = rs.getString("genre");
	            	String rating = rs.getString("rating");
	            	String runtime = rs.getString("runtime(mins)");
	              
	            	
	            	//Values added to string and string is added to list.
	            	String movieString =(movieNo + "\t  " + title + "\t " + genre + "\t " + rating + "\t " + runtime +"mins");
	            	 moviesList.getItems().add(movieString);   
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };  
		 return moviesList; 
	 }
    
    
    public ListView<String> getMovieNoList(Connection con){
		
		 ListView<String> moviesNoList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from movies";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	            	
	            	//Obtains column values.
	            	String movieNo = rs.getString("movieNo");
	            	String title = rs.getString("title");
	            	
	            	//Values added to string and string is added to list.
	            	String movieNoString = (movieNo + "\t" + title);
	            	moviesNoList.getItems().add(movieNoString);
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };  
		 return moviesNoList; 
	 }
    
    
    public ListView<String> getTheatreList(Connection con){
		
		 ListView<String> theatreList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from theatre";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	             
	            	//Obtains column values.
	            	String theatreNo = rs.getString("theatreNo");     
	            	String capacity = rs.getString("capacity");
	            	String floor = rs.getString("floor");
	              
	            	//Values added to string and string is added to list.
	            	String theatreString =(theatreNo + "\t  " + capacity + "\t " + floor);
	            	theatreList.getItems().add(theatreString);  
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       }; 
		 return theatreList; 
	 }
    
    
    public ListView<String> getTheatreNoList(Connection con){
		
		 ListView<String> theatreList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from theatre";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	             
	            	//Obtains column value
	            	String theatreNo = rs.getString("theatreNo"); 
	              
	            	//Values added to string and string is added to list.
	            	String theatreString =(theatreNo);
	            	theatreList.getItems().add(theatreString);     
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };  
		 return theatreList; 
	 }
    
    
    
    public ListView<String> getEmployeeList(Connection con){
		
		 ListView<String> employeeList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from employees";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	             
	            	//Obtains column values.
	            	String employeeNo = rs.getString("employeeNo");     
	            	String fName = rs.getString("fName");
	            	String lName = rs.getString("lName");
	            	String dateOfBirth = rs.getString("dateOfBirth");
	            	String address = rs.getString("address");
	            	String hourlyPay = rs.getString("hourlyPay");
	              
	            	//Values added to string and string is added to list.
	            	String employeeString =(employeeNo + "\t  " + fName + "\t " + lName+ "\t " + dateOfBirth + "\t " + address + "\t " + hourlyPay);
	            	employeeList.getItems().add(employeeString);   
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };
		 return employeeList; 
	 }
    
    
    public ListView<String> getEmployeeNoList(Connection con){
		
		 ListView<String> employeeNoList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from employees";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	           
	            while( rs.next()) { 
	             
	            	//Obtains column values.
	            	String employeeNo = rs.getString("employeeNo");     
	            	String fName = rs.getString("fName");
	            	String lName = rs.getString("lName");
	              
	            	//Values added to string and string is added to list.
	            	String employeeNoString =(employeeNo + "\t  " + fName + "\t " + lName);
	            	employeeNoList.getItems().add(employeeNoString);    
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };  
		 return employeeNoList; 
	 }
    
    
    
	public ListView<String> getScreeningList(Connection con) {
		 
		
		ListView<String> screeningList = new ListView<String>();
		 
		 try {
	            Statement stmt = con.createStatement();
	            String qry = "select * from screenings";
	            ResultSet rs = stmt.executeQuery(qry);
	            
	            while( rs.next()) { 
	             
	            	//Obtains column values.
	            	String date = rs.getString("date");     
	            	String time = rs.getString("time");
	            	String theatreNo = rs.getString("theatreNo");
	            	String movieNo = rs.getString("movieNo");
	            	String screenedBy = rs.getString("screenedBy");
	              
	            	//Values added to string and string is added to list.
	            	String screeningString =(date + "\t  " + time+ "\t " + theatreNo + "\t " + movieNo + "\t " + screenedBy);
	            	screeningList.getItems().add(screeningString);     
	            }
	        rs.close();
	        stmt.close();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };  
		 return screeningList; 
		 
	}
}
