package Application;

import java.io.IOException;
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

public class EditTaskController implements Initializable {


	@FXML 
	private TextField name,notes;
	@FXML
	private DatePicker date;
	
	@FXML 
	private Label dateLabel,nameLabel;
	
	@FXML
	private Button backBtn, doneBtn;
	
	private static final String warningStyle = "-fx-background-color: #ffffff; -fx-border-color: #ff0000; -fx-border-width: 5px;";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		System.out.println("Edit Task Scene");
		
		String[] selectedTasksInfo = GuiBasedApp.getSelectedTasksInfo();
		
		name.setText(selectedTasksInfo[0]);
		notes.setText(selectedTasksInfo[1]);
		date.setValue(LocalDate.parse(selectedTasksInfo[2]));
	}
	
	
	// ----------------------- EVENT HANDLERS -----------------------
	

	// Method Purpose:	Handler for doneBtn. Attempts to update the selected Task with the given field values. If
	//					successful, brings user back to Home Screen Scene.
	// Parameters:		ActionEvent event
	// Return Value:	Void
	// Functionality:	Verifies that the User has input a Name, Notes (optional), and a Due Date in the fields
	//					provided. Notifies the User if either Name or Due Date are missing. If both Name and Due
	//					Date are present, creates a new Task with the given field values.
	public void doneButton(ActionEvent event) throws IOException {
		// get name, notes & due date from the fields.
		String taskName = name.getText();
		String taskNotes = notes.getText();
		LocalDate taskDate = date.getValue();
		
		if (taskName.isEmpty()) {
			// validate taskName is not empty
			nameLabel.setText("Task must have a name!");
			name.setStyle(warningStyle);
		}
		
		if (taskDate == null) {
			// validate the taskDate is not empty 
			dateLabel.setText("Task must have a due date!");
			date.setStyle(warningStyle);
		}
		
		if (taskDate != null && !taskName.isEmpty()) {
			// if everything is good, edit the task
			GuiBasedApp.editSelectedTask(taskName, taskNotes, taskDate);
			
			// launch home screen scene
			GuiBasedApp.launchHomeScreenScene();	
		}
	}
	
	//Method Purpose: 	Handler for backBtn. Launches the Task Menu Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	public void back(ActionEvent event) {
		GuiBasedApp.launchTaskMenuScene();
	}
}
