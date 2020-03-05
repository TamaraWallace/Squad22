package Application;

import javafx.fxml.FXML;
import javafx.scene.text.*;
import  main.Task;
import javafx.scene.control.TextArea;
import java.util.UUID;
import java.time.LocalDate;

public class TaskMenuController {

	@FXML
	private Text taskName;
	
	@FXML
	private Text dueDate;
	
	@FXML
	private TextArea notesArea;
	
	private LocalDate due = LocalDate.now();
	
	//NOTE: right now, this is just a default task, 
	//but we need to integrate with select task somehow or add that to GUI
	Task selectedTask = new Task(UUID.randomUUID(), "Test", "", false, due);
	
	public TaskMenuController() {
		
	}
	
	@FXML
	public void initialize() {
		if(selectedTask != null) {
			taskName.setText("Test");
			dueDate.setText(selectedTask.getDueDate().toString());
			notesArea.setText(selectedTask.getNotes());
			
		} else {
			taskName.setText("Hmm.. no selected task");
			dueDate.setText("Today");
			notesArea.setText("Placeholder");
		}
	}
	
	public void completeButton() {
		selectedTask.setCompleted(true);
		System.out.println(selectedTask.isComplete());
		
		//Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		//AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		//Scene homeScene = new Scene(pane);
		
		//createUserScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		//GuiBasedApp.setPrevScene(window.getScene());
		//window.setScene(homeScene);
		//window.show();
	}
	
	public void deleteButton() {
		//I'm not sure we have delete implemented yet
		
		//Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		//AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		//Scene homeScene = new Scene(pane);
		
		//createUserScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		//GuiBasedApp.setPrevScene(window.getScene());
		//window.setScene(homeScene);
		//window.show();
	}
	
	public void editButton() {
		
		
		//Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		//AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		//Scene homeScene = new Scene(pane);
		
		//createUserScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		//GuiBasedApp.setPrevScene(window.getScene());
		//window.setScene(homeScene);
		//window.show();
	}

}
