package Application;

import javafx.fxml.FXML;
import javafx.scene.text.*;
import main.Task;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.io.IOException;

public class TaskMenuController implements Initializable {

	@FXML
	private Text taskName; //associated with text object for task name in FXML
	
	@FXML
	private Text dueDate; //associated with text object for task due date in FXML
	
	@FXML
	private TextArea notesArea; //associated with text area object for task notes in FXML
	
	private static Task selectedTask; //the task to be displayed
	
	// Initialize Method, implemented from the Initializable Class
	// When the class is started it sets the values of the display text to the task information
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		System.out.println("\nTask Menu Scene");
		
		if(selectedTask != null) {
			taskName.setText(selectedTask.getName());
			dueDate.setText(selectedTask.getDueDate().toString());
			notesArea.setText(selectedTask.getNotes());
		}

	}
	
	// Method Name: completeButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the complete button is pressed, the selected task is marked as complete
	//				  and the user is returned to the home screen
	public void completeButton(ActionEvent event) throws IOException {
		//setting the selected task to completed
		selectedTask.complete();
		
		//ensuring the task is also marked as complete in the GuiBasedApp
		for(Task t : GuiBasedApp.getTasks().getActiveTasks()) {
			if(t.getTaskID().equals(selectedTask.getTaskID())) {
				t.complete();
			}
		}
		
		//changing the scene to the home screen
		GuiBasedApp.launchHomeScreenScene();
	}
	
	// Method Name: deleteButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the delete button is pressed, the selected task is marked for deletion
	//				  and the user is returned to the home screen
	public void deleteButton(ActionEvent event) throws IOException {
		selectedTask.delete();
		
		//returning the user to the home screen
		GuiBasedApp.launchHomeScreenScene();
	}
	
	// Method Name: backButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the back button is pressed, the user is returned to 
	//				  the previous screen
	public void backButton(ActionEvent event) throws IOException {
		GuiBasedApp.launchHomeScreenScene();
	}
	
	// Method Name: editButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the edit button is pressed, the user is taken to a screen where
	//				  where they can edit a task's information
	public void editButton(ActionEvent event) throws IOException {
		//telling the EditTaskController which task is being edited
		EditTaskController.setTaskToEdit(selectedTask);
		
		//sending the user to the edit task screen
		GuiBasedApp.launchEditTaskScene();
	}
	
	// Method Name: setSelectedTask
	// Parameters: a task t
	// Return: void
	// Functionality: sets the selected task to the task t provided
	public static void setSelectedTask(Task t) {
		selectedTask = t;
	}
}
