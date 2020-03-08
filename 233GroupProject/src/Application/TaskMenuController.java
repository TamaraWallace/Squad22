package Application;

import javafx.fxml.FXML;
import javafx.scene.text.*;
import main.Task;
import main.TextBasedApp;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class TaskMenuController implements Initializable {

	@FXML
	private Text taskName;
	
	@FXML
	private Text dueDate;
	
	@FXML
	private TextArea notesArea;
	
	//NOTE: right now, this is just a default task, 
	//but we need to integrate with select task somehow or add that to GUI
	private static Task selectedTask;
	
	public TaskMenuController(){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(selectedTask != null) {
			taskName.setText("Test");
			dueDate.setText(selectedTask.getDueDate().toString());
			notesArea.setText(selectedTask.getNotes());
		}

	}
	
	public void completeButton(ActionEvent event) throws IOException {
		selectedTask.setCompleted(true);
		
		for(Task t : GuiBasedApp.getTasks().getActiveTasks()) {
			if(t.getTaskID().equals(selectedTask.getTaskID())) {
				t.setCompleted(true);
			}
		}
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		Scene homeScene = new Scene(pane);
		
		homeScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(homeScene);
		window.show();
	}
	
	public void deleteButton(ActionEvent event) throws IOException {
		//I'm not sure we have delete implemented yet
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		Scene homeScene = new Scene(pane);
		
		homeScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(homeScene);
		window.show();
	}
	
	public void backButton(ActionEvent event) throws IOException {

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Scene backScene = GuiBasedApp.getPrevScene();
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(backScene);
		window.show();
	}
	
	public void editButton(ActionEvent event) throws IOException {
		System.out.println("This is not implemented yet.");
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		
		Scene homeScene = new Scene(pane);
		
		homeScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(homeScene);
		window.show();
	}
	
	public static void setSelectedTask(Task t) {
		selectedTask = t;
	}

}
