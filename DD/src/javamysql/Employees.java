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

public class Employees extends Tab {

	Label result = new Label();

	public Employees(Connection con) {
		
		setText("Employees");
		Text employeeTitle = new Text("Employees");
		employeeTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		//New query to fill up the observable list.
		SQLQuery employeeListQuery = new SQLQuery();
		ListView<String> employeeList = employeeListQuery.getEmployeeList(con);

		Text addEmployeeTitle = new Text("Add Employee");
		addEmployeeTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		//Grid for labels and textfields.
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label addEmployeeNoLabel = new Label("EmployeeNo:");
		GridPane.setConstraints(addEmployeeNoLabel, 0, 0);

		TextField addEmployeeNoInput = new TextField();
		GridPane.setConstraints(addEmployeeNoInput, 0, 1);

		Label addfNameLabel = new Label("First Name:");
		GridPane.setConstraints(addfNameLabel, 1, 0);

		TextField addfNameInput = new TextField();
		GridPane.setConstraints(addfNameInput, 1, 1);

		Label addlNameLabel = new Label("Last Name:");
		GridPane.setConstraints(addlNameLabel, 2, 0);

		TextField addlNameInput = new TextField();
		GridPane.setConstraints(addlNameInput, 2, 1);
		
		Label addDateOfBirthLabel = new Label("DateOfBirth:");
		GridPane.setConstraints(addDateOfBirthLabel, 3, 0);

		TextField addDateOfBirthInput = new TextField();
		GridPane.setConstraints(addDateOfBirthInput, 3, 1);
		
		
		Label addAddressLabel = new Label("Address:");
		GridPane.setConstraints(addAddressLabel, 4, 0);

		TextField addAddressInput = new TextField();
		GridPane.setConstraints(addAddressInput, 4, 1);
		
		
		Label addHourlyPayLabel = new Label("Hourly Pay:");
		GridPane.setConstraints(addHourlyPayLabel, 5, 0);

		TextField addHourlyPayInput = new TextField();
		GridPane.setConstraints(addHourlyPayInput, 5, 1);
		
		
		
		
		
		//Add employee button
		Button addEmployeeButton = new Button("Add");

		addEmployeeButton.setOnAction(e -> {

			//Checks is employee numbers is an integer
			Boolean isemployeeNoInt = isInt(addEmployeeNoInput, addEmployeeNoInput.getText());
			
			if (isemployeeNoInt == true) {
				
				//New insert query
				InsertRecord insertNewemployee = new InsertRecord();
				boolean employeeInserted = insertNewemployee.insertNewEmployee(con, Integer.parseInt(addEmployeeNoInput.getText()), addfNameInput.getText() , addlNameInput.getText() , addDateOfBirthInput.getText() , addAddressInput.getText() ,addHourlyPayInput.getText());
				
				if (employeeInserted == true) {
					
					//Creates a string to be added to list.
					String employeeAdded = (addEmployeeNoInput.getText() + "\t\t " + addfNameInput.getText() + "\t" + addlNameInput.getText() + "\t" + addDateOfBirthInput.getText() + "\t" + addAddressInput.getText() + "\t" + addHourlyPayInput.getText());

					//Clears boxes 
					addEmployeeNoInput.clear();
					addfNameInput.clear();
					addlNameInput.clear();
					addDateOfBirthInput.clear();
					addAddressInput.clear();
					addHourlyPayInput.clear();
					
					//Adds employee to list.
					employeeList.getItems().add(employeeAdded);
					result.setText("Employee added");
					// }
					employeeList.refresh();
				}
			}
		});

		//Delete employee button
		Button deleteEmployee = new Button("Delete");

		deleteEmployee.setOnAction(g -> {
			
			
			String employeeNo = "";
			String chosenEmployee = "";
				
			
			ObservableList<String> employees;
			employees = employeeList.getSelectionModel().getSelectedItems();
		
			//Gets selected employee
			for (String m : employees) {
		
				chosenEmployee += m;
				//Obtains employee number from string
				employeeNo = (chosenEmployee.charAt(0) + ""  + chosenEmployee.charAt(1) + "" + chosenEmployee.charAt(2) + "" + chosenEmployee.charAt(3) + "" + chosenEmployee.charAt(4));	
			}
			
			//New delete query
			DeleteRecord deleteEmployeeQuery = new DeleteRecord();
			boolean employeeDeleted = deleteEmployeeQuery.deleteEmployee(con, employeeNo);
			
			//If query was successful
			if(employeeDeleted == true){
					
				//Removes employee from list
				employeeList.getItems().remove(chosenEmployee);
			}
			
			else{
				result.setText("Error.");
			}
			
			

		});

		GridPane.setConstraints(result, 1, 2);
		GridPane.setConstraints(deleteEmployee, 0, 3);

		GridPane.setConstraints(addEmployeeButton, 0, 2);
		grid.getChildren().addAll(addEmployeeNoLabel, addEmployeeNoInput, addfNameLabel, addfNameInput, addlNameLabel, addlNameInput, addDateOfBirthLabel, addDateOfBirthInput, addAddressLabel, addAddressInput, addHourlyPayLabel, addHourlyPayInput,  addEmployeeButton, deleteEmployee,result);
		grid.setAlignment(Pos.CENTER);

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(employeeTitle, employeeList, addEmployeeTitle, grid);

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
			result.setText("Error.");
			return false;
		}
	}

}
