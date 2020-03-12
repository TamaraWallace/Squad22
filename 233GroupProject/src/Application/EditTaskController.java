package Application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Task;

public class EditTaskController {


	@FXML 
	private TextField name,notes;
	@FXML
	private DatePicker date;
	
	@FXML 
	private Label dateLabel,nameLabel;
	
	@FXML
	private Button backBtn;
	

	@FXML
	public void doneButton(ActionEvent event) throws IOException {
		
		
		// get name & notes as strings
		String taskName = name.getText();
		String taskNotes = notes.getText();
		// validating taskName is not empty
		if (taskName.isEmpty()) {
			nameLabel.setText("TASK NAME CAN'T BE EMPTY");
			taskName = name.getText();
		} else {
			nameLabel.setText("");
		}			
		// validating the taskDate is not empty 
		LocalDate taskDate = date.getValue();
		if (taskDate == null) {
			dateLabel.setText("TASK DATE CAN'T BE EMPTY");
		} else {
			dateLabel.setText("");
		}
		// if everything is good, make a task:
		if (taskDate != null && !taskName.isEmpty()) {
			System.out.println("the name is " + taskName + "the date is" + taskDate + "the notes are" + taskNotes);
//			Task task = new Task(GuiBasedApp.getUser().getUsrID(), taskName, taskNotes, false, taskDate);
//
//			GuiBasedApp.addTask(task);
//
//			System.out.println("New task created:\n" + task.toString());
			
			// clearing out textfield boxes for name & notes 
			name.clear();
			notes.clear();
			// clear out the date picker as it doesn't do this automatically 
			date.setValue(null);
			
			// GUI set up code to show on the screen 
//			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			
//			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
//				
//			Scene HomeScreenScene = new Scene(pane);
//				
//			HomeScreenScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
//				
//			GuiBasedApp.setPrevScene(window.getScene());
//			window.hide();
//			window.setScene(HomeScreenScene);
//			window.show();
		}
	}
			// back button 
				@FXML
				public void back(ActionEvent event) {
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(GuiBasedApp.getPrevScene());
				}
}
