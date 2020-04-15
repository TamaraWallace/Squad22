package Application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddTaskController implements Initializable{
	
		@FXML 
		private TextField name,notes;
		@FXML
		private DatePicker date;
		
		@FXML 
		private Label dateLabel,nameLabel;
		
		@FXML
		private Button backBtn, addBtn;

		// FXML styling 
		private static final String defaultStyle = "-fx-background-color: #ffffff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

		private static final String warningStyle = "-fx-background-color: #ffffff; -fx-border-color: #ff0000; -fx-border-width: 5px;";
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("Add Task Scene");
		}
		
		
		// ----------------------- EVENT HANDLERS -----------------------
		
		
		//Method Purpose: Handler for the backBtn. Launches the Home Screen Scene
		//Parameters: ActionEvent event
		//Return Value: Void
		@FXML
		public void back(ActionEvent event) {
			GuiBasedApp.launchHomeScreenScene();
		}
		
		//Method Purpose: 	Handler for the addBtn. Attempts to create a new Task given the field values. If 
		//					successful, launches the Home Screen Scene.
		//Parameters: 		ActionEvent event
		//Return Value: 	Void
		//Functionality:	Verifies that the User has input a Name, Notes (optional), and a Due Date in the fields
		//					provided. Notifies the User if either Name or Due Date are missing. If both Name and Due
		//					Date are present, creates a new Task with the given field values.
		@FXML
		public void addButton(ActionEvent event) {
			// get name, notes, and due date from fields
			String taskName = name.getText();
			String taskNotes = notes.getText();
			LocalDate taskDate = date.getValue();
			
			if (taskName.isEmpty()) {
				// validate that taskName is not empty
				nameLabel.setText("Must have a name!");
				name.setStyle(warningStyle);
			} else {
				nameLabel.setText("");
				name.setStyle(defaultStyle);
			}
			
			if (taskDate == null) {
				// validate that taskDate is not empty 
				dateLabel.setText("Must provide a due date!");
				date.setStyle(warningStyle);
			} else {
				dateLabel.setText("");
				date.setStyle(defaultStyle);
			}
			
			if (taskDate != null && !taskName.isEmpty()) {
				// if everything is good, make a task
				GuiBasedApp.addTask(taskName, taskNotes, taskDate);
				
				// launch the Home Screen Scene
				GuiBasedApp.launchHomeScreenScene();
			}
		}
}