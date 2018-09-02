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

public class Movies extends Tab{

	Label result = new Label();
	
	 public Movies(Connection con) {
		 
		 
		 setText("Movies");
		 Text moviesTitle = new Text("Movies");
		 moviesTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
    	
		 //Calls a query to fill up observable list.
		 SQLQuery movieListQuery = new SQLQuery();
		 ListView<String> moviesList = movieListQuery.getMoviesList(con);
    	 
    	 Text addMovieTitle = new Text("Add Movie");
    	 addMovieTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
    	
    	//Grid of labels and textfields
    	GridPane grid = new GridPane();
 		grid.setPadding(new Insets(10, 10, 10, 10));
 		grid.setVgap(8);
 		grid.setHgap(10);
     	
 		
     	Label addMovieNoLabel = new Label("MovieNo:");
     	GridPane.setConstraints(addMovieNoLabel, 0, 0);
     		
     	TextField addMovieNoInput = new TextField();
		GridPane.setConstraints(addMovieNoInput, 0, 1);
		
    	Label addTitleLabel = new Label("Title:");
     	GridPane.setConstraints(addTitleLabel, 1, 0);
     	
     	TextField addTitleInput = new TextField();
		GridPane.setConstraints(addTitleInput, 1, 1);
			
		
    	Label addGenreLabel = new Label("Genre:");
     	GridPane.setConstraints(addGenreLabel, 2, 0);
     	
     	TextField addGenreInput = new TextField();
		GridPane.setConstraints(addGenreInput, 2, 1);
			
		
    	Label addRatingLabel = new Label("Rating:");
     	GridPane.setConstraints(addRatingLabel, 3, 0);
     	
     	TextField addRatingInput = new TextField();
		GridPane.setConstraints(addRatingInput, 3, 1);
			
		
    	Label addRuntimeLabel = new Label("Runtime:");
     	GridPane.setConstraints(addRuntimeLabel, 4, 0);
     
     	TextField addRuntimeInput = new TextField();
		GridPane.setConstraints(addRuntimeInput, 4, 1);
		
		
		Button addMovieButton = new Button("Add Movie");
		
		//Add movie button
		addMovieButton.setOnAction(e -> {
			
			//Checks if integers are not strings.
			Boolean isMovieNoInt = isInt(addMovieNoInput, addMovieNoInput.getText());	
			Boolean isRuntimeInt = isInt(addRuntimeInput, addRuntimeInput.getText());	
			 
			
			if(isRuntimeInt == true && isMovieNoInt == true){
				
				//Creates new query to insert movie
				InsertRecord insertNewMovie = new InsertRecord(); 
				boolean movieInserted = insertNewMovie.insertMovie(con, Integer.parseInt(addMovieNoInput.getText()),addTitleInput.getText(),addGenreInput.getText(),addRatingInput.getText(),Integer.parseInt(addRuntimeInput.getText()));
				
				//If insert was successful
				if(movieInserted == true){
					
					//String is created to add to observable list.
					String movieAdded = (addMovieNoInput.getText() + "\t\t " +addTitleInput.getText() + "\t " + addGenreInput.getText() + "\t " + addRatingInput.getText() + "\t " + addRuntimeInput.getText()+ "mins");
					
					//Boxes are cleared
					addMovieNoInput.clear();
					addTitleInput.clear();
					addGenreInput.clear();
					addRatingInput.clear();
					addRuntimeInput.clear();
					
					//Movie added to observable list
					moviesList.getItems().add(movieAdded);
					result.setText("Movie added");
				}
				moviesList.refresh();
			}
		});
		
		
		//Delete movie button.
		Button deleteMovie = new Button("Delete Movie");
		
		 deleteMovie.setOnAction(g -> {
	         	
			 
			 	String movieNo = "";
	        	String chosenMovie = "";
	        	ObservableList<String> movies;
	    		movies = moviesList.getSelectionModel().getSelectedItems();
	    		
	    		//Gets the selected movie.
	    		for(String m: movies){
	        	
	    			chosenMovie += m;
	    			//MovieNo string is obtained from the string.
	    			movieNo = (chosenMovie.charAt(0) +""+  chosenMovie.charAt(1) +""+  chosenMovie.charAt(2) +""+  chosenMovie.charAt(3) +""+  chosenMovie.charAt(4) + "");
	    			
	    		}
	    		
	    	
	    		//New delete query.
	    		DeleteRecord deleteMovieQuery = new DeleteRecord();
	    		boolean movieDeleted = deleteMovieQuery.deleteMovie(con,movieNo);
	    		
	    		//If query was successful 
	    		if (movieDeleted == true){
	    			
	    			//Movie is removed from list.
	    			moviesList.getItems().remove(chosenMovie);
	    		}else{
	    			result.setText("Error.");
	    		}
	    		
	         });
		 
		 
	
		 
		GridPane.setConstraints(result, 2, 3);
		GridPane.setConstraints(deleteMovie, 0, 3);
		
		GridPane.setConstraints(addMovieButton, 0, 2);
		grid.getChildren().addAll(addMovieNoLabel,addMovieNoInput, addTitleLabel, addTitleInput, addGenreLabel, addGenreInput, addRatingLabel, addRatingInput, addRuntimeLabel, addRuntimeInput, addMovieButton, result, deleteMovie);
		grid.setAlignment(Pos.CENTER);
		
		
    	 
		
		 VBox layout = new VBox(10);
			layout.setPadding(new Insets(20, 20, 20, 20));
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().addAll(moviesTitle, moviesList, addMovieTitle, grid);
			
			setContent(layout);
	}
	
	 
	/**
	 * Method to check if inputed text is an integer.
	 */
	 
	 private boolean isInt(TextField input, String message){
	    	
	    	try{
	    		
	    		//Parses int
	    		int numEntered = Integer.parseInt(input.getText());
	    		
	    		if(numEntered > 0){
	    			return true;
	    		}
	    		else{
	    			
	    			result.setText("Invalid int.");
	    			return false;
	    		}
	    		
	    	}catch(NumberFormatException e){
	    		
	    		//If the entered textfield is not an int
	    		result.setText(message + " is not a number.");
	    		return false;
	    	}
	    } 
	 
}
