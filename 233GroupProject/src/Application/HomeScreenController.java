package Application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HomeScreenController {
	
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
	private Label helloUser, currentTasksTitle, viewAllLabel;
	
	public HomeScreenController() {
		//String name = main.User.getUsrName();
		//helloUser.setText("Hello, " + name);
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
	}
	
	
	public void mostRecentTask() {
		//task1.setCompleted(true);
	}
	
	public void secondRecentTask() {
		//task2.setCompleted(true);
	}
	
	public void thirdRecentTask() {
		//task3.setCompleted(true);
	}
	
	
	public void viewAll() {
		
	}

	//scene transitions?
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
	}
	
	
	//can add later for demo 3 
	public void profileButton() {
		
	}

	
	
	
}

