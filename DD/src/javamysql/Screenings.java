package javamysql;

import java.sql.Connection;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Screenings extends Tab {
	
	Label result = new Label();
	
	public Screenings(Connection con){
		
		setText("Screenings");
		Text screeningsTitle = new Text("Screenings");
		screeningsTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		
		//New query to fill up the screening list.
		SQLQuery screeningListQuery = new SQLQuery();
		ListView<String> screeningList = screeningListQuery.getScreeningList(con);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Text addScreeningTitle = new Text("Add Screening");
		addScreeningTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		Label addMovieNoLabel = new Label("Movie:");
		addMovieNoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		GridPane.setConstraints(addMovieNoLabel, 0, 0);
		
		//New query to fill up the movie list
		SQLQuery movieListQuery = new SQLQuery();
		ListView<String> addMovieNoListInput = movieListQuery.getMovieNoList(con);
		GridPane.setConstraints(addMovieNoListInput, 0, 1);
		
		
		Label addTheatreNoLabel = new Label("Theatre:");
		GridPane.setConstraints(addTheatreNoLabel, 1, 0);
		
		//New query to fill up the theatre list
		SQLQuery theatreListQuery = new SQLQuery();
		ListView<String> addTheatreNoListInput = theatreListQuery.getTheatreNoList(con);
		GridPane.setConstraints(addTheatreNoListInput, 1, 1);
		
		
		Label addEmployeeNoLabel = new Label("Screened By:");
		GridPane.setConstraints(addEmployeeNoLabel, 2, 0);

		//New query to fill up the employee list
		SQLQuery employeeListQuery = new SQLQuery();
		ListView<String> addEmployeeNoListInput = employeeListQuery.getEmployeeNoList(con);
		GridPane.setConstraints(addEmployeeNoListInput, 2, 1);
		
		
		Label addDateLabel = new Label("Date: (YYYY/MM/DD)");
     	GridPane.setConstraints(addDateLabel, 0, 2);
     
     	TextField addDateInput = new TextField();
		GridPane.setConstraints(addDateInput, 0, 3);
		
		Label addTimeLabel = new Label("Time: (HH:MM:SS)");
     	GridPane.setConstraints(addTimeLabel, 1, 2);
     
     	TextField addTimeInput = new TextField();
		GridPane.setConstraints(addTimeInput, 1, 3);
		
		//Add screening button
		Button addScreeningButton = new Button("Add Screening");
		
		
		GridPane.setConstraints(addScreeningButton, 2, 3);
		GridPane.setConstraints(result, 1, 4);
		
		
		addScreeningButton.setOnAction(e -> {

			String chosenMovie = "";
			
			
			
			//Obtains the selected movie
			for(String m: addMovieNoListInput.getSelectionModel().getSelectedItems()){
				
				chosenMovie += m;
			
			}
			
			String chosenTheatre = "";
			
			//Obtains the selected theatre
			for(String m: addTheatreNoListInput.getSelectionModel().getSelectedItems()){
				
				chosenTheatre += m;
			
			}
			chosenTheatre += " ";
			String chosenEmployee = "";
			
			//Obtains the selected employee
			for(String m: addEmployeeNoListInput.getSelectionModel().getSelectedItems()){
				
				chosenEmployee += m;
			
			}
			
				//New insert query.
				InsertRecord insertNewScreening = new InsertRecord();
				boolean screeningInserted = insertNewScreening.insertScreening(con, chosenMovie, chosenTheatre, chosenEmployee, addDateInput.getText(),addTimeInput.getText());

				
				//If query was successful
				if (screeningInserted == true) {

					//Creating IDs from the string
					chosenMovie = chosenMovie.charAt(0) +""+ chosenMovie.charAt(1) +""+ chosenMovie.charAt(2) +""+ chosenMovie.charAt(3) +""+ chosenMovie.charAt(4) ;
					chosenTheatre = chosenTheatre.charAt(0) +""+ chosenTheatre.charAt(1) +"";
					chosenEmployee = chosenEmployee.charAt(0) +""+ chosenEmployee.charAt(1) +""+ chosenEmployee.charAt(2) +""+ chosenEmployee.charAt(3) +""+ chosenEmployee.charAt(4) ;
				    	
					
					
					//String to be added to list
					String screeningAdded = (addDateInput.getText()+ "\t  " +addTimeInput.getText() + "\t " +  chosenTheatre + "\t " + chosenMovie + "\t" + chosenEmployee);

					//Boxes are cleared.
					addDateInput.clear();
					addTimeInput.clear();
					
					//Screening is added to list.
					screeningList.getItems().add(screeningAdded);
					result.setText("Screening added");
				 
				} else {
					result.setText("Error.");
			}
				screeningList.refresh();
		});
		
		//Delete button
		Button deleteScreeningButton = new Button("Delete Screening");
		GridPane.setConstraints(deleteScreeningButton, 0, 4);
		
		
		deleteScreeningButton.setOnAction(g -> {
	         	
			 
			 	String screening = "";
	        	String chosenScreening = "";
	        	ObservableList<String> screenings;
	    		screenings = screeningList.getSelectionModel().getSelectedItems();
	    		
	    		//Obtains selected screening
	    		for(String m: screenings){
	        	
	    			chosenScreening += m;
	    			
	    			for(int k = 0; k < 25; k++){
	    				
	    				
	    				screening += chosenScreening.charAt(k) +"";
	    			}
	    		}
	    	
	    		//New delete query
	    		DeleteRecord deleteScreeningQuery = new DeleteRecord();
	    		boolean movieScreening = deleteScreeningQuery.deleteScreening(con,screening);
	    		
	    		//If query was successful
	    		if (movieScreening == true){
	    			
	    			//Screening is removed from list.
	    			screeningList.getItems().remove(chosenScreening);
	    		}
	    		else{
	    			result.setText("Error.");
	    		}
	         });
		
		
		grid.getChildren().addAll(addTheatreNoLabel, addTheatreNoListInput, addMovieNoLabel, addMovieNoListInput, addEmployeeNoLabel, addEmployeeNoListInput, addDateLabel, addDateInput, addTimeLabel, addTimeInput, addScreeningButton, deleteScreeningButton,result);
		grid.setAlignment(Pos.CENTER);

		
	

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(screeningsTitle, screeningList,addScreeningTitle,grid);
		
		
		
		
		
		setContent(layout);
	}

	
}



