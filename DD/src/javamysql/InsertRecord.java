package javamysql;
import java.sql.*;

public class InsertRecord {

	public InsertRecord() {
	}

	public boolean insertMovie(Connection con, int movieNo, String title, String genre, String rating, int runtime) {

		boolean movieInserted = false;
		try {
			
			Statement insertStmt = con.createStatement();
			//Insert new row into table
			String insertSQL = " Insert into movies values ('" + movieNo + "', '" + title + "', '" + genre + "' , '"
					+ rating + "', '" + runtime + "')";
			int res = insertStmt.executeUpdate(insertSQL);
			System.out.println("The Number or records inserted is      " + res);
			
			insertStmt.close();

			movieInserted = true;

		} catch (Exception io) {
			System.out.println("error" + io);
		}
		;
		
		//Returns indication of if the insert was successful
		return movieInserted;
	}

	public boolean insertNewEmployee(Connection con, int employeeNo, String fName, String lName, String dateOfBirth,
			String address, String hourlyPay) {

		boolean employeeInserted = false;
		try {
			
			Statement insertStmt = con.createStatement();
			//Insert new row into table
			String insertSQL = " Insert into employees values ('" + employeeNo + "', '" + fName + "', '" + lName
					+ "' , '" + dateOfBirth + "', '" + address + "', '" + hourlyPay + "')";
			int res = insertStmt.executeUpdate(insertSQL);
			System.out.println("The Number or records inserted is " + res);
			
			insertStmt.close();

			employeeInserted = true;
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		;
		
		//Returns indication of if the insert was successful
		return employeeInserted;
	}

	public boolean insertScreening(Connection con, String movieNo, String theatreNo, String screenedBy, String date,
			String time) {

		boolean screeningInserted = false;

		try {
			
			//IDs are obtained from the string.
			movieNo = movieNo.charAt(0) + "" + movieNo.charAt(1) + "" + movieNo.charAt(2) + "" + movieNo.charAt(3) + ""
					+ movieNo.charAt(4);
			
			theatreNo = theatreNo.charAt(0) + "" + theatreNo.charAt(1) + "";
			screenedBy = screenedBy.charAt(0) + "" + screenedBy.charAt(1) + "" + screenedBy.charAt(2) + ""
					+ screenedBy.charAt(3) + "" + screenedBy.charAt(4);

			
			Statement insertStmt = con.createStatement();
			//Insert new row into table
			String insertSQL = " Insert into screenings values ('" + date + "', '" + time + "', '" + theatreNo + "' , '"
					+ movieNo + "', '" + screenedBy + "')";

			System.out.println(insertSQL);
			int res = insertStmt.executeUpdate(insertSQL);
			System.out.println("The Number or records inserted is " + res);
			
			screeningInserted = true;
			insertStmt.close();
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		;
		
		//Returns indication of if the insert was successful
		return screeningInserted;
	}

	public boolean insertTheatre(Connection con, int theatreNo, int capacity, int floor) {

		boolean theatreInserted = false;
		try {
			
			Statement insertStmt = con.createStatement();
			//Insert new row into table
			String insertSQL = " Insert into theatre values ('" + theatreNo + "', '" + capacity + "', '" + floor + "')";
			int res = insertStmt.executeUpdate(insertSQL);
			System.out.println("The Number or records inserted is " + res);
			
			insertStmt.close();

			theatreInserted = true;
		} catch (Exception io) {
			System.out.println("error" + io);
		}
		;

		//Returns indication of if the insert was successful
		return theatreInserted;
	}

}
