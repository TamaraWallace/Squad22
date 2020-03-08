package Application;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HomeScreenController implements Initializable {
	
	@FXML
	private RadioButton mostRecentTask;
	
	@FXML
	private RadioButton secondRecentTask;
	
	@FXML
	private RadioButton thirdRecentTask;
	
	@FXML
	private Button viewAll;
	
	@FXML
	private Button profileButton;
	
	@FXML
	private Button addTask;
	
	@FXML 
	private Label helloUser, currentTasksTitle;
	
	public HomeScreenController() {
	}
	
	@Override 
	public void initialize(URL arg0, ResourceBundle arg1) {
		String name = main.User.getUsrName();
		helloUser.setText("Hello, " + name);
		
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
	
	
	public void viewAll() {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DisplayTasks.fxml"));
		
		Scene viewAllScene = new Scene(pane);
		
		viewAllScene.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(viewAllScene);
		window.show();
	}


	public void addTask() {
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
		
		addTaskScene.getStylesheets().add(getClass().getResource("applicationADDTASK.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(addTaskScene);
		window.show();
		
	}
	
	//can add later for demo 3 
	public void profileButton() {
		
	}

	
	
	
}

