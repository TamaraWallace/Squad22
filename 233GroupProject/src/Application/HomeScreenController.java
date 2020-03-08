package Application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Task;
import main.TaskCollection;

public class HomeScreenController implements Initializable {
	
	@FXML
	private RadioButton mostRecentTask;
	
	@FXML
	private RadioButton secondRecentTask;
	
	@FXML
	private RadioButton thirdRecentTask;
	
	@FXML
	private Button viewAllbtn;
	
	@FXML
	private Button profileButton;
	
	@FXML
	private Button addTaskBtn;
	
	@FXML 
	private Label helloUser, currentTasksTitle;
	
	public HomeScreenController() {
	}
	
	@Override 
	public void initialize(URL arg0, ResourceBundle arg1) {
		String usrName = GuiBasedApp.getLgnUserName();
		helloUser.setText("Hello, " + usrName);
		
		TaskCollection tasks_object = GuiBasedApp.getTasks();
		ArrayList<Task> tasks_list = tasks_object.getActiveTasks();
		int list_size = tasks_list.size();
		if (list_size==1) {
			String name = tasks_list.get(0).getName();
			this.mostRecentTask.setText(name);
			
			this.secondRecentTask.setText("Add Tasks!");
			this.thirdRecentTask.setText("Add Tasks!");
		}
		else if(list_size==2) {
			String name = tasks_list.get(0).getName();
			this.mostRecentTask.setText(name);
			
			String name2 = tasks_list.get(1).getName();
			this.secondRecentTask.setText(name2);
			
			this.thirdRecentTask.setText("Add Tasks!");
		}
		else if (list_size>=3) {
			String name = tasks_list.get(0).getName();
			this.mostRecentTask.setText(name);
			
			String name2 = tasks_list.get(1).getName();
			this.secondRecentTask.setText(name2);
			
			String name3 = tasks_list.get(2).getName();
			this.thirdRecentTask.setText(name3);
		}
		else if (list_size==0) {
			this.mostRecentTask.setText("Add Tasks!");
			this.secondRecentTask.setText("Add Tasks!");
			this.thirdRecentTask.setText("Add Tasks!");
		}
		
	}
	
	
	public void mostRecentTask() {
		//will implement these buttons once we can sort the tasks by date so that if you select a task
		// it goes to a scene with the details of the task and a complete button at the bottom 
				
	}
	
	public void secondRecentTask() {
		
	}
	
	public void thirdRecentTask() {
		
	}
	
	
	@FXML
	public void viewAll(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DisplayTasks.fxml"));
		
		Scene viewAllScene = new Scene(pane);
		
		viewAllScene.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(viewAllScene);
		window.show();
		}


	@FXML
	public void addTask(ActionEvent event) throws IOException {
		//https://www.youtube.com/watch?v=LDVztNtJWOo 
		
		//https://stackoverflow.com/questions/34863425/javafx-scene-builder-how-switch-scene 
		//Here's how you get those references:
		//Scene scene = btnSignIn.getScene();
		//Window window = scene.getWindow();
		//Stage stage = (Stage) window;
		
		//change the entire Scene:
		//FXMLLoader loader = ... // create and load() view
		//Stage stage = (Stage) btnSignIn.getScene().getWindow();
		//Scene scene = new Scene(loader.getRoot());
		//stage.setScene(scene);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Pane pane = (Pane) FXMLLoader.load(getClass().getResource("AddTask.fxml"));
		
		Scene addTaskScene = new Scene(pane);
		
		addTaskScene.getStylesheets().add(getClass().getResource("AddTask.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(addTaskScene);
		window.show();
		
	}
	
	//can add later for demo 3 
	public void profileButton() {
		
	}

	
	
	
}

