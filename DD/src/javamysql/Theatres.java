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

public class Theatres extends Tab {

	Label result = new Label();

	public Theatres(Connection con) {
		
		 setText("Theatres");
		Text theatreTitle = new Text("Theatres");
		theatreTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		//New query that fills up observable list.
		SQLQuery theatreListQuery = new SQLQuery();
		ListView<String> theatreList = theatreListQuery.getTheatreList(con);

		Text addTheatreTitle = new Text("Add Theatre");
		addTheatreTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		
		//Grid of labels and input fields
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label addTheatreNoLabel = new Label("TheatreNo:");
		GridPane.setConstraints(addTheatreNoLabel, 0, 0);

		TextField addTheatreNoInput = new TextField();
		GridPane.setConstraints(addTheatreNoInput, 0, 1);

		Label addCapacityLabel = new Label("Capacity:");
		GridPane.setConstraints(addCapacityLabel, 1, 0);

		TextField addCapacityInput = new TextField();
		GridPane.setConstraints(addCapacityInput, 1, 1);

		Label addFloorLabel = new Label("Floor:");
		GridPane.setConstraints(addFloorLabel, 2, 0);

		TextField addFloorInput = new TextField();
		GridPane.setConstraints(addFloorInput, 2, 1);

		//Add movie button
		Button addTheatreButton = new Button("Add Theatre");

		addTheatreButton.setOnAction(e -> {

			//Checks if all inputed are integers.
			Boolean isTheatreNoInt = isInt(addTheatreNoInput, addTheatreNoInput.getText());
			Boolean isCapacityInt = isInt(addCapacityInput, addCapacityInput.getText());
			Boolean isFloorNoInt = isInt(addFloorInput, addFloorInput.getText());

			if (isTheatreNoInt == true && isCapacityInt == true && isFloorNoInt == true) {
				
				//New insert query.
				InsertRecord insertNewTheatre = new InsertRecord();
				boolean theatreInserted = insertNewTheatre.insertTheatre(con,
						Integer.parseInt(addTheatreNoInput.getText()), Integer.parseInt(addCapacityInput.getText()),
						Integer.parseInt(addFloorInput.getText()));

				//If query was successful 
				if (theatreInserted == true) {
					
					//Creates string to add to list
					String theatreAdded = (addTheatreNoInput.getText() + "\t  " + addCapacityInput.getText() + "\t "
							+ addFloorInput.getText());

					//Clears boxes.
					addTheatreNoInput.clear();
					addCapacityInput.clear();
					addFloorInput.clear();

					//Adds new theatre to list.
					theatreList.getItems().add(theatreAdded);
					result.setText("Theatre added");
					// }
					theatreList.refresh();
				} else {
					// result.setText("Error.");
				}
			}
		});

		//Delete theater button
		Button deleteTheatre = new Button("Delete Selected Theatre");

		deleteTheatre.setOnAction(g -> {
			
			
				String theatreNo = "";
				String chosenTheatre = "";
				
				
				ObservableList<String> theatres;
				theatres = theatreList.getSelectionModel().getSelectedItems();
	
				//Obtains the selected theatre
				for (String m : theatres) {
	
					chosenTheatre += m;
					//Creates the theatre number
					theatreNo = chosenTheatre.charAt(0) + "" +  chosenTheatre.charAt(1) ;
	
				}
				
				//Creates new delete query.
				DeleteRecord deleteTheatreQuery = new DeleteRecord();
				boolean theatreDeleted = deleteTheatreQuery.deleteTheatre(con, theatreNo);
			
				//If query was successful
				if(theatreDeleted == true){
					//Removes theatre from list.
					theatreList.getItems().remove(chosenTheatre);
				}
			
			
			else{
				result.setText("Error.");
			}
			
			

		});

		GridPane.setConstraints(result, 1, 2);
		GridPane.setConstraints(deleteTheatre, 0, 3);

		GridPane.setConstraints(addTheatreButton, 0, 2);
		grid.getChildren().addAll(addTheatreNoLabel, addTheatreNoInput, addCapacityLabel, addCapacityInput,
				addFloorLabel, addFloorInput, addTheatreButton, deleteTheatre);
		grid.setAlignment(Pos.CENTER);

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(theatreTitle, theatreList, addTheatreTitle, grid,result );

		setContent(layout);
	}

	/**
	 * Method to check if inputed text is an integer.
	 */
	
	private boolean isInt(TextField input, String message) {

		try {

			// Parses int
			int numEntered = Integer.parseInt(input.getText());

			if (numEntered > 0) {
				return true;
			} else {

				result.setText("Invalid int.");
				return false;
			}

		} catch (NumberFormatException e) {

			// If the entered textfield is not an int
			result.setText(message + " is not a number.");
			return false;
		}
	}

}
