package javamysql;
import java.sql.*;


public class DeleteRecord {

	
	public DeleteRecord() {
	}

	
	public boolean deleteMovie(Connection con, String movieID) {

		boolean movieDeleted = false;
		try {
			
			Statement deleteStmt = con.createStatement();
			//Deletes row from table
			String deleteSQL = " Delete from movies where movieNo ='" + movieID + "'";
			int res = deleteStmt.executeUpdate(deleteSQL);
			System.out.println("The Number or records deleted is      " + res);
			
			deleteStmt.close();
			
			//If the query deleted any rows.
			if (res > 0) {
				movieDeleted = true;
			}
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		//Returns indication of if the delete was successful
		return movieDeleted;
	}

	public boolean deleteTheatre(Connection con, String theatreNo) {

		boolean theatreDeleted = false;
		try {
			
			Statement deleteStmt = con.createStatement();
			//Deletes row from table
			String deleteSQL = " Delete from theatre where theatreNo ='" + theatreNo + "'";
			int res = deleteStmt.executeUpdate(deleteSQL);
			System.out.println("The Number or records deleted is      " + res);
			
			deleteStmt.close();

			//If the query deleted any rows.
			if (res > 0) {
				theatreDeleted = true;
			}
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		//Returns indication of if the delete was successful
		return theatreDeleted;
	}

	public boolean deleteEmployee(Connection con, String employeeNo) {

		boolean employeeDeleted = false;
		try {

			
			Statement deleteStmt = con.createStatement();
			//Deletes row from table
			String deleteSQL = " Delete from employees where employeeNo ='" + employeeNo + "'";
			int res = deleteStmt.executeUpdate(deleteSQL);
			System.out.println("The Number or records deleted is      " + res);
			
			deleteStmt.close();

			//If the query deleted any rows.
			if (res > 0) {
				employeeDeleted = true;
			}
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		//Returns indication of if the delete was successful
		return employeeDeleted;
	}
	
	
	public boolean deleteScreening(Connection con, String screeningKey) {

		
		boolean screeningDeleted = false;
		try {
			
			//Obtains ID from string.
			String date = screeningKey.charAt(0) +"" + screeningKey.charAt(1) +"" + screeningKey.charAt(2) +"" + screeningKey.charAt(3) +"" + screeningKey.charAt(4) +""+  screeningKey.charAt(5) +"" + screeningKey.charAt(6) + screeningKey.charAt(7) +""+ screeningKey.charAt(8) +"" + screeningKey.charAt(9);
			String time = screeningKey.charAt(13) +"" + screeningKey.charAt(14) +""+screeningKey.charAt(15) +""+screeningKey.charAt(16) +""+screeningKey.charAt(17) +""+screeningKey.charAt(18) +""+screeningKey.charAt(19) +"" +screeningKey.charAt(20) +"" ;
			String theatre = screeningKey.charAt(23) +"" + screeningKey.charAt(24) +"";
			
			
			Statement deleteStmt = con.createStatement();
			//Deletes row from table
			String deleteSQL = " Delete from screenings where date ='" +date + "' AND time = '"+time+"' AND  theatreNo = '"+theatre+"'";
			int res = deleteStmt.executeUpdate(deleteSQL);
			System.out.println("The Number or records deleted is      " + res);
			
			deleteStmt.close();
			
			//If the query deleted any rows.
			if (res > 0) {
				screeningDeleted = true;
			}
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		//Returns indication of if the delete was successful
		return screeningDeleted;
	}
}